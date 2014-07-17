package com.chris.tictactoe.game;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.exceptions.WrongShapeException;
import com.chris.tictactoe.game.model.Player;

public interface TicTacToe {
	
	void registerPlayers(Player circlePlayer, Player crossPlayer) throws WrongShapeException;
	
	void startGame() throws NoPlayersRegisteredException;
	
	void playCross(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException;
	
	void playCircle(GameCoordinates coordinate) throws PositionOccupiedException, WaitYourTurnException, GameOverException;
	
	Player checkWinner();
	
	boolean isTie();
	
	

}
