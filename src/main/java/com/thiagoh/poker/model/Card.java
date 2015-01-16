package com.thiagoh.poker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card_")
public class Card extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="face")
	private String face;
	
	@Column(name="suit")
	private String suit;

	public Card() {

	}

	public Card(String face, String suit) {

		super();
		this.face = face;
		this.suit = suit;
	}

	public String getFace() {

		return face;
	}

	public void setFace(String face) {

		this.face = face;
	}

	public String getSuit() {

		return suit;
	}

	public void setSuit(String suit) {

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
