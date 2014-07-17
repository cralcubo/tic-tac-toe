package com.chris.tictactoe.game;

import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;


public class GameManager {
	
	private PlayerManager circlePlayer;
	private PlayerManager crossPlayer;
	
	private Matrix matrix;
	
	public GameManager(PlayerManager circlePlayer, PlayerManager crossPlayer){
		matrix = new Matrix();
		this.circlePlayer = circlePlayer;
		this.crossPlayer = crossPlayer;
	}
	
	public void play(PlayerManager playerManager) throws PositionOccupiedException, WaitYourTurnException{
		if(!playerManager.hasPlayed()){
			matrix.writeElement(playerManager.getShape());
			gameTurnsSetter(playerManager);
		} else {
			throw new WaitYourTurnException();
		}
	}

	private void gameTurnsSetter(PlayerManager playerManager) {
		if(playerManager.equals(circlePlayer)){
			circlePlayer.setPlayed(true);
			crossPlayer.setPlayed(false);
		} else {
			circlePlayer.setPlayed(false);
			crossPlayer.setPlayed(true);
		}
	}

	public PlayerManager checkWinner(){
		
		TicTacToeShape rowWinnerShape = matrix.checkRows();
		if(rowWinnerShape != null){
			return verifyWinnerShape(rowWinnerShape);
		}
		
		TicTacToeShape colWinnerShape = matrix.checkCols();
		if(colWinnerShape != null){
			return verifyWinnerShape(colWinnerShape);
		}
		
		TicTacToeShape diagonalWinnerShape = matrix.checkDiagonals();
		if(diagonalWinnerShape != null){
			return verifyWinnerShape(diagonalWinnerShape);
		}
		
		return null;
	}
	
	private PlayerManager verifyWinnerShape(TicTacToeShape winnerShape){
		if(winnerShape != null){
			if(winnerShape instanceof Circle){
				return circlePlayer;
			} else {
				return crossPlayer;
			}
		}
		return null;
	}
	
	public void setMatrix(Matrix matrix){
		this.matrix = matrix;
	}

	public boolean isTie() {
		return matrix.isFull();
	}

	

}
