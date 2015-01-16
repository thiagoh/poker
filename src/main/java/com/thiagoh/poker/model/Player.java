package com.thiagoh.poker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue
	private Long _id;

	private String _name;
	private String _email;

	public Player() {

	}

	public Player(String email, String name) {

		this._email = email;
		this._name = name;
	}

	public String getEmail() {

		return _email;
	}

	public void setEmail(String email) {

		this._email = email;
	}

	public String getName() {

		return _name;
	}

	public void setName(String name) {

		this._name = name;
	}

	public Long getId() {

		return _id;
	}

	public void setId(Long id) {

		this._id = id;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((_email == null) ? 0 : _email.hashCode());
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
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
		Player other = (Player) obj;
		if (_email == null) {
			if (other._email != null)
				return false;
		} else if (!_email.equals(other._email))
			return false;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Player [" + _name + "<" + _email + ">]";
	}

}
