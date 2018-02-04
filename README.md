# simple-game
A simple game service using RESTful 

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
