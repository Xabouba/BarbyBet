DROP TABLE IF EXISTS Team;

CREATE TABLE Team (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(128) NOT NULL,
	img VARCHAR(128),
	idWebService INT
);

/*
INSERT INTO Team (team, sname, img)
VALUES ("Saint-Etienne", "Saint-Etienne", "asse")
,("Bastia", "Bastia", "bastia")
,("Bordeaux", "Bordeaux", "bordeaux")
,("Caen", "Caen", "caen")
,("Evian TG", "Evian", "etg")
,("Guingamp", "Guingamp", "guingamp")
,("Lens", "Lens", "lens")
,("Lille", "Lille", "lille")
,("Lorient", "Lorient", "lorient")
,("Lyon", "Lyon", "lyon")
,("Marseille", "Marseille", "marseille")
,("Metz", "Metz", "metz")
,("Monaco", "Monaco", "monaco")
,("Montpellier", "Montpellier", "montpellier")
,("Nantes", "Nantes", "nantes")
,("Nice", "Nice", "nice")
,("PSG", "PSG", "psg")
,("Reims", "Reims", "reims")
,("Rennes", "Rennes", "rennes")
,("Toulouse", "Toulouse", "toulouse")
;
*/