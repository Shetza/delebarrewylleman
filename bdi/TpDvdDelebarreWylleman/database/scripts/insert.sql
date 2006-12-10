INSERT INTO utilisateurs VALUES(1, 'Delebarre', 'Johann', 'jojo', 'toto');
INSERT INTO utilisateurs VALUES(2, 'Wylleman', 'Julien', 'juju', 'titi');
INSERT INTO utilisateurs VALUES(3, 'Le Meur', 'Anne-Françoise', 'lemeur', 'lemeur');

INSERT INTO categories VALUES(DEFAULT, 'Science-fiction');
INSERT INTO categories VALUES(DEFAULT, 'Action');
INSERT INTO categories VALUES(DEFAULT, 'Animation');
INSERT INTO categories VALUES(DEFAULT, 'Aventure');
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
INSERT INTO categories VALUES (DEFAULT, 'Wu Xia Pian');

INSERT INTO dvds VALUES(1, 1, 1, 'The Matrix', '1999-06-23');
INSERT INTO dvds VALUES(2, 1, 1, 'The Matrix Reloaded', '2003-05-16');
INSERT INTO dvds VALUES(3, 1, 1, 'The Matrix Revolutions', '2003-11-05');
INSERT INTO dvds VALUES(4, 2, 30, 'The Blade', '1991-06-03');
INSERT INTO dvds VALUES(5, 2, 30, 'Seven Swords', '2005-10-10');
INSERT INTO dvds VALUES(6, 2, 14, 'Vampires', '1998-07-05');
INSERT INTO dvds VALUES(7, 3, 1, 'Ghosts of Mars', '2001-10-25');
INSERT INTO dvds VALUES(8, 3, 1, 'New York 1997', '1987-01-05');
INSERT INTO dvds VALUES(9, 2, 2, 'Tango & Cash', '1992-02-06');
INSERT INTO dvds VALUES(10, 3, 1, 'Soldier', '1997-02-15');

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

INSERT INTO artistes VALUES(5, 'Xin Xin Xiong', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(4, 5);

INSERT INTO artistes VALUES(6, 'Moses Chan', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(4, 6);

INSERT INTO artistes VALUES(7, 'Tsui Hark', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(4, 7);
INSERT INTO dvds_has_artistes VALUES(5, 7);

INSERT INTO artistes VALUES(8, 'John Carpenter', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(7, 8);
INSERT INTO dvds_has_artistes VALUES(8, 8);
INSERT INTO dvds_has_artistes VALUES(8, 8);

INSERT INTO artistes VALUES(9, 'James Woods', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(6, 9);

INSERT INTO artistes VALUES(10, 'Kurt Russel', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(8, 10);
INSERT INTO dvds_has_artistes VALUES(9, 10);
INSERT INTO dvds_has_artistes VALUES(10, 10);

INSERT INTO artistes VALUES(11, 'Lee Van Cleef', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(8, 11);

INSERT INTO artistes VALUES(12, 'Jason Statham', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(7, 12);

INSERT INTO artistes VALUES(13, 'Sheryl Lee', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(6, 13);

INSERT INTO artistes VALUES(14, 'Ice Cube', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(7, 14);

INSERT INTO artistes VALUES(15, 'Donnie Yen', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(5, 15);

INSERT INTO artistes VALUES(16, 'Leon Lai', 'ACTEUR');
INSERT INTO dvds_has_artistes VALUES(5, 16);

INSERT INTO artistes VALUES(17, 'Andrei Konchalovsky', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(9, 17);

INSERT INTO artistes VALUES(18, 'Paul W.S. Anderson', 'REALISATEUR');
INSERT INTO dvds_has_artistes VALUES(10, 18);

INSERT INTO avis VALUES(1, 1, 'MONUMENTAL!! Ce film restera pour moi le meilleur que j\'ai jamais vu !!! des scenes qui resterons cultes pour l\'eternité dans le monde du cinema du grand art du kung fu a rendre fou (...), une histoire a couper le souffle bref ce film ma litteralement scotché à mon siege !! A VOIR ET A AVOIR');
INSERT INTO avis VALUES(2, 1, 'Le meilleur volet de la trilogie des effets speciaux incroyable de tres bonne scene d\'action le film est exellent REGARDER LE');
INSERT INTO avis VALUES(3, 1, 'Avec Matrix revolution, les frères Wachowski boucle de la plus belle manière qui soit, la plus excitante et philosophique saga de science fiction de l\'histoire du cinéma sous un déluge d\'effets spéciaux toujours plus spectaculaire.');

INSERT INTO emprunts VALUES(3,2,0,NULL,'2006-12-06');
INSERT INTO emprunts VALUES(7,2,0,NULL,'2006-11-20');
INSERT INTO emprunts VALUES(8,2,0,NULL,'2006-12-03');
INSERT INTO emprunts VALUES(9,1,1,2,'2006-12-04');
INSERT INTO emprunts VALUES(10,1,0,NULL,'2006-11-05','2006-11-10');
