CREATE TABLE User(
	id INT,
	name VARCHAR(50),
	PRIMARY KEY(id)
);

INSERT INTO User (id,name) VALUES (1,'范传奇');

CREATE TABLE Post(
	id INT,
	u_id INT,
	content VARCHAR(100),
	PRIMARY KEY(id)
);

INSERT INTO Post(id,u_id,content) VALUES (1,1,'今天早晨出了一件大事');
INSERT INTO Post(id,u_id,content) VALUES (2,1,'今天早晨出了一件大事');

CREATE TABLE Comment(
	id int,
	p_id INT,
	name VARCHAR(50),
	comment VARCHAR(100),
	PRIMARY KEY(id)
);

INSERT INTO Comment (id,p_id,name,comment) VALUES(1,1,'王克晶','出什么事了？');
INSERT INTO Comment (id,p_id,name,comment) VALUES(2,1,'刘苍松','Duang');


