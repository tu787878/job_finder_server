package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
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
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

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
	private int gender;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_job_category", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "job_category_id"))
	private Set<JobCategory> jobCategories;

	@Column(name = "description")
	private String description;
	
	@Column(name = "avatar")
	private String avatar;

	public User(String userId, String first_name, String last_name, String birthday, String address, int postCode,
			City city, int phone, int gender, Set<JobCategory> jobCategories,
			String description, String avatar) {
		super();
		this.userId = userId;
		this.firstName = first_name;
		this.lastName = last_name;
		this.birthday = birthday;
		this.address = address;
		this.postCode = postCode;
		this.city = city;
		this.phone = phone;
		this.gender = gender;
		this.jobCategories = jobCategories;
		this.description = description;
		this.avatar = avatar;
	}

	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Set<JobCategory> getJobCategories() {
		return jobCategories;
	}

	public void setJobCategories(Set<JobCategory> jobCategories) {
		this.jobCategories = jobCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthday=" + birthday + ", address=" + address + ", postCode=" + postCode + ", city=" + city
				+ ", phone=" + phone + ", gender=" + gender + ", jobCategories=" + jobCategories + ", description="
				+ description + ", avatar=" + avatar + "]";
	}


}
