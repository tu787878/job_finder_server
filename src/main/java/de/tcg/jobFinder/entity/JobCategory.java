package de.tcg.jobFinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job_category")
public class JobCategory  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "color")
	private String color;

	@Column(name = "businessCategoryId")
	private String businessCategoryId;

	public JobCategory() {
	}

	public JobCategory(String name, String color, String businessCategoryId) {
		super();
		this.name = name;
		this.color = color;
		this.businessCategoryId = businessCategoryId;
	}

	public JobCategory(Long id, String name, String color, String businessCategoryId) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.businessCategoryId = businessCategoryId;
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

	public String getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void setBusinessCategoryId(String businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	@Override
	public String toString() {
		return "JobCategory [id=" + id + ", name=" + name + ", color=" + color + ", businessCategoryId="
				+ businessCategoryId + "]";
	}

}
