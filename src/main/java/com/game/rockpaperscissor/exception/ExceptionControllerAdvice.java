package com.game.rockpaperscissor.exception;

import com.game.rockpaperscissor.dtos.RPSErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin
@PropertySource("classpath:exception.properties")
public class ExceptionControllerAdvice {

    @Autowired
    private Environment env;

    @ExceptionHandler(RPSException.class)
    public ResponseEntity<RPSErrorDto> handleRPSException(Exception e){
        return ResponseEntity.badRequest().body(RPSErrorDto.builder()
                .message(env.getProperty(e.getMessage()))
                .status(HttpStatus.BAD_REQUEST.value())
                .build()
        );
    }

}
