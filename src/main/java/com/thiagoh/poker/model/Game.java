package com.thiagoh.poker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "game")
	private Set<GamePlayer> gamePlayers;

	private Card tableCard1;
	private Card tableCard2;
	private Card tableCard3;
	private Card tableCard4;
	private Card tableCard5;

	private String state;
	private String tableCardsState;

	public Game() {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Set<GamePlayer> getGamePlayers() {

		return gamePlayers;
	}

	public void setGamePlayers(Set<GamePlayer> gamePlayers) {

		this.gamePlayers = gamePlayers;
	}

	public Card getTableCard1() {

		return tableCard1;
	}

	public void setTableCard1(Card tableCard1) {

		this.tableCard1 = tableCard1;
	}

	public Card getTableCard2() {

		return tableCard2;
	}

	public void setTableCard2(Card tableCard2) {

		this.tableCard2 = tableCard2;
	}

	public Card getTableCard3() {

		return tableCard3;
	}

	public void setTableCard3(Card tableCard3) {

		this.tableCard3 = tableCard3;
	}

	public Card getTableCard4() {

		return tableCard4;
	}

	public void setTableCard4(Card tableCard4) {

		this.tableCard4 = tableCard4;
	}

	public Card getTableCard5() {

		return tableCard5;
	}

	public void setTableCard5(Card tableCard5) {

		this.tableCard5 = tableCard5;
	}

	public String getState() {

		return state;
	}

	public void setState(String state) {

		this.state = state;
	}

	public String getTableCardsState() {

		return tableCardsState;
	}

	public void setTableCardsState(String tableCardsState) {

		this.tableCardsState = tableCardsState;
	}

}
