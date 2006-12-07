INSERT INTO categories VALUES (DEFAULT, 'Wu Xia Pian');

INSERT INTO dvds VALUES(4, 2, 30, 'The Blade', '1991-06-03');
INSERT INTO dvds VALUES(5, 2, 30, 'Seven Swords', '2005-10-10');
INSERT INTO dvds VALUES(6, 2, 14, 'Vampires', '1998-07-05');
INSERT INTO dvds VALUES(7, 3, 1, 'Ghosts of Mars', '2001-10-25');
INSERT INTO dvds VALUES(8, 3, 1, 'New York 1997', '1987-01-05');
INSERT INTO dvds VALUES(9, 2, 2, 'Tango & Cash', '1992-02-06');
INSERT INTO dvds VALUES(10, 3, 1, 'Soldier', '1997-02-15');

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

INSERT INTO emprunts VALUES(3,2,'2006-12-06');
INSERT INTO emprunts VALUES(7,2,'2006-11-20');
INSERT INTO emprunts VALUES(8,2,'2006-12-03');
INSERT INTO emprunts VALUES(9,1,'2006-12-04');
INSERT INTO emprunts VALUES(10,1,'2006-11-05','2006-11-10');