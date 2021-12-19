##About

This app is about the game of rock paper scissors. #Springboot.

##Heroku

The app is successfully deployed in heroku and the accessible enpoints are

curl --location --request GET 'https://rock-paper-scissors-play.herokuapp.com/rps/start'
--response -> VKdqBtywGU

curl --location --request GET 'https://rock-paper-scissors-play.herokuapp.com/rps/v1/VKdqBtywGU/rock'
--response -> {
"token": "VKdqBtywGU",
"computersMove": "scissors",
"status": "In Progress",
"totalTurns": 1,
"message": "The game will end in 2",
"playerScore": 1,
"computerScore": 0
}

curl --location --request GET 'https://rock-paper-scissors-play.herokuapp.com/rps/v2/VKdqBtywGU/rock'
--response {
"token": "ZJaSlW6Buc",
"computersMove": "paper",
"status": "Computer won",
"totalTurns": 3,
"message": "The game will end in 0",
"playerScore": 0,
"computerScore": 3
}

## Invalid Requests

curl --location --request GET 'https://rock-paper-scissors-play.herokuapp.com/rps/v1/VKdqBtywGU/rocky'
--response {
"message": "Invalid Move",
"status": 400
}
There are other invalid req as well with respect to invalid token or trying to play without generating a token and all.

##Local host should be

curl --location --request GET 'http://localhost:8080/rps/start'
curl --location --request GET 'http://localhost:8080/rps/v1/5ho1FQMXy8/scissors'
curl --location --request GET 'http://localhost:8080/rps/v2/5ho1FQMXy8/scissors'