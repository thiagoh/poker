package com.thiagoh.poker.model;


public class GamePlayerForm {

	private long playerId;

	private Face face1;
	private Suit suit1;

	private Face face2;
	private Suit suit2;

	private GamePlayerState state;

	public GamePlayerForm() {

	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public Face getFace1() {
		return face1;
	}

	public void setFace1(Face face1) {
		this.face1 = face1;
	}

	public Suit getSuit1() {
		return suit1;
	}

	public void setSuit1(Suit suit1) {
		this.suit1 = suit1;
	}

	public Face getFace2() {
		return face2;
	}

	public void setFace2(Face face2) {
		this.face2 = face2;
	}

	public Suit getSuit2() {
		return suit2;
	}

	public void setSuit2(Suit suit2) {
		this.suit2 = suit2;
	}

	public GamePlayerState getState() {
		return state;
	}

	public void setState(GamePlayerState state) {
		this.state = state;
	}

}
