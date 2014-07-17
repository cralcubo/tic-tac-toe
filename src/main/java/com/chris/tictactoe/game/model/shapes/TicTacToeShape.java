package com.chris.tictactoe.game.model.shapes;

import com.chris.tictactoe.game.model.Coordinate;

public abstract class TicTacToeShape {
	private Coordinate coordinate;
	
	public TicTacToeShape(){}

	public TicTacToeShape(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}

}
