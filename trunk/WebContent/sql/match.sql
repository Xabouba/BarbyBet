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
,(1, 10, 1, 1, 1, 0, 0, '2015-04-19 21:00:00', false, false)
,(3, 14, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(4, 5, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(7, 15, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(9, 13, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(11, 2, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(12, 8, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(17, 18, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(19, 10, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(1, 6, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
,(20, 16, 1, 1, 38, 0, 0, '2015-05-23 21:00:00', false, false)
;