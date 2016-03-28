DROP TABLE IF EXISTS InfoEquipe;

CREATE TABLE InfoEquipe (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idTeam INT,
	idCompetition INT,
	groupName VARCHAR(255)
);


INSERT INTO InfoEquipe (idTeam, idCompetition, groupName)
VALUES (1, 1, "A")
,(2, 1, "A")
,(3, 1, "A")
,(4, 1, "A")
,(5, 1, "B")
,(6, 1, "B")
,(7, 1, "B")
,(8, 1, "B")
,(9, 1, "C")
,(10, 1, "C")
,(11, 1, "C")
,(12, 1, "C")
;
