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
-- TOC entry 1193 (class 1259 OID 16419)
-- Dependencies: 4
-- Name: utilisateurs; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE utilisateurs (
    id serial NOT NULL,
    nom character varying(20),
    prenom character varying(20),
    login character varying(20),
    password character varying(20)
);


ALTER TABLE public.utilisateurs OWNER TO lemeur;

--
-- TOC entry 1570 (class 0 OID 0)
-- Dependencies: 1203
-- Name: utilisateurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval(pg_catalog.pg_get_serial_sequence('utilisateurs', 'id'), 1, false);

--
-- TOC entry 1196 (class 1259 OID 16425)
-- Dependencies: 4
-- Name: dvds; Type: TABLE; Schema: public; Owner: lemeur; Tablespace: 
--

CREATE TABLE dvds (
    id serial NOT NULL,
    utilisateurs_id integer,-- NOT NULL, IMPOSSIBLE CF. README
    categories_id integer,-- NOT NULL, IMPOSSIBLE CF. README
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
	prolonge integer NOT NULL DEFAULT 0,
	loueur_suivant_id integer,
	date_emprunt date,
	date_retour date
);


ALTER TABLE public.emprunts OWNER TO lemeur;

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
-- TOC entry 1532 (class 2606 OID 16459)
-- Dependencies: 1193 1193
-- Name: utilisateurs_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY utilisateurs
    ADD CONSTRAINT utilisateurs_pkey PRIMARY KEY (id);


--
-- TOC entry 1538 (class 2606 OID 16467)
-- Dependencies: 1196 1196
-- Name: dvds_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--


ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_categories_id_fkey FOREIGN KEY (categories_id) REFERENCES categories(id);

ALTER TABLE ONLY dvds
    ADD CONSTRAINT dvds_utilisateurs_id_fkey FOREIGN KEY (utilisateurs_id) REFERENCES utilisateurs(id);

--
-- TOC entry 1542 (class 2606 OID 16511)
-- Dependencies: 1198 1198 1198
-- Name: emprunts_pkey; Type: CONSTRAINT; Schema: public; Owner: lemeur; Tablespace: 
--

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_pkey PRIMARY KEY (dvds_id, utilisateurs_id, date_emprunt);


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
-- TOC entry 1550 (class 2606 OID 16505)
-- Dependencies: 1198 1193 1531
-- Name: emprunts_utilisateurs_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: lemeur
--

ALTER TABLE ONLY emprunts
    ADD CONSTRAINT emprunts_reserve_utilisateurs_id_fkey FOREIGN KEY (reserve_utilisateurs_id) REFERENCES utilisateurs(id);


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
