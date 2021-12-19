package com.game.rockpaperscissor.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RPSdto {

    private String token;
    private String computersMove;
    private String status;
    private Integer totalTurns;
    private String message;
    private Integer playerScore;
    private Integer computerScore;

}
