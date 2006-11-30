
DROP TABLE reserves;
DROP TABLE emprunts;
DROP TABLE avis;
DROP TABLE utilisateurs;
DROP TABLE dvds_has_artistes;
DROP TABLE dvds;
DROP TABLE categories;
DROP TABLE artistes;

\connect "postgres"
DROP DATABASE "dvdthequeDelebarreWylleman";
