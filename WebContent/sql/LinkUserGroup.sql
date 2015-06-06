DROP TABLE IF EXISTS LinkUserGroup;

CREATE TABLE LinkUserGroup (
	user_id INT PRIMARY KEY,
	group_name VARCHAR(128) NOT NULL,
	status VARCHAR(128) NOT NULL
);
