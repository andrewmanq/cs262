--EXERCISE 8.1
--sort games by ascending time
SELECT *
FROM Game
ORDER BY time ASC;
--selects games in the past week
SELECT *
FROM Game
WHERE time > '2018-10-19 01:00:00';
--finds players with no name
SELECT *
FROM Player
WHERE name IS NOT NULL;
--finds the id of players with scores above 2000
SELECT playerID
FROM PlayerGame
WHERE score > 2000;
--finds all players with a gmail account
SELECT *
FROM Player
WHERE emailAddress LIKE '%@gmail.edu%';


--EXERCISE 8.2
--finds all scores from dogbreath
SELECT score
FROM Player, PlayerGame
WHERE Player.ID = PlayerGame.playerID
	AND Player.name = 'Dogbreath';
--finds the winner of the game that happened on 2006-06-28 13:20:00
SELECT Player.name
FROM Player, PlayerGame, Game
WHERE Game.time = '2006-06-28 13:20:00'
	AND PlayerGame.gameID = Game.ID
	AND Player.ID = PlayerGame.playerID
	ORDER BY PlayerGame.score DESC
	LIMIT 1;

--ANSWER C: P1.ID < P2.ID ensures that the two players are not the same player. Otherwise, it would compare a player to itself.
--ANSWER D: Joining a table to itself makes it possible to compare two items from the same table.