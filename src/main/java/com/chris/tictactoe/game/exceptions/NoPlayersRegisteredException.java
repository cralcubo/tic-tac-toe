package com.chris.tictactoe.game.exceptions;

public class NoPlayersRegisteredException extends Exception {
	private static final long serialVersionUID = -8735431092877081216L;
	private static final String MESSAGE = "No players were registered to start the game.";
	
	public NoPlayersRegisteredException() {
		super(MESSAGE);
	}

}
