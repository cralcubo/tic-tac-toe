package com.chris.tictactoe.game;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.Cross;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class GameManagerTest {
	
	private GameManager gameManager;
	private PlayerManager circlePlayer;
	private PlayerManager crossPlayer;
	private Matrix matrix;
	
	@Before
	public void setUp(){
		circlePlayer = EasyMock.createMock(PlayerManager.class);
		crossPlayer  = EasyMock.createMock(PlayerManager.class);
		matrix = EasyMock.createMock(Matrix.class);
		
		gameManager = new GameManager(circlePlayer, crossPlayer);
		gameManager.setMatrix(matrix);
	}
	
	@Test
	public void testPlay() throws PositionOccupiedException, WaitYourTurnException{
		PlayerManager playerManager = circlePlayer;
		EasyMock.expect(playerManager.hasPlayed()).andReturn(false);
		TicTacToeShape circleShape = new Circle();
		EasyMock.expect(playerManager.getShape()).andReturn(circleShape);
		matrix.writeElement(circleShape);
		
		circlePlayer.setPlayed(true);
		crossPlayer.setPlayed(false);
		
		EasyMock.replay(circlePlayer, crossPlayer, matrix);
		
		gameManager.play(playerManager);
		
		EasyMock.verify(circlePlayer, crossPlayer, matrix);
	}
	
	@Test(expected = WaitYourTurnException.class)
	public void testPlayShape_alreadyPlayed() throws PositionOccupiedException, WaitYourTurnException{
		PlayerManager playerManager = crossPlayer;
		EasyMock.expect(crossPlayer.hasPlayed()).andReturn(true);
		
		EasyMock.replay(circlePlayer, crossPlayer, matrix);
		
		gameManager.play(playerManager);
		
		EasyMock.verify(circlePlayer, crossPlayer, matrix);
	}
	
	@Test
	public void testCheckWinner(){
		TicTacToeShape winnerShape = new Cross();
		
		EasyMock.expect(matrix.checkRows()).andReturn(winnerShape);
		
		EasyMock.replay(circlePlayer, crossPlayer, matrix);
		
		PlayerManager winner = gameManager.checkWinner();
		
		assertEquals(crossPlayer, winner);
		
		EasyMock.verify(circlePlayer, crossPlayer, matrix);
	}

}
