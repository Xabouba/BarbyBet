DROP TABLE IF EXISTS LinkUserGroup;

CREATE TABLE LinkUserGroup (
	groupId INT,
	userId INT,
	isAdmin BOOLEAN,
	userRank INT,
	userRankBeforeLastGame INT,
	dateUserAdded timestamp,
	FOREIGN KEY (groupId) REFERENCES Groups(id),
	FOREIGN KEY (userId) REFERENCES Users(id)
);
