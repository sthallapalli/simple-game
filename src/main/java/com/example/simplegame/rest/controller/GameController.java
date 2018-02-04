package com.example.simplegame.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplegame.domain.PlayRequest;
import com.example.simplegame.domain.PlayResponse;
import com.example.simplegame.service.GameService;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping(path = "/play", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayResponse> play(@RequestBody(required = true) PlayRequest betInfo) {
		PlayResponse play = this.gameService.play(betInfo);
		return new ResponseEntity<PlayResponse>(play, HttpStatus.OK);
	}

}
