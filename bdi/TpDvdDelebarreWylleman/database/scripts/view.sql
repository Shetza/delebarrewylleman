-- une vue des emprunts
-- une vue du nombre d'emprunts
-- un trigger pour ne permettre qu'un seul emprunt d'un dvd

CREATE VIEW vu_nombre_emprunts
	AS SELECT u.nom, count(*) FROM utilisateurs u, dvds d, emprunts e WHERE d.id = e.dvds_id AND u.id = e.utilisateurs_id GROUP BY u.nom;

CREATE VIEW vue_emprunts
	AS SELECT
		u.nom as nom_emprunteur,
		u.prenom as prenom_emprunteur,
		d.titre as titre_dvd,
		e.date_emprunt as debut_emprunt,
		e.date_emprunt+15 as date_limite,
		e.date_retour as date_rendu
	FROM
		utilisateurs u,
		dvds d,
		emprunts e
	WHERE
		d.id = e.dvds_id
	AND
		u.id = e.utilisateurs_id;

CREATE VIEW vue_retardataires
	AS SELECT
		ve.nom_emprunteur as nom_emprunteur,
		ve.prenom_emprunteur as prenom_emprunteur,
		ve.titre_dvd as titre_dvd
	FROM
		vue_emprunts ve
	WHERE
		ve.date_rendu is NULL
	AND
		ve.date_limite < current_date;