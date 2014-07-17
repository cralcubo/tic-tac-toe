package com.chris.tictactoe.game.exceptions;

public class WaitYourTurnException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Wait your turn.";
	
	public WaitYourTurnException() {
		super(MESSAGE);
	}

}
