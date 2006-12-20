
SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;


------------------------------------
-- Creations de la base de donnes --
------------------------------------

--CREATE DATABASE "dvdthequeDelebarreWylleman" WITH TEMPLATE = template0 ENCODING = 'UTF8';
ALTER DATABASE "dvdthequeDelebarreWylleman" OWNER TO lemeur;

\connect "dvdthequeDelebarreWylleman"

SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;

COMMENT ON SCHEMA public IS 'Standard public schema';

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;


--------------------------
-- Creations des tables --
--------------------------

-- Table artistes --
CREATE TABLE artistes (
    id serial NOT NULL,
    nom character varying(20),
    fonction character varying(20),
    CONSTRAINT artistes_fonction_check CHECK ((((fonction)::text = 'REALISATEUR'::text) OR ((fonction)::text = 'ACTEUR'::text)))
);

ALTER TABLE public.artistes OWNER TO lemeur;

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('artistes', 'id'), 1, false);

-- Table avis --
CREATE TABLE avis (
    dvds_id integer NOT NULL,
    utilisateurs_id integer NOT NULL,
    avis text
);

ALTER TABLE public.avis OWNER TO lemeur;

-- Table categories --
CREATE TABLE categories (
    id serial NOT NULL,
    nom character varying(20)
);

ALTER TABLE public.categories OWNER TO lemeur;

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('categories', 'id'), 1, false);

-- Table utilisateurs --
CREATE TABLE utilisateurs (
    id serial NOT NULL,
    nom character varying(20),
    prenom character varying(20),
    login character varying(20),
    password character varying(20)
);

ALTER TABLE public.utilisateurs OWNER TO lemeur;

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('utilisateurs', 'id'), 1, false);

-- Table dvds --
CREATE TABLE dvds (
    id serial NOT NULL,
    utilisateurs_id integer,-- NOT NULL, IMPOSSIBLE CF. README
    categories_id integer,-- NOT NULL, IMPOSSIBLE CF. README
    titre character varying(45),
    parution date
);

ALTER TABLE public.dvds OWNER TO lemeur;

-- Table dvd_has_artistes --
CREATE TABLE dvds_has_artistes (
    dvds_id integer NOT NULL,
    artistes_id integer NOT NULL
);

ALTER TABLE public.dvds_has_artistes OWNER TO lemeur;

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('dvds', 'id'), 1, false);

-- Table emprunts --
CREATE TABLE emprunts (
    dvds_id integer NOT NULL,
    utilisateurs_id integer NOT NULL,
	date_emprunt date NOT NULL,
	date_retour date,
	prolonge integer,
	loueur_suivant_id integer,
	date_reserve2emprunt date
);

ALTER TABLE public.emprunts OWNER TO lemeur;


-----------------------------------------------
-- affectation des clef primaires des tables --
-----------------------------------------------

-- Table artistes --
ALTER TABLE ONLY artistes
    ADD CONSTRAINT artistes_pkey PRIMARY KEY (id);

-- Table avis --
ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_pkey PRIMARY KEY (dvds_id, utilisateurs_id);

-- Table categories --
ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);

-- Table dvds_has_artistes --
ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_pkey PRIMARY KEY (dvds_id, artistes_id);

-- Table utilisateurs --
ALTER TABLE ONLY utilisateurs
    ADD CONSTRAINT utilisateurs_pkey PRIMARY KEY (id);

-- Table dvds --
ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_pkey PRIMARY KEY (id);

-- Table emprunts --
ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (dvds_id, utilisateurs_id, date_emprunt);


------------------------------------------------
-- affectation des clef etrangeres des tables --
------------------------------------------------

-- Table dvds --
ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_categories_id_fkey FOREIGN KEY (categories_id) REFERENCES categories(id);

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);

-- Table avis --
ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);

ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);

-- Table dvds_has_artistes --
ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_artistes_id_fkey FOREIGN KEY (artistes_id) REFERENCES artistes(id);

ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);

-- Table emprunts --
ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_loueur_suivant_id_fkey FOREIGN KEY (loueur_suivant_id) REFERENCES utilisateurs(id);


------------------------------------------
-- Mise en place des droits des tablees --
------------------------------------------

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
