package com.chris.tictactoe.game.model.shapes;

import com.chris.tictactoe.game.model.Coordinate;

public class Cross extends TicTacToeShape {
	
	public Cross(Coordinate coordinate) {
		super(coordinate);
	}

	public Cross() {
	}
	
	@Override
	public String toString() {
		return "Cross";
	}

}
