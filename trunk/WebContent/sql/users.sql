 CREATE TABLE Users(
    id INT AUTO_INCREMENT,
    username VARCHAR (128) NOT NULL,
    email VARCHAR (128) NOT NULL,
    password VARCHAR (128) NOT NULL,
    dateRegistration timestamp,
    coins INT DEFAULT 10000,
    PRIMARY KEY ( id )
    ) 