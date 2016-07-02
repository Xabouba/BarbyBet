DROP TABLE IF EXISTS InfoTeam;

CREATE TABLE InfoTeam (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idTeam INT,
	idCompetition INT,
	groupName VARCHAR(255)
);


INSERT INTO InfoTeam (idTeam, idCompetition, groupName)
VALUES (10, 1, "A")
,(14, 1, "A")
,(27, 1, "A")
,(28, 1, "A")
,(29, 1, "B")
,(30, 1, "B")
,(2, 1, "B")
,(11, 1, "B")
,(1, 1, "C")
,(4, 1, "C")
,(12, 1, "C")
,(31, 1, "C")
,(32, 1, "D")
,(6, 1, "D")
,(9, 1, "D")
,(3, 1, "D")
,(7, 1, "E")
,(8, 1, "E")
,(13, 1, "E")
,(15, 1, "E")
,(5, 1, "F")
,(33, 1, "F")
,(34, 1, "F")
,(35, 1, "F")
;
