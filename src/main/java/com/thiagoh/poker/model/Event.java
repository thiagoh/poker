package com.thiagoh.poker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;
	@Column
	private String description;
	@Column(name = "date_", nullable = false)
	private Date date;
	@Column(nullable = false)
	private double buyInPrice;
	@Column(nullable = false)
	private int buyInChipCount;

	public Event() {

	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBuyInPrice() {
		return buyInPrice;
	}

	public void setBuyInPrice(double buyInPrice) {
		this.buyInPrice = buyInPrice;
	}

	public int getBuyInChipCount() {
		return buyInChipCount;
	}

	public void setBuyInChipCount(int buyInChipCount) {
		this.buyInChipCount = buyInChipCount;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", date=" + date + ", buyInPrice=" + buyInPrice
				+ ", buyInChipCount=" + buyInChipCount + "]";
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
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
