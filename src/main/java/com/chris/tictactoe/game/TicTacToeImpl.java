package com.chris.tictactoe.game;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.exceptions.WrongShapeException;
import com.chris.tictactoe.game.model.Player;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.Cross;

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
	}
	
	public void playCross(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException{
		play(PlayerShape.CROSS, coordinate);
	}
	
	public void playCircle(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException{
		play(PlayerShape.CIRCLE, coordinate);
	}
	
	private void play(PlayerShape shape, GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException{
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
	
	public boolean isTie(){
		return gameManager.isTie();
	}
	
	public void startGame() throws NoPlayersRegisteredException{
		if(circlePlayerManager != null && crossPlayerManager != null){
			gameManager = new GameManager(circlePlayerManager, crossPlayerManager);
		} else {
			throw new NoPlayersRegisteredException();
		}
	}

}
