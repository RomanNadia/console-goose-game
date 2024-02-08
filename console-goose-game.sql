Create database gooseGame;
Use gooseGame;

create table food (
	id INT NOT NULL auto_increment primary key,
	foodName VARCHAR(30),
	nutrition int
);

insert into food
value (1, "cookie", 10),
(2, "marshmallow", 5),
(3, "chocolate", 15);

create table activities (
	id INT NOT NULL auto_increment primary key,
	activityName VARCHAR(30),
	satisfaction int
);

insert into activities
value (1, "swiming", 5),
(2, "terrorizeing the neighbors", 15),
(3, "elegant honking", 10);


create table detergent (
	id INT NOT NULL auto_increment primary key,
	detergentName VARCHAR(30),
	washingLevel int
);

insert into detergent
value (1, "shampoo", 10),
(2, "water", 5),
(3, "soap", 15);

create table sessions (
	sessionName VARCHAR(30) NOT NULL primary key
);

insert into sessions
value ('DEFAULT');

create table hat (
	id INT NOT NULL auto_increment primary key,
	hatName VARCHAR(30),
	hungerBonus int,
	hygieneBonus int,
	satisfactionBonus int,
    sessionName VARCHAR(30),
    FOREIGN KEY (sessionName) REFERENCES sessions(sessionName)
);

insert into hat
value (1, "no hat", 0, 0, 0, 'DEFAULT'),
(2, "wizard hat", 5, 5, 5, 'DEFAULT'),
(3, "birsday hat", 2, -2, 10, 'DEFAULT');

create table goose (
	id INT NOT NULL auto_increment primary key,
	gooseName VARCHAR(30),
	maxHunger int,
	currentHunger int,
	maxHygiene int,
	currentHygiene int,
	maxSatisfaction int,
	currentSatisfaction int,
	maxHealth int,
	currentHealth int,
	lastUpdateTime BIGINT,
    gooseCoins int DEFAULT 100, 
	currentHatId int DEFAULT 1, 
    sessionName VARCHAR(30),
    FOREIGN KEY (currentHatId) REFERENCES hat(id),
    FOREIGN KEY (sessionName) REFERENCES sessions(sessionName)
);

CREATE TABLE hat_goose (
  gooseId INT,
  hatId INT,
  FOREIGN KEY (gooseId) REFERENCES goose(id),
  FOREIGN KEY (hatId) REFERENCES hat(id),
  primary key (gooseId, hatId)
);