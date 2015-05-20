DROP TABLE IF EXISTS Competition;

CREATE TABLE Competition (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

INSERT INTO Competition (name)
VALUES ("French Ligue 1");