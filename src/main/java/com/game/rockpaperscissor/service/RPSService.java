package com.game.rockpaperscissor.service;

import com.game.rockpaperscissor.dtos.RPSdto;
import com.game.rockpaperscissor.exception.RPSException;

public interface RPSService {

    String generateToken();

    void updateNewToken(String token);

    boolean validateToken(String token) throws RPSException;

    void validMove(String playersMove) throws RPSException;

    String playGame(String input);

    Integer calculateScore(String playersMove, String computersMove);

    String gameStatus();

    String playGameStrategically(String input);

    void validateUpdateAndReset(String token) throws RPSException;

    RPSdto populateResponse(String computersMove);

}
