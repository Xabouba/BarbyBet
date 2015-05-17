DROP TABLE IF EXISTS Comments;

CREATE TABLE Comments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idUser INT,
	idMatch INT,
	comment VARCHAR(512) NOT NULL,
	dateComment timestamp
);

INSERT INTO Comments (idUser, idMatch, comment, dateComment)
VALUES (2, 3, "Bonjour", "2015-04-19 17:49:00")
,(3, 3, "Marseille a perdu ahaha", "2015-04-19 18:01:00")
,(2, 3, "Ce match est nul", "2015-04-19 18:01:00")
;