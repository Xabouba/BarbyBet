DROP TABLE IF EXISTS Pronostics;

CREATE TABLE Pronostics (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idMatch INT,
	idUser INT,
	prono INT,
	credits INT,
	creditsWon INT
);