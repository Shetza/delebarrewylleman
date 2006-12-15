-----------------------------------------------------------------------------------------------------------
--#########################################################################################################
--#																										  #
--#								TRIGGERS SUR LA TABLE EMPRUNTS											  #
--#																										  #
--#########################################################################################################
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- Trigger testant la possibilite d'un emprunt
--		Leve une exception si le nombre d'emprunt maximum est deja atteint
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_verifier_emprunts_maximum() returns TRIGGER AS $BODY$
DECLARE nbDvdEmpruntes integer;
BEGIN
	SELECT count(*) INTO nbDvdEmpruntes
		FROM emprunts
		WHERE NEW.utilisateurs_id = utilisateurs_id
		AND date_retour IS NULL;
		
	IF(nbDvdEmpruntes > 2) THEN
		RAISE EXCEPTION 'emprunt impossible (maximum atteint)';
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_verifier_emprunts_max
	BEFORE INSERT ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_verifier_emprunts_maximum();

-----------------------------------------------------------------------------------------------------------
-- Trigger fixant la valeur 0 par defaut pour le champ prolonge
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_prolonge_0() returns TRIGGER AS $BODY$
BEGIN
	IF ( NEW.prolonge IS NULL OR NEW.prolonge < 0 ) THEN
		NEW.prolonge = 0;
	--ELSE 
	--	NEW.prolonge  = OLD.prolonge +1;
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_prolonge_0
	BEFORE INSERT OR UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_prolonge_0();

-----------------------------------------------------------------------------------------------------------
-- Trigger testant la possiblilite d'un prolongement
--		Leve une exception si l'emprunt a deja ete prolonge une fois
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_prolongement_possible() returns TRIGGER AS $BODY$
DECLARE compteur integer;
BEGIN
	SELECT count(*) INTO compteur
		FROM emprunts e
		WHERE NEW.prolonge > 1;

	IF (compteur > 1) THEN
		RAISE EXCEPTION 'impossible de prolonger';
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_prolongement_possible
	BEFORE UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_prolongement_possible();

-----------------------------------------------------------------------------------------------------------
-- Trigger testant la possiblilite d'un emprunt
--		Leve une exception si le dvd est deja emprunte
-- + Force la date d'emprunt a etre egale a la date du jour.
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_emprunt_possible() returns TRIGGER AS $BODY$
DECLARE compteur integer;
BEGIN
	SELECT count(*) into compteur
		FROM emprunts e
		WHERE e.dvds_id = NEW.dvds_id
		AND date_retour IS NULL;

	IF (compteur > 0) THEN
		RAISE EXCEPTION 'emprunt impossible (deja emprunte)';
	END IF;
	
	SELECT count(*) into compteur
		FROM emprunts e
		WHERE e.dvds_id = NEW.dvds_id
		AND date_reserve2emprunt IS NULL;

	IF (compteur > 0) THEN
		RAISE EXCEPTION 'emprunt impossible (reserve)';
	END IF;
	
	-- On force la date d'emprunt a etre egale a la date du jour.
	NEW.date_emprunt = current_date;
	
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_emprunt_possible
	BEFORE INSERT ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_emprunt_possible();

-----------------------------------------------------------------------------------------------------------
-- Trigger testant la possibilite d'une reservation
--		Leve une exception si la reservation est impossible car celui qui tente de reserve emprunte deja le dvd
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_reservation_possible() returns TRIGGER AS $BODY$
DECLARE compteur integer;
BEGIN
	-- Fonction a executer UNIQUEMENT si on met a jour le loueur suivant.
	IF(NEW.loueur_suivant_id <> NULL) THEN
		SELECT count(*) INTO compteur
			FROM emprunts e
			WHERE e.dvds_id = NEW.dvds_id
			AND date_retour IS NULL
			AND e.loueur_suivant_id IS NOT NULL;
		IF (compteur > 0) THEN
			RAISE EXCEPTION 'reservation impossible (deja reserve)';
		END IF;
	
		SELECT count(*) INTO compteur
			FROM emprunts e
			WHERE e.utilisateurs_id = NEW.loueur_suivant_id
			AND e.dvds_id = NEW.dvds_id
			AND date_retour IS NULL;
		IF (compteur > 0) THEN
			RAISE EXCEPTION 'reservation impossible (emprunt en cours)';
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_reservation_possible
	BEFORE UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_reservation_possible();


-----------------------------------------------------------------------------------------------------------
-- Trigger qui bascule un reserveur en emprunter au rendu du dit dvd.
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_rendu_vers_emprunteur_suivant() returns TRIGGER AS $BODY$
DECLARE 
	nbDvdEmpruntes integer;
	ancienneReservation integer;
BEGIN
	
	SELECT dvds_id INTO ancienneReservation
		FROM emprunts
		WHERE NEW.utilisateurs_id = loueur_suivant_id
		AND date_reserve2emprunt IS NULL
		ORDER BY date_retour ASC
		LIMIT 1;
	
	-- Si le rendeur a des reservations en suspens alors une de ces reservation est transformee en emprunt.
	IF(ancienneReservation IS NOT NULL) THEN
		-- On doit desactiver le trigger 'trigger_emprunt_possible'
		-- qui empeche l'emprunt d'un dvd en cours d'emprunt.
		-- En effet, ici nous ajoutons un emprunt sur un dvd qui est en train d'etre rendu
		-- mais qui ne l'est pas totalement.
		ALTER TABLE emprunts 
			DISABLE TRIGGER trigger_emprunt_possible;
		INSERT INTO emprunts VALUES(ancienneReservation, NEW.utilisateurs_id, current_date, null, 0, null, null);
		-- Re-activation du trigger 'trigger_emprunt_possible'.
		ALTER TABLE emprunts 
			ENABLE TRIGGER trigger_emprunt_possible;
				
		-- On doit desactiver ce trigger qui s'appelera au moment du 'UPDATE' ci-dessous
		ALTER TABLE emprunts 
			DISABLE TRIGGER trigger_rendu_vers_emprunteur_suivant;
		UPDATE emprunts SET date_reserve2emprunt = current_date 
			WHERE ancienneReservation = dvds_id
			AND NEW.utilisateurs_id = loueur_suivant_id
			AND date_reserve2emprunt IS NULL;
		-- Re-activation du trigger 'trigger_rendu_vers_emprunteur_suivant'.
		ALTER TABLE emprunts 
			ENABLE TRIGGER trigger_rendu_vers_emprunteur_suivant;
	END IF;
	
	SELECT count(*) INTO nbDvdEmpruntes
		FROM emprunts
		WHERE NEW.loueur_suivant_id = utilisateurs_id
		AND OLD.date_retour IS NULL;
	
	-- Si le reserveur du dvd rendu n'a pas deja ses 3 emprunts
	-- Alors il devient le nouvel emprunteur du dvd.
	IF(nbDvdEmpruntes < 3) THEN		
		IF(NEW.loueur_suivant_id IS NOT NULL AND NEW.date_retour IS NOT NULL) THEN
			-- On doit desactiver le trigger 'trigger_emprunt_possible'
			-- qui empeche l'emprunt d'un dvd en cours d'emprunt.
			-- En effet, ici nous ajoutons un emprunt sur un dvd qui est en train d'etre rendu
			-- mais qui ne l'est pas totalement.
			ALTER TABLE emprunts 
				DISABLE TRIGGER trigger_emprunt_possible;
			INSERT INTO emprunts VALUES(NEW.dvds_id, NEW.loueur_suivant_id, current_date, null, 0, null, null);
			-- Re-activation du trigger 'trigger_emprunt_possible'.
			ALTER TABLE emprunts 
				ENABLE TRIGGER trigger_emprunt_possible;
		END IF;
		
		-- On doit desactiver ce trigger qui s'appelera au moment du 'UPDATE' ci-dessous
		ALTER TABLE emprunts 
			DISABLE TRIGGER trigger_rendu_vers_emprunteur_suivant;
		UPDATE emprunts SET date_reserve2emprunt = current_date 
			WHERE NEW.dvds_id = dvds_id
			AND NEW.utilisateurs_id = utilisateurs_id
			AND NEW.date_retour = current_date
			AND date_reserve2emprunt IS NULL;
		-- Re-activation du trigger 'trigger_rendu_vers_emprunteur_suivant'.
		ALTER TABLE emprunts 
			ENABLE TRIGGER trigger_rendu_vers_emprunteur_suivant;
		
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_rendu_vers_emprunteur_suivant
	AFTER UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_rendu_vers_emprunteur_suivant();

-----------------------------------------------------------------------------------------------------------
-- Trigger assurant que la date de retour d'un dvd n'est pas antidate
--		Force la date de retour d'un dvd a la date du jour
-----------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION fonction_rendu_est_aujourdhui() returns TRIGGER AS $BODY$
BEGIN
	IF(NEW.date_retour IS NOT NULL) THEN
		NEW.date_retour = current_date;
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_rendu_est_aujourdhui
	BEFORE UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_rendu_est_aujourdhui();