package de.tcg.jobFinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="color")
	private String color;
	
	public City() {}

	public City(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}

	public City(Long id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", color=" + color + "]";
	}

	
}
