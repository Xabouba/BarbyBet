DROP TABLE IF EXISTS Comments;

CREATE TABLE Comments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idUser INT,
	comment VARCHAR(512) NOT NULL,
	dateComment timestamp
);

INSERT INTO Comments (idUser, comment, dateComment)
VALUES (1, "Bonjour", "2015-04-19 17:49:00")
,(2, "Marseille a perdu ahaha", "2015-04-19 18:01:00")
;