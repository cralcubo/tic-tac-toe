package com.chris.tictactoe.game;

import java.util.HashMap;
import java.util.Map;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.exceptions.WrongShapeException;
import com.chris.tictactoe.game.model.Player;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.Cross;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class TicTacToeImpl implements TicTacToe {
	private enum PlayerShape{CIRCLE, CROSS}

	private static final String TIE_MESSAGE = "Game Over, there was a tie";


	private static final String WINNER_MESSAGE = "Game Over, there is already a winner";

	
	private GameManager gameManager;
	
	private PlayerManager circlePlayerManager;
	private PlayerManager crossPlayerManager;

	
	public void registerPlayers(Player circlePlayer, Player crossPlayer) throws WrongShapeException{
		if(!(circlePlayer.getShape() instanceof Circle) || !(crossPlayer.getShape() instanceof Cross)){
			throw new WrongShapeException();
		}
		
		circlePlayerManager = new PlayerManager(circlePlayer);
		crossPlayerManager = new PlayerManager(crossPlayer);
		
		gameManager = new GameManager(circlePlayerManager, crossPlayerManager);
		
	}
	
	public void playCross(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException{
		play(PlayerShape.CROSS, coordinate);
	}
	
	public void playCircle(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException{
		play(PlayerShape.CIRCLE, coordinate);
	}
	
	private void play(PlayerShape shape, GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException{
		
		if(crossPlayerManager == null || circlePlayerManager == null){
			throw new NoPlayersRegisteredException();
		}
		
		PlayerManager playerManager;
		if(shape == PlayerShape.CROSS){
			playerManager = crossPlayerManager;
		} else {
			playerManager = circlePlayerManager;
		}
		playerManager.setCoordinate(coordinate.getCoordinates());
		
		Player winner = checkWinner();
		if(winner == null && !isTie()){ 
			gameManager.play(playerManager);
		} else {
			if(isTie()){
				throw new GameOverException(TIE_MESSAGE);
			}else{
				throw new GameOverException(String.format("%s: %s", WINNER_MESSAGE, winner.getShape().toString()));
			}
			
		}
	}
	
	public Player checkWinner(){
		PlayerManager playerManager = gameManager.checkWinner();
		
		if(playerManager != null){
			return playerManager.getPlayer();
		}
		
		return null;
	}
	
	public Map<GameCoordinates, TicTacToeShape> getGameMatrix(){
		TicTacToeShape[][] matrix = gameManager.getGameMatrix();
		Map<GameCoordinates, TicTacToeShape> gameMatrix = new HashMap<>();
		
		for(int i = 0; i < matrix.length; i++){
			for(int j=0; j < matrix[i].length ; j++){
				TicTacToeShape shape = matrix[i][j];
				GameCoordinates gameCoordinates = GameCoordinates.getGameCoordinates(i, j);
				
				gameMatrix.put(gameCoordinates, shape);
			}
		}
		
		return gameMatrix;
	}
	
	public boolean isTie(){
		return gameManager.isTie();
	}

}
