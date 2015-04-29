DROP TABLE IF EXISTS Matchs;

CREATE TABLE Matchs (
	id INT AUTO_INCREMENT PRIMARY KEY,
	teamHId INT,
	teamAId INT,
	scoreH INT,
	scoreA INT,
	beginDate timestamp,
	isBegin boolean,
	isEnded boolean
);

INSERT INTO Matchs (teamHId, teamAId, scoreH, scoreA, beginDate, isBegin, isEnded)
VALUES (8, 3, 0, 0, '2015-04-19 14:00:00', false, false)
,(14, 4,  0, 0, '2015-04-19 17:00:00', false, false)
,(10, 1,  0, 0, '2015-04-19 21:00:00', false, false)
;