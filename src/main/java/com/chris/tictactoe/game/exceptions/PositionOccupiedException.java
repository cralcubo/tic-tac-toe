package com.chris.tictactoe.game.exceptions;

public class PositionOccupiedException extends Exception {
	private static final long serialVersionUID = 1L;
	private final static String MESSAGE = "The position: Column[%s] Row[%s] is already occupied";

	public PositionOccupiedException(int x, int y) {
		super(String.format(MESSAGE, x, y));
	}

}
