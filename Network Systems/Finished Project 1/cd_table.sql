CREATE DATABASE student13_cds;  /* Qualify with your username ex. chona_cds when on the Cloud*/
USE student13_cds;

CREATE TABLE track(
trkid		INTEGER auto_increment,
trknum		INTEGER,
trktitle	VARCHAR(50),
trklength	DECIMAL(4,2),
	PRIMARY KEY (trkid));
    
INSERT INTO track (trknum, trktitle, trklength) VALUES      /* multiple*/ (1, 'Giant Steps', 4.72), (2, 'Cousin Mary', 5.75), (3, 'Countdown', 2.35), (4, 'Spiral', 5.93), (5, 'Syeeda''s song flute', 7), /* Can substitute “Syeeda’s song flute”*/
(6, 'Naima', 4.35),
(7, 'Mr. P.C.', 6.95),
(8, 'Giant Steps', 3.67),
(9, 'Naima', 4.45),
(10, 'Cousin Mary', 5.9),
(11, 'Countdown', 4.55),
(12, 'Syeeda''s song flute', 7.03),
(1, 'Stomp of King Porter', 3.2),
(2, 'Sing a Study in Brown', 2.85),
(3, 'Sing Moten''s Swing', 3.6),
(4, 'A-tisket, A-tasket', 2.95),
(5, 'I Know Why', 3.57),
(6, 'Sing You Sinners', 2.75),
(7, 'Java Jive', 2.85),
(8, 'Down South Camp Meetin''', 3.25),
(9, 'Topsy', 3.23),
(10, 'Clouds', 7.2),
(11, 'Skyliner', 3.18),
(12, 'It''s Good Enough to Keep', 3.18),
(13, 'Choo Choo Ch'' Boogie', 3);

