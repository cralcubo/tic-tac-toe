package com.chris.tictactoe.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.chris.tictactoe.game.model.Player;
import com.chris.tictactoe.game.model.shapes.Circle;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class PlayerManagerTest {
	
	private PlayerManager playerManager;
	private Player player;
	
	@Before
	public void setUp(){
		player = EasyMock.createMock(Player.class);
		
		playerManager = new PlayerManager(player);
	}
	
	@Test
	public void testHasPlayed(){
		EasyMock.expect(player.isPlayed()).andReturn(true);
		
		EasyMock.replay(player);
		
		assertTrue(playerManager.hasPlayed());
		
		EasyMock.verify(player);
	}
	
	@Test
	public void testGetShape(){
		TicTacToeShape circle = new Circle();
		EasyMock.expect(player.getShape()).andReturn(circle );
		
		EasyMock.replay(player);
		
		TicTacToeShape shape = playerManager.getShape();
		
		assertEquals(circle, shape);
		
		EasyMock.verify(player);
		
	}
	
	

}
