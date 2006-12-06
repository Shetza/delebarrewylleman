--
-- PostgreSQL database dump
--

-- Started on 2006-11-24 12:44:22 Paris, Madrid

SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1564 (class 1262 OID 16405)
-- Name: dvdthequeDelebarreWylleman; Type: DATABASE; Schema: -; Owner: lemeur
--

CREATE DATABASE "dvdthequeDelebarreWylleman" WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE "dvdthequeDelebarreWylleman" OWNER TO lemeur;

\connect "dvdthequeDelebarreWylleman"

SET client_encoding = 'UTF8';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1565 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- TOC entry 259 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: 
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1195 (class 1259 OID 16423)
-- Dependencies: 1529 4
-- Name: artistes; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE artistes (
    id serial NOT NULL,
    nom character varying(20),
    fonction character varying(20),
    CONSTRAINT artistes_fonction_check CHECK ((((fonction)::text = 'REALISATEUR'::text) OR ((fonction)::text = 'ACTEUR'::text)))
);


ALTER TABLE public.artistes OWNER TO lemeur;

--
-- TOC entry 1567 (class 0 OID 0)
-- Dependencies: 1201
-- Name: artistes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('artistes', 'id'), 1, false);


--
-- TOC entry 1200 (class 1259 OID 16433)
-- Dependencies: 4
-- Name: avis; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE avis (
    dvds_id integer NOT NULL,
    utilisateurs_id integer NOT NULL,
    avis text
);


ALTER TABLE public.avis OWNER TO lemeur;

--
-- TOC entry 1194 (class 1259 OID 16421)
-- Dependencies: 4
-- Name: categories; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE categories (
    id serial NOT NULL,
    nom character varying(20)
);


ALTER TABLE public.categories OWNER TO lemeur;

--
-- TOC entry 1568 (class 0 OID 0)
-- Dependencies: 1202
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('categories', 'id'), 1, false);


--
-- TOC entry 1196 (class 1259 OID 16425)
-- Dependencies: 4
-- Name: dvds; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE dvds (
    id serial NOT NULL,
    utilisateurs_id integer NOT NULL,
    categories_id integer NOT NULL,
    titre character varying(45),
    parution date
);


ALTER TABLE public.dvds OWNER TO lemeur;

--
-- TOC entry 1197 (class 1259 OID 16427)
-- Dependencies: 4
-- Name: dvds_has_artistes; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE dvds_has_artistes (
    dvds_id integer NOT NULL,
    artistes_id integer NOT NULL
);


ALTER TABLE public.dvds_has_artistes OWNER TO lemeur;

--
-- TOC entry 1569 (class 0 OID 0)
-- Dependencies: 1204
-- Name: dvds_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('dvds', 'id'), 1, false);


--
-- TOC entry 1198 (class 1259 OID 16429)
-- Dependencies: 4
-- Name: emprunts; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE emprunts (
    dvds_id integer NOT NULL,
    utilisateurs_id integer NOT NULL,
	debut date,
	fin date
);


ALTER TABLE public.emprunts OWNER TO lemeur;

--
-- TOC entry 1199 (class 1259 OID 16431)
-- Dependencies: 4
-- Name: reserves; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE reserves (
    dvds_id integer NOT NULL,
    utilisateurs_id integer NOT NULL
);


ALTER TABLE public.reserves OWNER TO lemeur;

--
-- TOC entry 1193 (class 1259 OID 16419)
-- Dependencies: 4
-- Name: utilisateurs; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE utilisateurs (
    id serial NOT NULL,
    nom character varying(20),
    prenom character varying(20),
    "login" character varying(20),
    "password" character varying(20)
);


ALTER TABLE public.utilisateurs OWNER TO lemeur;

--
-- TOC entry 1570 (class 0 OID 0)
-- Dependencies: 1203
-- Name: utilisateurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('utilisateurs', 'id'), 1, false);


--
-- TOC entry 1557 (class 0 OID 16423)
-- Dependencies: 1195
-- Data for Name: artistes; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1562 (class 0 OID 16433)
-- Dependencies: 1200
-- Data for Name: avis; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1556 (class 0 OID 16421)
-- Dependencies: 1194
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1558 (class 0 OID 16425)
-- Dependencies: 1196
-- Data for Name: dvds; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1559 (class 0 OID 16427)
-- Dependencies: 1197
-- Data for Name: dvds_has_artistes; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1560 (class 0 OID 16429)
-- Dependencies: 1198
-- Data for Name: emprunts; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1561 (class 0 OID 16431)
-- Dependencies: 1199
-- Data for Name: reserves; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1555 (class 0 OID 16419)
-- Dependencies: 1193
-- Data for Name: utilisateurs; Type: TABLE DATA; Schema: public; Owner: lemeur
--



--
-- TOC entry 1536 (class 2606 OID 16442)
-- Dependencies: 1195 1195
-- Name: artistes_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY artistes
    ADD CONSTRAINT artistes_pkey PRIMARY KEY (id);


--
-- TOC entry 1546 (class 2606 OID 16535)
-- Dependencies: 1200 1200 1200
-- Name: avis_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_pkey PRIMARY KEY (dvds_id, utilisateurs_id);


--
-- TOC entry 1534 (class 2606 OID 16451)
-- Dependencies: 1194 1194
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 1540 (class 2606 OID 16491)
-- Dependencies: 1197 1197 1197
-- Name: dvds_has_artistes_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_pkey PRIMARY KEY (dvds_id, artistes_id);


--
-- TOC entry 1538 (class 2606 OID 16467)
-- Dependencies: 1196 1196
-- Name: dvds_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_categories_id_fkey FOREIGN KEY (categories_id) REFERENCES categories(id);

--
-- TOC entry 1542 (class 2606 OID 16511)
-- Dependencies: 1198 1198 1198
-- Name: emprunts_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (dvds_id, utilisateurs_id);


--
-- TOC entry 1544 (class 2606 OID 16523)
-- Dependencies: 1199 1199 1199
-- Name: reserves_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY reserves
    ADD CONSTRAINT reserves_pkey PRIMARY KEY (dvds_id, utilisateurs_id);


--
-- TOC entry 1532 (class 2606 OID 16459)
-- Dependencies: 1193 1193
-- Name: utilisateurs_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY utilisateurs
    ADD CONSTRAINT utilisateurs_pkey PRIMARY KEY (id);


--
-- TOC entry 1553 (class 2606 OID 16524)
-- Dependencies: 1537 1196 1200
-- Name: avis_dvds_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);


--
-- TOC entry 1554 (class 2606 OID 16529)
-- Dependencies: 1193 1200 1531
-- Name: avis_utilisateurs_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY avis
    ADD CONSTRAINT avis_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);


--
-- TOC entry 1548 (class 2606 OID 16492)
-- Dependencies: 1197 1195 1535
-- Name: dvds_has_artistes_artistes_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_artistes_id_fkey FOREIGN KEY (artistes_id) REFERENCES artistes(id);


--
-- TOC entry 1547 (class 2606 OID 16482)
-- Dependencies: 1196 1197 1537
-- Name: dvds_has_artistes_dvds_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY dvds_has_artistes
    ADD CONSTRAINT dvds_has_artistes_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);


--
-- TOC entry 1549 (class 2606 OID 16500)
-- Dependencies: 1196 1537 1198
-- Name: emprunts_dvds_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);


--
-- TOC entry 1550 (class 2606 OID 16505)
-- Dependencies: 1198 1193 1531
-- Name: emprunts_utilisateurs_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);


--
-- TOC entry 1551 (class 2606 OID 16512)
-- Dependencies: 1199 1196 1537
-- Name: reserves_dvds_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY reserves
    ADD CONSTRAINT reserves_dvds_id_fkey FOREIGN KEY (dvds_id) REFERENCES dvds(id);


--
-- TOC entry 1552 (class 2606 OID 16517)
-- Dependencies: 1193 1531 1199
-- Name: reserves_utilisateurs_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY reserves
    ADD CONSTRAINT reserves_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);


--
-- TOC entry 1566 (class 0 OID 0)
-- Dependencies: 4
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2006-11-24 12:44:25 Paris, Madrid

--
-- PostgreSQL database dump complete
--

-- PROBLEME D'ENCODAGE
-- ERROR:  invalid byte sequence for encoding "UTF8": 0x87
-- HINT:  This error can also happen if the byte sequence 
-- does not match the encoding expected by the server, 
-- which is controlled by "client_encoding".

INSERT INTO utilisateurs VALUES(1, 'Delebarre', 'Johann', 'jojo', 'toto');
INSERT INTO utilisateurs VALUES(2, 'Wylleman', 'Julien', 'juju', 'titi');
INSERT INTO utilisateurs VALUES(3, 'Le Meur', 'Anne-Françoise', 'lemeur', 'lemeur');

INSERT INTO categories VALUES(1, 'Science-fiction');
INSERT INTO categories VALUES(2, 'Action');
INSERT INTO categories VALUES(3, 'Animation');
INSERT INTO categories VALUES(4, 'Aventure');
INSERT INTO categories VALUES(DEFAULT, 'Bollywood');
INSERT INTO categories VALUES(DEFAULT, 'Cinéma Asiatique');
INSERT INTO categories VALUES(DEFAULT, 'Comédie');
INSERT INTO categories VALUES(DEFAULT, 'Comédie Musicale');
INSERT INTO categories VALUES(DEFAULT, 'Comédie Romantique');
INSERT INTO categories VALUES(DEFAULT, 'Dessin animé');
INSERT INTO categories VALUES(DEFAULT, 'Documentaire');
INSERT INTO categories VALUES(DEFAULT, 'Drame');
INSERT INTO categories VALUES(DEFAULT, 'Espionnage');
INSERT INTO categories VALUES(DEFAULT, 'Fantastique');
INSERT INTO categories VALUES(DEFAULT, 'Films et Spectacles musicaux');
INSERT INTO categories VALUES(DEFAULT, 'Grands Classiques');
INSERT INTO categories VALUES(DEFAULT, 'Guerre');
INSERT INTO categories VALUES(DEFAULT, 'Historique');
INSERT INTO categories VALUES(DEFAULT, 'Horreur');
INSERT INTO categories VALUES(DEFAULT, 'Karaoké');
INSERT INTO categories VALUES(DEFAULT, 'Mangas');
INSERT INTO categories VALUES(DEFAULT, 'Policier');
INSERT INTO categories VALUES(DEFAULT, 'Romantique');
INSERT INTO categories VALUES(DEFAULT, 'Séries TV');
INSERT INTO categories VALUES(DEFAULT, 'Spectacles');
INSERT INTO categories VALUES(DEFAULT, 'Humour');
INSERT INTO categories VALUES(DEFAULT, 'Sports, Loisirs');
INSERT INTO categories VALUES(DEFAULT, 'Super Héros');
INSERT INTO categories VALUES(DEFAULT, 'Thriller');

INSERT INTO dvds VALUES(1, 1, 1, 'The Matrix', '1999-06-23');
INSERT INTO dvds VALUES(2, 1, 1, 'The Matrix Reloaded', '2003-05-16');
INSERT INTO dvds VALUES(3, 1, 1, 'The Matrix Revolutions', '2003-11-05');

INSERT INTO artistes VALUES(1, 'Keanu Reeves', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(1, 1);
INSERT INTO dvds_has_artistes VALUES(2, 1);
INSERT INTO dvds_has_artistes VALUES(3, 1);

INSERT INTO artistes VALUES(2, 'Laurence Fishburne', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(1, 2);
INSERT INTO dvds_has_artistes VALUES(2, 2);
INSERT INTO dvds_has_artistes VALUES(3, 2);

INSERT INTO artistes VALUES(3, 'Andy Wachowski', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(1, 3);
INSERT INTO dvds_has_artistes VALUES(2, 3);
INSERT INTO dvds_has_artistes VALUES(3, 3);

INSERT INTO artistes VALUES(4, 'Larry Wachowski', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(1, 4);
INSERT INTO dvds_has_artistes VALUES(2, 4);
INSERT INTO dvds_has_artistes VALUES(3, 4);

INSERT INTO avis VALUES(1, 1, 'MONUMENTAL!! Ce film restera pour moi le meilleur que j\'ai jamais vu !!! des scenes qui resterons cultes pour l\'eternité dans le monde du cinema du grand art du kung fu a rendre fou (...), une histoire a couper le souffle bref ce film ma litteralement scotché à mon siege !! A VOIR ET A AVOIR');
INSERT INTO avis VALUES(2, 1, 'Le meilleur volet de la trilogie des effets speciaux incroyable de tres bonne scene d\'action le film est exellent REGARDER LE');
INSERT INTO avis VALUES(3, 1, 'Avec Matrix revolution, les frères Wachowski boucle de la plus belle manière qui soit, la plus excitante et philosophique saga de science fiction de l\'histoire du cinéma sous un déluge d\'effets spéciaux toujours plus spectaculaire.');
