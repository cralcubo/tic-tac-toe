package com.chris.tictactoe.game;

import com.chris.tictactoe.game.model.Coordinate;

public enum GameCoordinates {
	A1(0,0), A2(0,1), A3(0,2),
	B1(1,0), B2(1,1), B3(1,2),
	C1(2,0), C2(2,1), C3(2,2);
	
	private int row;
	private int col;
	
	private GameCoordinates(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Coordinate getCoordinates(){
		return new Coordinate(row, col);
	}
	
}
