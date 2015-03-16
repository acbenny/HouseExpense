package com.acbenny.HouseExpenses.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Item {

	@Id
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "ITEMS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	private Integer id;

	@Column(unique = true, nullable = false)
	private String itemname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private BigDecimal price;

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", price=" + price + "]";
	}
}