package com.chris.tictactoe.game;

import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.Cross;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class Matrix {
	private enum MatrixDirection{VERTICAL, HORIZONTAL, DIAGONAL}
	
	private final static int MATRIX_DIMENSION = 3;
	
	private TicTacToeShape[][] arrayMatrix;
	
	
	public Matrix(){
		setArrayMatrix(new TicTacToeShape[MATRIX_DIMENSION][MATRIX_DIMENSION]);
	}
	
	public void writeElement(TicTacToeShape element) throws PositionOccupiedException{
		int x = element.getCoordinate().getX();
		int y = element.getCoordinate().getY();
		
		if(getArrayMatrix()[x][y] == null){
			getArrayMatrix()[x][y] = element;
		} else {
			throw new PositionOccupiedException(x, y);
		}
	}
	
	public TicTacToeShape checkRows(){
		return linesChecker(MatrixDirection.HORIZONTAL);
	}
	
	public TicTacToeShape checkCols() {
		return linesChecker(MatrixDirection.VERTICAL);
	}
	
	public TicTacToeShape checkDiagonals() {
		return linesChecker(MatrixDirection.DIAGONAL);
	}
	
	private TicTacToeShape linesChecker(MatrixDirection direction){
		int crossCounter = 0;
		int circleCounter = 0;
		
		for(int i = 0; i < arrayMatrix.length; i++){
			for(int j = 0; j < arrayMatrix[i].length; j++){
				TicTacToeShape shape;
				if(direction == MatrixDirection.VERTICAL){
					shape = getArrayMatrix()[j][i];
				} else if( direction == MatrixDirection.HORIZONTAL) {
					shape = getArrayMatrix()[i][j];
				} else {
					if(i == 0){
						shape = arrayMatrix[j][j];
					} else {
						shape = arrayMatrix[j][(MATRIX_DIMENSION - 1) - j];
					}
				}
				  
				if(shape != null){
					if(shape instanceof Circle){
						circleCounter++;
					}else{
						crossCounter++;
					}
				}
			}
			
			if(circleCounter == 3){
				return new Circle();
			}
			
			if(crossCounter == 3){
				return new Cross();
			}
			
			crossCounter = 0;
			circleCounter = 0;
			
			if(direction == MatrixDirection.DIAGONAL && i > 0){
				break;
			}
		}
		
		return null;
	}
	
	public boolean isFull() {
		for(int i = 0; i < arrayMatrix.length; i++){
			for(int j = 0 ; j < arrayMatrix[i].length ; j++){
				if(arrayMatrix[i][j] == null){
					return false;
				}
			}
		}
		
		return true;
	}

	public TicTacToeShape[][] getArrayMatrix() {
		return arrayMatrix;
	}

	public void setArrayMatrix(TicTacToeShape[][] arrayMatrix) {
		this.arrayMatrix = arrayMatrix;
	}

}
