package com.thiagoh.poker.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private Face face;
	@Enumerated(EnumType.STRING)
	private Suit suit;

	public Card() {

	}

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

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Card other = (Card) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "[" + face + " " + suit + "]";
	}

}
