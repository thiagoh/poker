package com.thiagoh.poker.model;

import com.thiagoh.poker.execution.GamePlayerState;

public class GamePlayerForm {

	private long playerId;

	private String face1;
	private String suit1;

	private String face2;
	private String suit2;

	private GamePlayerState state;

	public GamePlayerForm() {

	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getFace1() {
		return face1;
	}

	public void setFace1(String face1) {
		this.face1 = face1;
	}

	public String getSuit1() {
		return suit1;
	}

	public void setSuit1(String suit1) {
		this.suit1 = suit1;
	}

	public String getFace2() {
		return face2;
	}

	public void setFace2(String face2) {
		this.face2 = face2;
	}

	public String getSuit2() {
		return suit2;
	}

	public void setSuit2(String suit2) {
		this.suit2 = suit2;
	}

	public GamePlayerState getState() {
		return state;
	}

	public void setState(GamePlayerState state) {
		this.state = state;
	}

}
