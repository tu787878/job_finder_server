package de.tcg.jobFinder.dto;

import org.springframework.beans.factory.annotation.Autowired;

import de.tcg.jobFinder.entity.User;
import de.tcg.jobFinder.reposity.CityReposity;

public class UserRequest {
	private String userId;
	private String firstName;
	private String lastName;
	private String birthday;
	private String address;
	private int postCode;
	private String cityId;
	private String phone;
	private String gender;
	private String businessCategoryIds;
	private String jobCategoryIds;
	private String description;

	public UserRequest(String userId, String firstName, String lastName, String birthday, String address, int postCode,
			String cityId, String phone, String gender, String businessCategoryIds, String jobCategoryIds,
			String description) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.address = address;
		this.postCode = postCode;
		this.cityId = cityId;
		this.phone = phone;
		this.gender = gender;
		this.businessCategoryIds = businessCategoryIds;
		this.jobCategoryIds = jobCategoryIds;
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBusinessCategoryIds() {
		return businessCategoryIds;
	}

	public void setBusinessCategoryIds(String businessCategoryIds) {
		this.businessCategoryIds = businessCategoryIds;
	}

	public String getJobCategoryIds() {
		return jobCategoryIds;
	}

	public void setJobCategoryIds(String jobCategoryIds) {
		this.jobCategoryIds = jobCategoryIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRequest [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday="
				+ birthday + ", address=" + address + ", postCode=" + postCode + ", cityId=" + cityId + ", phone="
				+ phone + ", gender=" + gender + ", businessCategoryIds=" + businessCategoryIds + ", jobCategoryIds="
				+ jobCategoryIds + ", description=" + description + "]";
	}

}
