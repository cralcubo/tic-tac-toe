package com.chris.tictactoe.game.model;

import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class Player {
	
	private TicTacToeShape shape;
	
	private boolean played;
	
	public Player(TicTacToeShape shape) {
		this.shape = shape;
	}

	public TicTacToeShape getShape() {
		return shape;
	}

	public void setShape(TicTacToeShape shape) {
		this.shape = shape;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}
	

}
