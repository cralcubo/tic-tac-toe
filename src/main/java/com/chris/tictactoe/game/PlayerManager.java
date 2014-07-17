package com.chris.tictactoe.game;

import com.chris.tictactoe.game.model.Coordinate;
import com.chris.tictactoe.game.model.Player;
import com.chris.tictactoe.game.model.shapes.TicTacToeShape;

public class PlayerManager {
	
	private Player player;
	
	public PlayerManager(Player player) {
		this.setPlayer(player);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean hasPlayed() {
		return player.isPlayed();
	}

	public TicTacToeShape getShape() {
		return player.getShape();
	}

	public void setPlayed(boolean played) {
		player.setPlayed(played);
	}

	public void setCoordinate(Coordinate coordinate) {
		player.getShape().setCoordinate(coordinate);
	}

}
