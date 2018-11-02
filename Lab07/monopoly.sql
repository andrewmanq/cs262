--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerGame CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS Property CASCADE;
DROP TABLE IF EXISTS Building CASCADE;

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer,
	cash integer,
	location integer
	);

CREATE TABLE Property (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	propertyIndex integer
	);

CREATE TABLE Building (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	propertyIndex integer,
	buildingType varchar(50)
);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;
GRANT SELECT ON Building TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00, 100, 3);
INSERT INTO PlayerGame VALUES (1, 2, 0.00, 250, 6);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00, 1000, 22);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00, 800, 15);
INSERT INTO PlayerGame VALUES (2, 2, 0.00, 1100, 7);
INSERT INTO PlayerGame VALUES (2, 3, 500.00, 600, 29);
INSERT INTO PlayerGame VALUES (3, 2, 0.00, 900, 4);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00, 150, 1);

INSERT INTO Property VALUES (1, 3, 5);
INSERT INTO Property VALUES (1, 3, 22);
INSERT INTO Property VALUES (1, 3, 30);
INSERT INTO Property VALUES (1, 3, 10);
INSERT INTO Property VALUES (2, 1, 7);
INSERT INTO Property VALUES (2, 1, 16);

INSERT INTO Building VALUES (1, 3, 7, 'house');
INSERT INTO Building VALUES (2, 2, 7, 'hotel');
INSERT INTO Building VALUES (3, 3, 7, 'house');
INSERT INTO Building VALUES (3, 2, 7, 'hotel');