DROP TABLE IF EXISTS Team;

CREATE TABLE Team (
	id INT AUTO_INCREMENT PRIMARY KEY,
	team VARCHAR(128) NOT NULL,
	sname VARCHAR(128) NOT NULL,
	img VARCHAR(128),
	idCompetition VARCHAR(128)
);

INSERT INTO Team (team, sname, img, idCompetition)
VALUES ("Saint-Etienne", "Saint-Etienne", "asse", "1")
,("Bastia", "Bastia", "bastia", "1")
,("Bordeaux", "Bordeaux", "bordeaux", "1")
,("Caen", "Caen", "caen", "1")
,("Evian TG", "Evian", "etg", "1")
,("Guingamp", "Guingamp", "guingamp", "1")
,("Lens", "Lens", "lens", "1")
,("Lille", "Lille", "lille", "1")
,("Lorient", "Lorient", "lorient", "1")
,("Lyon", "Lyon", "lyon", "1")
,("Marseille", "Marseille", "marseille", "1")
,("Metz", "Metz", "metz", "1")
,("Monaco", "Monaco", "monaco", "1")
,("Montpellier", "Montpellier", "montpellier", "1")
,("Nantes", "Nantes", "nantes", "1")
,("Nice", "Nice", "nice", "1")
,("PSG", "PSG", "psg", "1")
,("Reims", "Reims", "reims", "1")
,("Rennes", "Rennes", "rennes", "1")
,("Toulouse", "Toulouse", "toulouse", "1")
;