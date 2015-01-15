package com.thiagoh.poker.model;

public class Card {

	private Face face;
	private Suit suit;

	public Card(Face face, Suit suit) {

		super();
		this.face = face;
		this.suit = suit;
	}

	public Face getFace() {

		return face;
	}

	public void setFace(Face face) {

		this.face = face;
	}

	public Suit getSuit() {

		return suit;
	}

	public void setSuit(Suit suit) {

		this.suit = suit;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((face == null) ? 0 : face.hashCode());
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
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (face != other.face)
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "[" + face + " " + suit + "]";
	}

}
