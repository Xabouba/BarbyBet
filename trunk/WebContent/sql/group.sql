DROP TABLE IF EXISTS Groups;

CREATE TABLE Groups (
	name VARCHAR(128) PRIMARY KEY,
	description VARCHAR(1024),
	status VARCHAR(128) NOT NULL,
	img VARCHAR(128),
	creation_date timestamp
);
