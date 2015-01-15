package com.thiagoh.poker.model;

public class Hand {

	private Card card1;
	private Card card2;

	public Hand(Card card1, Card card2) {

		this.card1 = card1;
		this.card2 = card2;
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

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((card1 == null) ? 0 : card1.hashCode());
		result = prime * result + ((card2 == null) ? 0 : card2.hashCode());
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
		Hand other = (Hand) obj;
		if (card1 == null) {
			if (other.card1 != null)
				return false;
		} else if (!card1.equals(other.card1))
			return false;
		if (card2 == null) {
			if (other.card2 != null)
				return false;
		} else if (!card2.equals(other.card2))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Hand [card1=" + card1 + ", card2=" + card2 + "]";
	}

}
