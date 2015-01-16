package com.thiagoh.poker.execution;

import com.thiagoh.poker.model.GamePlayerState;

public class GamePlayer {

	private Player player;
	private GameExecution game;
	private Hand hand;
	private boolean hasCards;
	private GamePlayerState state;

	public GamePlayer(GameExecution game, Player player) {

		this.game = game;
		this.player = player;
		this.hand = null;
		this.hasCards = false;
		this.state = GamePlayerState.READY;
	}

	public GamePlayer(GameExecution game, Player player, Hand hand) {

		this.game = game;
		this.player = player;
		this.hand = hand;
		this.hasCards = true;
		this.state = GamePlayerState.READY;
	}

	public void fold() {

		this.state = GamePlayerState.OUT;
	}

	public Hand getHand() {

		return hand;
	}

	public void setHand(Hand hand) {

		this.hand = hand;
	}

	public Player getPlayer() {

		return player;
	}

	public void setPlayer(Player player) {

		this.player = player;
	}

	public GameExecution getGame() {

		return game;
	}

	public void setGame(GameExecution game) {

		this.game = game;
	}

	public boolean isHasCards() {

		return hasCards;
	}

	public void setHasCards(boolean hasCards) {

		this.hasCards = hasCards;
	}

	public boolean isPlaying() {

		return state == GamePlayerState.GAMING;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GamePlayer other = (GamePlayer) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return player + " with " + hand;
	}

}
