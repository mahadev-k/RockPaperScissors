package com.game.rockpaperscissor.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RPSErrorDto {

    private String message;
    private int status;

}
