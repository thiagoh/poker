package com.thiagoh.poker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_player")
public class GamePlayer {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "playerId")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "gameModelId")
	private Game game;

	private String cardFace1;
	private String cardSuit1;

	private String cardFace2;
	private String cardSuit2;

	private boolean hasCards;
	private String state;

	public GamePlayer() {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getCardFace1() {

		return cardFace1;
	}

	public void setCardFace1(String cardFace1) {

		this.cardFace1 = cardFace1;
	}

	public String getCardSuit1() {

		return cardSuit1;
	}

	public void setCardSuit1(String cardSuit1) {

		this.cardSuit1 = cardSuit1;
	}

	public String getCardFace2() {

		return cardFace2;
	}

	public void setCardFace2(String cardFace2) {

		this.cardFace2 = cardFace2;
	}

	public String getCardSuit2() {

		return cardSuit2;
	}

	public void setCardSuit2(String cardSuit2) {

		this.cardSuit2 = cardSuit2;
	}

	public Player getPlayer() {

		return player;
	}

	public void setPlayer(Player player) {

		this.player = player;
	}

	public Game getGame() {

		return game;
	}

	public void setGame(Game game) {

		this.game = game;
	}

	public boolean isHasCards() {

		return hasCards;
	}

	public void setHasCards(boolean hasCards) {

		this.hasCards = hasCards;
	}

	public String getState() {

		return state;
	}

	public void setState(String state) {

		this.state = state;
	}

}
