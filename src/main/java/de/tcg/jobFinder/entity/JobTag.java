package de.tcg.jobFinder.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "job_tag")
public class JobTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="color")
	private String color;
	
	@ManyToMany(mappedBy = "jobTag", fetch = FetchType.LAZY)
	Set<Job> jobs;
	
	public JobTag(){}

	public JobTag(String name, String color, Set<Job> jobs) {
		this.name = name;
		this.color = color;
		this.jobs = jobs;
	}

	public JobTag(Long id, String name, String color, Set<Job> jobs) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.jobs = jobs;
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
		return "JobTag [id=" + id + ", name=" + name + ", color=" + color + "]";
	}


}
