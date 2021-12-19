package com.game.rockpaperscissor;

import com.game.rockpaperscissor.dtos.RPSdto;
import com.game.rockpaperscissor.exception.RPSException;
import com.game.rockpaperscissor.service.RPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class RPSControllerV1 {

    @Autowired
    private RPSService rpsService;

    @GetMapping("start")
    public ResponseEntity<String> getToken(){

        String newToken = rpsService.generateToken();
        rpsService.updateNewToken(newToken);

        return ResponseEntity.ok(newToken);

    }

    @GetMapping("v1/{token}/{playersMove}")
    public ResponseEntity<RPSdto> playGame(@PathVariable String token, @PathVariable String playersMove) throws RPSException {

        rpsService.validMove(playersMove);
        rpsService.validateUpdateAndReset(token);
        String computersMove =  rpsService.playGame(playersMove);
        rpsService.calculateScore(playersMove, computersMove);

        return ResponseEntity.ok(rpsService.populateResponse(computersMove));

    }

    @GetMapping("v2/{token}/{playersMove}")
    public ResponseEntity<RPSdto> playGameStrategically(@PathVariable String token, @PathVariable String playersMove) throws RPSException {


        rpsService.validMove(playersMove);
        rpsService.validateUpdateAndReset(token);
        String computersMove =  rpsService.playGameStrategically(playersMove);
        rpsService.calculateScore(playersMove, computersMove);

        return ResponseEntity.ok(rpsService.populateResponse(computersMove));

    }

}
