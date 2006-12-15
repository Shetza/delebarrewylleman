--------------------------------------------------------------------------------------------------------------------------------------------
-- Vue vue_emprunteurs
--
-- nom_emprunteur | prenom_emprunteur |       titre_dvd        | prolonge | debut_emprunt | date_limite | date_rendu | nom_emprunteur_suivant | prenom_emprunteur_suivant
------------------+-------------------+------------------------+----------+---------------+-------------+------------+------------------------+---------------------------
-- Delebarre      | Johann            | Soldier                |        0 | 2006-11-05    | 2006-11-20  | 2006-11-10 |                        |
-- Delebarre      | Johann            | Tango & Cash           |        1 | 2006-12-04    | 2007-01-03  |            | Wylleman               | Julien
-- Wylleman       | Julien            | Ghosts of Mars         |        0 | 2006-11-20    | 2006-12-05  |            |                        |
-- Wylleman       | Julien            | New York 1997          |        0 | 2006-12-03    | 2006-12-18  |            |                        |
-- Wylleman       | Julien            | The Matrix Revolutions |        0 | 2006-12-06    | 2006-12-21  |            |                        |

CREATE VIEW vue_emprunteurs
	AS SELECT
		u.nom AS nom_emprunteur,
		u.prenom AS prenom_emprunteur,
		d.titre AS titre_dvd,
		e.prolonge AS prolonge,
		e.date_emprunt AS debut_emprunt,
		e.date_emprunt+(15+(e.prolonge*15)) AS date_limite,
		e.date_retour AS date_rendu,
		NULL AS nom_emprunteur_suivant,
		NULL AS prenom_emprunteur_suivant
	FROM
		utilisateurs u,
		dvds d,
		emprunts e
	WHERE
		d.id = e.dvds_id
	AND
		u.id = e.utilisateurs_id
	AND
		e.loueur_suivant_id IS NULL
	UNION SELECT
		u.nom AS nom_emprunteur,
		u.prenom AS prenom_emprunteur,
		d.titre AS titre_dvd,
		e.prolonge AS prolonge,
		e.date_emprunt AS debut_emprunt,
		e.date_emprunt+(15+(e.prolonge*15)) AS date_limite,
		e.date_retour AS date_rendu,
		usuiv.nom AS nom_emprunteur_suivant,
		usuiv.prenom AS prenom_emprunteur_suivant
	FROM
		utilisateurs u,
		utilisateurs usuiv,
		dvds d,
		emprunts e
	WHERE
		d.id = e.dvds_id
	AND
		u.id = e.utilisateurs_id
	AND
		e.loueur_suivant_id IS NOT NULL
	AND
		e.loueur_suivant_id = usuiv.id;
--------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------
-- Vue vue_emprunts
--
--       titre_dvd        | nom_emprunteur | prenom_emprunteur | nom_emprunteur_suivant | prenom_emprunteur_suivant | date_limite
--------------------------+----------------+-------------------+------------------------+---------------------------+-------------
-- Tango & Cash           | Delebarre      | Johann            | Wylleman               | Julien                    | 2007-01-03
-- Ghosts of Mars         | Wylleman       | Julien            |                        |                           | 2006-12-05
-- New York 1997          | Wylleman       | Julien            |                        |                           | 2006-12-18
-- The Matrix Revolutions | Wylleman       | Julien            |                        |                           | 2006-12-21

CREATE VIEW vue_emprunts
	AS SELECT
		ve.titre_dvd AS titre_dvd,
		ve.nom_emprunteur AS nom_emprunteur,
		ve.prenom_emprunteur AS prenom_emprunteur,
		ve.nom_emprunteur_suivant AS nom_emprunteur_suivant,
		ve.prenom_emprunteur_suivant AS prenom_emprunteur_suivant,
		ve.date_limite AS date_limite
	FROM
		vue_emprunteurs ve
	WHERE
		ve.date_rendu IS NULL;
--------------------------------------------------------------------------------------------------------------------------------------------

CREATE VIEW vue_emprunts2
	AS SELECT
		u.id as utilisateurs_id,
		d.id as dvds_id,
		d.titre as dvds_titre,
		e.date_emprunt as date_emprunt,
		e.date_emprunt+15 as date_limite,
		e.date_retour as date_retour,
		e.prolonge as prolonge,
		e.loueur_suivant_id as reserve_utilisateurs_id
	FROM
		utilisateurs u,
		dvds d,
		emprunts e
	WHERE d.id = e.dvds_id
	AND u.id = e.utilisateurs_id
	AND e.date_reserve2emprunt IS NULL;

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE VIEW vue_reserves2
	AS SELECT
		u.id as utilisateurs_id,
		d.id as dvds_id,
		d.titre as dvds_titre,
		e.date_emprunt as date_emprunt,
		e.date_emprunt+15 as date_limite,
		e.date_retour as date_retour,
		e.prolonge as prolonge,
		e.loueur_suivant_id as reserve_utilisateurs_id
	FROM
		utilisateurs u,
		dvds d,
		emprunts e
	WHERE d.id = e.dvds_id
	AND u.id = e.utilisateurs_id
	AND e.loueur_suivant_id IS NOT NULL
	AND e.date_reserve2emprunt IS NULL;

--------------------------------------------------------------------------------------------------------------------------------------------
-- Vue vue_nombre_emprunts
--
-- nom_emprunteur | nombre_emprunts
------------------+-----------------
-- Delebarre      |               1
-- Wylleman       |               3

CREATE VIEW vue_nombre_emprunts
	AS SELECT ve.nom_emprunteur AS nom_emprunteur, 
		count(*) AS nombre_emprunts 
	FROM 
		vue_emprunteurs ve 
	WHERE 
		ve.date_rendu IS NULL 
	GROUP BY 
		ve.nom_emprunteur;
--------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------
-- Vue vue_retardataires
--
-- nom_emprunteur | prenom_emprunteur |   titre_dvd
------------------+-------------------+----------------
-- Wylleman       | Julien            | Ghosts of Mars

CREATE VIEW vue_retardataires
	AS SELECT
		ve.nom_emprunteur AS nom_emprunteur,
		ve.prenom_emprunteur AS prenom_emprunteur,
		ve.titre_dvd AS titre_dvd
	FROM
		vue_emprunteurs ve
	WHERE
		ve.date_rendu IS NULL
	AND
		ve.date_limite < current_date;
--------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------
-- Vue vue_reservations
--  
--  titre_dvd   | nom_emprunteur_suivant | prenom_emprunteur_suivant | debut_emprunt | date_limite
----------------+------------------------+---------------------------+---------------+-------------
-- Tango & Cash | Wylleman               | Julien                    | 2006-12-04    | 2006-12-19

CREATE VIEW vue_reservations
	AS SELECT
		d.titre AS titre_dvd,
		u.nom AS nom_emprunteur_suivant,
		u.prenom AS prenom_emprunteur_suivant,
		e.date_emprunt AS debut_emprunt,
		e.date_emprunt+15 AS date_limite
	FROM
		utilisateurs u,
		dvds d,
		emprunts e
	WHERE
		d.id = e.dvds_id
	AND
		u.id = e.loueur_suivant_id;
--------------------------------------------------------------------------------------------------------------------------------------------
