DROP TABLE IF EXISTS Groups;

CREATE TABLE Groups (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(128),
	description TEXT,
	status INT NOT NULL,
	img VARCHAR(128),
	groupCreator INT,
	creationDate timestamp,
	FOREIGN KEY (groupCreator) REFERENCES Users(id)
);
