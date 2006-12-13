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
	SELECT nombre_emprunts INTO nbDvdEmpruntes
			FROM vue_nombre_emprunts;

		IF(nbDvdEmpruntes > 3) THEN
			RAISE EXCEPTION 'emprunt impossible (maximum atteint)';
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_verifier_emprunts_max
	AFTER INSERT OR UPDATE ON emprunts FOR EACH ROW
	EXECUTE PROCEDURE fonction_verifier_emprunts_maximum();

-----------------------------------------------------------------------------------------------------------
-- Trigger fixant la valeur 0 par defaut pour le champ prolonge
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_prolonge_0() returns TRIGGER AS $BODY$
BEGIN
	IF ( NEW.prolonge IS NULL OR NEW.prolonge < 0 ) THEN
		NEW.prolonge = 0;
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
		WHERE
			NEW.prolonge > 1;

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
--#########################################################################################################
--#																										  #
--#								TRIGGERS SUR LA TABLE RESERVES											  #
--#																										  #
--#########################################################################################################
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- Trigger testant la possibilite d'une reservation
--		Leve une exception si la reservation est impossible car celui qui tente de reserve emprunte deja le dvd
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_reservation_possible() returns TRIGGER AS $BODY$
DECLARE emprunteur integer;
DECLARE emprunteur_suivant integer;
DECLARE compteur integer;
BEGIN
	SELECT count(*) INTO compteur
		FROM reserves r
		WHERE r.dvds_id = NEW.dvds_id;
	IF (compteur > 0) THEN
		RAISE EXCEPTION 'reservation impossible (deja reserve)';
	END IF;

	SELECT count(*) INTO compteur
		FROM emprunts e
		WHERE
			e.utilisateurs_id = NEW.utilisateurs_id
		AND
			e.dvds_id = NEW.dvds_id;
	IF (compteur > 0) THEN
		RAISE EXCEPTION 'reservation impossible (emprunt en cours)';
	END IF;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_reservation_possible
	BEFORE INSERT OR UPDATE ON reserves FOR EACH ROW
	EXECUTE PROCEDURE fonction_reservation_possible();

-----------------------------------------------------------------------------------------------------------
-- Trigger assurant la coherence entre la table emprunts et la table reserves
--		Insere dans la table emprunts l'id d'une personne reservant un dvd
-----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION fonction_coherence_reservation_emprunts() returns TRIGGER AS $BODY$
BEGIN
	UPDATE emprunts SET loueur_suivant_id = NEW.utilisateurs_id WHERE dvds_id = NEW.dvds_id;
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER trigger_reservation_possible
	AFTER INSERT OR UPDATE ON reserves FOR EACH ROW
	EXECUTE PROCEDURE fonction_coherence_reservation_emprunts();
