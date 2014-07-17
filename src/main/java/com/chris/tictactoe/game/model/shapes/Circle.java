package com.chris.tictactoe.game.model.shapes;

import com.chris.tictactoe.game.model.Coordinate;

public class Circle extends TicTacToeShape {

	public Circle(Coordinate coordinate) {
		super(coordinate);
	}

	public Circle() {
	}
	
	@Override
	public String toString() {
		return "Circle";
	}

}
