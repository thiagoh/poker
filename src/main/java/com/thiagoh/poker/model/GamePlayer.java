package com.thiagoh.poker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_player")
public class GamePlayer extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "playerId")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "gameId")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "cardId1")
	private Card card1;

	@ManyToOne
	@JoinColumn(name = "cardId2")
	private Card card2;

	private String state;

	public GamePlayer() {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Card getCard1() {

		return card1;
	}

	public void setCard1(Card card1) {

		this.card1 = card1;
	}

	public Card getCard2() {

		return card2;
	}

	public void setCard2(Card card2) {

		this.card2 = card2;
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

	public String getState() {

		return state;
	}

	public void setState(String state) {

		this.state = state;
	}

}
