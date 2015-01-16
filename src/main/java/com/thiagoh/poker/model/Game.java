package com.thiagoh.poker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thiagoh.poker.execution.GameState;

@Entity
@Table(name = "game")
public class Game extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "game")
	private Set<GamePlayer> gamePlayers;

	@ManyToOne
	@JoinColumn(name = "tableCardId1")
	private Card tableCard1;
	@ManyToOne
	@JoinColumn(name = "tableCardId2")
	private Card tableCard2;
	@ManyToOne
	@JoinColumn(name = "tableCardId3")
	private Card tableCard3;
	@ManyToOne
	@JoinColumn(name = "tableCardId4")
	private Card tableCard4;
	@ManyToOne
	@JoinColumn(name = "tableCardId5")
	private Card tableCard5;
	@Enumerated(EnumType.STRING)
	private GameState state;
	@Enumerated(EnumType.STRING)
	private TableCardsState tableCardsState;

	public Game() {

	}

	public long getId() {

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

	public GameState getState() {

		return state;
	}

	public void setState(GameState state) {

		this.state = state;
	}

	public TableCardsState getTableCardsState() {

		return tableCardsState;
	}

	public void setTableCardsState(TableCardsState tableCardsState) {

		this.tableCardsState = tableCardsState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
