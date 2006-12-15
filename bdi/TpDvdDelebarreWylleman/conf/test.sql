-- Cas standard: l'utilisateur 1 emprunte le dvd 2.
-- Remarque: La date d'emprunt (2006-12-06) sera modifie et prendra la valeur de la date du jour (trigger_emprunt_possible).
INSERT INTO emprunts VALUES(2, 1, '2006-12-06', NULL, NULL, NULL, NULL);
SELECT * FROM emprunts WHERE dvds_id = 2 AND utilisateurs_id = 1 AND date_retour IS NULL;

-- Cas standard: l'utilisateur 1 rend le dvd 2.
-- Remarque: La date de retour (2006-12-06) sera modifie et prendra la valeur de la date du jour (trigger_rendu_est_aujourdhui).
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 2 AND utilisateurs_id = 1 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 2 AND utilisateurs_id = 1;

-- Cas d'erreur: l'utilisateur 2 emprunte le dvd 10.
-- Erreur: L'emprunt est impossible puisque l'utilisateur a deja emprunte 3 dvds.
INSERT INTO emprunts VALUES(10, 2, '2006-12-06', NULL, NULL, NULL, NULL);

-- Cas d'erreur: l'utilisateur 2 reserve le dvd 2.
-- Erreur: La reservation est impossible puisque le dvd est deja reserve.
UPDATE emprunts SET loueur_suivant_id = 2 WHERE dvds_id = 2 AND date_retour IS NULL;

------------------------------
-- Exemple de reservation 1 --
------------------------------

-- L'utilisateur 1 reserve le dvd 3 (actuellement emprunte par l'utilisateur 2).
UPDATE emprunts SET loueur_suivant_id = 1 WHERE dvds_id = 3 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 3 AND loueur_suivant_id = 1;

-- L'utilisateur 2 rend le dvd 3.
-- Remarque 1: Le dvd 3 sera automatiquement emprunte par l'utilisateur 1.
-- Remarque 2: La date d'emprunt automatique (date_reserve2emprunt) sera affectee avec la date du jour.
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 3 AND utilisateurs_id = 2 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 3;

------------------------------
-- Exemple de reservation 2 --
------------------------------

-- L'utilisateur 2 emprunte le dvd 4 et atteint le seuil de 3 dvds emprunte.
INSERT INTO emprunts VALUES(4, 2, '2006-12-06', NULL, NULL, NULL, NULL);
SELECT * FROM emprunts WHERE utilisateurs_id = 2 AND date_retour IS NULL;

-- L'utilisateur 2 reserve le dvd 9 (actuellement emprunte par l'utilisateur 1).
UPDATE emprunts SET loueur_suivant_id = 2 WHERE dvds_id = 9 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE utilisateurs_id = 2 AND date_retour IS NULL OR loueur_suivant_id = 2;

-- L'utilisateur 1 rend le dvd 9.
-- Remarque 1: Le dvd 9 ne peut pas etre automatiquement emprunte par l'utilisateur 2 puisqu'il a atteint le seuil de 3 dvds emprunte.
-- Remarque 2: La date d'emprunt automatique (date_reserve2emprunt) reste vierge montrant une reservation en attente.
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 9 AND utilisateurs_id = 1 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 9 AND utilisateurs_id = 1 UNION SELECT * FROM emprunts WHERE dvds_id = 0 AND utilisateurs_id = 2 AND date_retour IS NULL;

-- L'utilisateur 3 veut emprunter le dvd 9 qui a ete rendu.
-- Erreur: L'emprunt est impossible puisque le dvd est reserve par l'utilisateur 2, même s'il ne l'a toujours pas emprunte.
INSERT INTO emprunts VALUES(9, 3, '2006-12-06', NULL, NULL, NULL, NULL);

-- L'utilisateur 2 rend un dvd quelconque, le 4 par exemple.
-- Remarque 1: Le dvd 9 (la plus ancienne reserve) qui lui est toujours reserve est automatiquement emprunte par l'utilisateur 2.
-- Remarque 2: La date d'emprunt automatique (date_reserve2emprunt) sera affectee avec la date du jour.
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 4 AND utilisateurs_id = 2 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 9 AND utilisateurs_id = 2;

--------------------
INSERT INTO emprunts VALUES(2, 1, '2006-12-06', NULL, NULL, NULL, NULL);
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 2 AND utilisateurs_id = 1 AND date_retour IS NULL;

UPDATE emprunts SET loueur_suivant_id = 1 WHERE dvds_id = 3 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 3 AND loueur_suivant_id = 1;
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 3 AND utilisateurs_id = 2 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 3;

INSERT INTO emprunts VALUES(4, 2, '2006-12-06', NULL, NULL, NULL, NULL);
SELECT * FROM emprunts WHERE utilisateurs_id = 2 AND date_retour IS NULL;
UPDATE emprunts SET loueur_suivant_id = 2 WHERE dvds_id = 9 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE utilisateurs_id = 2 AND date_retour IS NULL OR loueur_suivant_id = 2;
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 9 AND utilisateurs_id = 1 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 9 AND utilisateurs_id = 1 UNION SELECT * FROM emprunts WHERE dvds_id = 9 AND utilisateurs_id = 2 AND date_retour IS NULL;
UPDATE emprunts SET date_retour = '2006-12-06' WHERE dvds_id = 4 AND utilisateurs_id = 2 AND date_retour IS NULL;
SELECT * FROM emprunts WHERE dvds_id = 9 AND utilisateurs_id = 2;
