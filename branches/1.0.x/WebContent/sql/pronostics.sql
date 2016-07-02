DROP TABLE IF EXISTS Pronostics;

CREATE TABLE Pronostics (
	id INT AUTO_INCREMENT PRIMARY KEY,
	idMatch INT,
	idUser INT,
	scoreHome INT,
	scoreAway INT,
	prono INT,
	credits INT,
	creditsWon INT
);