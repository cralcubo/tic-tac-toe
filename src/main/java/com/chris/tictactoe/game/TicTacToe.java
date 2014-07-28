package com.chris.tictactoe.game;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.exceptions.WrongShapeException;
import com.chris.tictactoe.game.model.Player;
/**
 * Interface that has all the actions
 * to play the Tic Tac Toe game.
 * 
 * @author christian
 *
 */
public interface TicTacToe {
	
	/**
	 * Method that will register two players
	 * to play the game.
	 * 
	 * @param circlePlayer
	 * @param crossPlayer
	 * @throws WrongShapeException
	 */
	void registerPlayers(Player circlePlayer, Player crossPlayer) throws WrongShapeException;
	
	/**
	 * Place a 'X' in the matrix of the game.
	 * 
	 * @param coordinate
	 * @throws PositionOccupiedException
	 * @throws WaitYourTurnException
	 * @throws GameOverException
	 */
	void playCross(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException;
	
	/**
	 * Place a 'O' in the matrix of the game.
	 * 
	 * @param coordinate
	 * @throws PositionOccupiedException
	 * @throws WaitYourTurnException
	 * @throws GameOverException
	 */
	void playCircle(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException;
	
	/**
	 * Method that will return the winner player in case there is a winner.
	 * 
	 * @return
	 */
	Player checkWinner();
	
	/**
	 * Method that will verify if the game is a tie. 
	 * @return
	 */
	boolean isTie();
	
	

}
