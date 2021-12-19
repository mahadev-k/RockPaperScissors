package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.dtos.RPSdto;
import com.game.rockpaperscissor.exception.RPSException;
import com.game.rockpaperscissor.exception.RPSExceptionConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RPSServiceImpl implements RPSService{

    private static final String basicCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";
    private static final List<String> possibeMoves = List.of(ROCK, PAPER, SCISSORS);
    private static final int tokenLength = 10;
    public static final int MAX_TURNs = 3;
    public static final String GAME_END_MESSAGE = "The game will end in ";
    private Optional<String> token = Optional.empty();
    private Integer playerScore = 0;
    private Integer computerScore = 0;
    private Integer totalTurns = 0;

    @Override
    public String generateToken() {

        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<tokenLength; i++){
            builder.append(basicCharacters.charAt(random.nextInt(basicCharacters.length())));
        }
        resetValues();

        return builder.toString();
    }

    @Override
    public void updateNewToken(String token) {
        this.token = Optional.ofNullable(token);
    }

    @Override
    public void validMove(String playersMove) throws RPSException {
        possibeMoves.stream().filter(moves -> moves.equalsIgnoreCase(playersMove)).findAny().orElseThrow(
                () -> new RPSException(RPSExceptionConstants.INVALID_MOVE.getType())
        );
    }

    @Override
    public boolean validateToken(String token) throws RPSException {

        if(this.token.isEmpty()){
            throw new RPSException(RPSExceptionConstants.NO_TOKEN.getType());
        }else if(!this.token.get().equalsIgnoreCase(token)){
            throw new RPSException(RPSExceptionConstants.INVALID_TOKEN.getType());
        }

        return true;
    }

    @Override
    public String playGame(String input) {

        Random random = new Random();

        int computersMove = random.nextInt(possibeMoves.size());

        return possibeMoves.get(computersMove);
    }

    @Override
    public Integer calculateScore(String playersMove, String computersMove) {

        if(computersMove.equalsIgnoreCase(ROCK) && playersMove.equalsIgnoreCase(SCISSORS)){
            computerScore++;
        }else if(computersMove.equalsIgnoreCase(ROCK) && playersMove.equalsIgnoreCase(PAPER)){
            playerScore++;
        }else if(computersMove.equalsIgnoreCase(PAPER) && playersMove.equalsIgnoreCase(ROCK)){
            computerScore++;
        }else if(computersMove.equalsIgnoreCase(PAPER) && playersMove.equalsIgnoreCase(SCISSORS)){
            playerScore++;
        }else if(computersMove.equalsIgnoreCase(SCISSORS) && playersMove.equalsIgnoreCase(ROCK)){
            playerScore++;
        } else if (computersMove.equalsIgnoreCase(SCISSORS) && playersMove.equalsIgnoreCase(PAPER)) {
            computerScore++;
        }

        return playerScore;
    }

    @Override
    public String gameStatus() {
        if(totalTurns != 3) return String.valueOf("In Progress");
        if(playerScore > computerScore)return String.valueOf("Player won");
        else if(playerScore < computerScore) return String.valueOf("Computer won");
        return String.valueOf("Game is tied");
    }

    @Override
    public RPSdto populateResponse(String computersMove) {

        return RPSdto.builder()
                .computerScore(computerScore)
                .playerScore(playerScore)
                .computersMove(computersMove)
                .status(gameStatus())
                .message(getMessageForPlayer(totalTurns))
                .totalTurns(totalTurns)
                .token(token.get())
                .build();

    }

    private static String getMessageForPlayer(Integer totalTurns){
        return String.valueOf(GAME_END_MESSAGE +(MAX_TURNs-totalTurns));
    }

    @Override
    public void validateUpdateAndReset(String token) throws RPSException {
        if(totalTurns< MAX_TURNs){
            validateToken(token);
            totalTurns++;
        }else{
           resetValues();
        }
    }

    private void resetValues(){
        playerScore = 0;
        computerScore = 0;
        totalTurns = 0;
        token = Optional.empty();
    }

    @Override
    public String playGameStrategically(String input) {

        if(input.equalsIgnoreCase(ROCK)){
            return PAPER;
        }else if(input.equalsIgnoreCase(PAPER)){
            return SCISSORS;
        }

        return ROCK;
    }
}
