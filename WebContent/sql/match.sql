DROP TABLE IF EXISTS Matchs;

CREATE TABLE Matchs (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idSport INT,
	idCompetition INT,
	journee INT,
	teamHId INT,
	teamAId INT,
	scoreH INT,
	scoreA INT,
	beginDate timestamp,
	isBegin boolean,
	isEnded boolean
);

INSERT INTO Matchs (teamHId, teamAId, idSport, idCompetition, journee, scoreH, scoreA, beginDate, isBegin, isEnded)
VALUES (8, 3, 1, 1, 1, 0, 0, '2015-04-19 14:00:00', false, false)
,(14, 4, 1, 1, 1, 0, 0, '2015-04-19 17:00:00', false, false)
,(10, 1, 1, 1, 1, 0, 0, '2015-04-19 21:00:00', false, false)
,(11, 2, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
;