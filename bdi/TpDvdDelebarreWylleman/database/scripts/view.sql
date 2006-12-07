-- psql -f view.sql dvdthequeDelebarreWylleman
-- script de creation des vues de la dvdtheque

CREATE VIEW vue_emprunts
	AS SELECT
		u.nom as nom_emprunteur,
		u.prenom as prenom_emprunteur,
		d.titre as titre_dvd,
		e.date_emprunt as debut_emprunt,
		x.date_limite as date_limite,
		e.date_retour as date_rendu
	FROM
		utilisateurs u,
		dvds d,
		emprunts e,
		(SELECT e.date_emprunt+15 as date_limite FROM emprunts e) x
	WHERE
		d.id = e.dvds_id
	AND
		u.id = e.utilisateurs_id;
