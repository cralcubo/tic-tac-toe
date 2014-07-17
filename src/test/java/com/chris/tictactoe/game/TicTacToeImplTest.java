package com.chris.tictactoe.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chris.tictactoe.game.exceptions.GameOverException;
import com.chris.tictactoe.game.exceptions.NoPlayersRegisteredException;
import com.chris.tictactoe.game.exceptions.PositionOccupiedException;
import com.chris.tictactoe.game.exceptions.WaitYourTurnException;
import com.chris.tictactoe.game.exceptions.WrongShapeException;
import com.chris.tictactoe.game.model.Player;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.Cross;

public class TicTacToeImplTest {
	
	private TicTacToeImpl game;
	
	@Before
	public void setUp(){
		game = new TicTacToeImpl();
	}
	
	private void startGame() throws NoPlayersRegisteredException, WrongShapeException{
		Player circlePlayer = new Player(new Circle());
		Player crossPlayer = new Player(new Cross());
		game.registerPlayers(circlePlayer, crossPlayer);
		game.startGame();
	}
	
	@Test(expected = NoPlayersRegisteredException.class)
	public void testStartGame_noPlayers() throws NoPlayersRegisteredException{
		game.startGame();
	}
	
	@Test
	public void testStartGame() throws NoPlayersRegisteredException, WrongShapeException{
		startGame();
	}
	
	@Test(expected = WrongShapeException.class)
	public void testStartGame_wrongShape() throws NoPlayersRegisteredException, WrongShapeException{
		Player circlePlayer = new Player(new Cross());
		Player crossPlayer = new Player(new Cross());
		game.registerPlayers(circlePlayer, crossPlayer);
		game.startGame();
	}
	
	@Test
	public void testPlay() throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
		startGame();
		GameCoordinates coordinate = GameCoordinates.A1;
		game.playCross(coordinate);
		
		GameCoordinates coordinateCircle = GameCoordinates.C2;
		game.playCircle(coordinateCircle );
		
		GameCoordinates coordinate2 = GameCoordinates.C1;
		game.playCross(coordinate2);
		
		GameCoordinates coordinateCircle2 = GameCoordinates.B2;
		game.playCircle(coordinateCircle2 );
		
	}
	
	@Test(expected = WaitYourTurnException.class)
	public void testPlay_twice() throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
		startGame();
		GameCoordinates coordinate = GameCoordinates.B1;
		game.playCross(coordinate);
		
		GameCoordinates coordinate2 = GameCoordinates.B1;
		game.playCross(coordinate2);
	}
	
	
	@Test(expected = PositionOccupiedException.class)
	public void testPlay_positionOccupied() throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
		startGame();
		GameCoordinates coordinate = GameCoordinates.C2;
		game.playCross(coordinate );
		
		GameCoordinates coordinateCircle = GameCoordinates.C2;
		game.playCircle(coordinateCircle );
	}
	
	@Test(expected = GameOverException.class)
	public void testPlay_gameFinished() throws PositionOccupiedException, WaitYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
		winGameCross();
		
		GameCoordinates coordinate = GameCoordinates.C1;
		game.playCircle(coordinate );
	}
	
	@Test
	public void testWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		startGame();
		
		game.playCross(GameCoordinates.A1);
		game.playCircle(GameCoordinates.A3);
		
		game.playCross(GameCoordinates.B2);
		game.playCircle(GameCoordinates.C1);
		
		game.playCross(GameCoordinates.C3);
		
		Player winner = game.checkWinner();
		
		assertFalse(game.isTie());
		assertTrue(winner.getShape() instanceof Cross);
		
	}
	
	@Test
	public void testIsTie() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		tieGame();
		assertTrue(game.isTie());
	}
	
	@Test
	public void testCheckWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		winGameCross();
		Player winner = game.checkWinner();
		
		assertNotNull(winner);
		assertTrue(winner.getShape() instanceof Cross);
	}
	
	@Test
	public void testCheckWinner_noWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		startGame();
		
		GameCoordinates coordinate = GameCoordinates.B2;
		game.playCross(coordinate);
		
		Player winner = game.checkWinner();
		
		assertNull(winner);
	}
	
	@Test(expected = GameOverException.class)
	public void testTieGame() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		tieGame();
		
		game.playCross(GameCoordinates.A1);
	}
	
	private void tieGame() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		startGame();
		GameCoordinates x1 = GameCoordinates.A2;
		GameCoordinates x2 = GameCoordinates.B1;
		GameCoordinates x3 = GameCoordinates.C2;
		GameCoordinates x4 = GameCoordinates.C3;
		
		GameCoordinates o1 = GameCoordinates.A1;
		GameCoordinates o2 = GameCoordinates.A3;
		GameCoordinates o3 = GameCoordinates.B2;
		GameCoordinates o4 = GameCoordinates.B3;
		GameCoordinates o5 = GameCoordinates.C1;
		
		game.playCircle(o1);
		game.playCross(x1);
		
		game.playCircle(o2);
		game.playCross(x2);
		
		game.playCircle(o3);
		game.playCross(x3);
		
		game.playCircle(o4);
		game.playCross(x4);
		
		game.playCircle(o5);
	}
	
	private void winGameCross() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
		startGame();
		GameCoordinates x1 = GameCoordinates.A1;
		GameCoordinates x2 = GameCoordinates.A2;
		GameCoordinates x3 = GameCoordinates.A3;
		
		GameCoordinates o1 = GameCoordinates.B1;
		GameCoordinates o2 = GameCoordinates.B2;
		
		game.playCross(x1);
		game.playCircle(o1);
		
		game.playCross(x2);
		game.playCircle(o2);
		
		game.playCross(x3);
		
	}
	

}
