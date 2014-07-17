package com.chris.tictactoe.game.exceptions;

public class GameOverException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public GameOverException(String message) {
		super(message);
	}

}
