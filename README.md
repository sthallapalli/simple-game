# simple-game
A simple game service using RESTful - NetEnt 


Create a RESTful service using which players can play a simple game described
below.

- The game should have the following rules:

- The player has an infinite amount of coins.

- The player bets 10 coins to play a normal game round.

- In any round (free or normal), the player has a 30% chance of winning back 20 coins.

- In any round (free or normal), the player also has a 10% chance of triggering a free round where
the player does not have to pay for bet. The free round works in the same way as a normal round except
it costs 0 coins. The free round should follow immediately after the normal round.

- The player can both win coins and free round at the same time.

# How to use
The following is the sample request to play the game.

POST - http://localhost:8080/simple-game/play

	Content-Type: application/json

	{
	  "playerId": "test",
	  "coins": 10,
	  "isFreeRound": false
	}


HATEOAS RESTful endpoints

GET - http://localhost:8080/simple-game/players - To see the players info.

GET - http://localhost:8080/simple-game/play-rounds - To see the play rounds info.
