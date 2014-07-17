package com.chris.tictactoe.game.exceptions;

public class WrongShapeException extends Exception {
	private static final long serialVersionUID = 4425379695994103529L;
	
	private static final String MESSAGE = "Wrong shape set in player.";

	public WrongShapeException() {
		super(MESSAGE);
	}

}
