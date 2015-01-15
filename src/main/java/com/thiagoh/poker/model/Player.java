package com.thiagoh.poker.model;

public class Player {

	private String name;
	private String email;

	public Player(String email, String name) {

		this.email = email;
		this.name = name;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Player [" + name + "<" + email + ">]";
	}

}
