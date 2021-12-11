package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", unique = true)
	private String userId;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "birthday")
	private String birthday;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "post_code")
	private int postCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city", referencedColumnName = "id")
	private City city;
	
	@Column(name = "phone")
	private int phone;
	
	@Column(name = "gender")
	private String gender;
	
	@ManyToMany
	@JoinTable(name = "user_business_category", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "business_category_id"))
	private Set<BusinessCategory> businessCategories;
	
	@ManyToMany
	@JoinTable(name = "user_job_category", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "job_category_id"))
	private Set<JobCategory> jobCategories;
	
}
