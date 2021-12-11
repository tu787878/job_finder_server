package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "businessCategory")
public class BusinessCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "businessCategoryId", unique = true)
	private String businessCategoryId;

	@Column(name = "businessCategoryName", unique=true)
	private String businessCategoryName;
	
	public BusinessCategory() {
	}

	public BusinessCategory(String businessCategoryName) {
		this.businessCategoryId = "BC-" + UUID.randomUUID().toString();
		this.businessCategoryName = businessCategoryName;
	}

	public BusinessCategory(String businessCategoryId, String businessCategoryName) {
		
		this.businessCategoryId = businessCategoryId;
		this.businessCategoryName = businessCategoryName;
	}

	public String getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void businessRequest(String businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public String getBusinessCategoryName() {
		return businessCategoryName;
	}

	public void setBusinessCategoryName(String businessCategoryName) {
		this.businessCategoryName = businessCategoryName;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "BusinesCategory [id=" + id + ", businessCategoryId=" + businessCategoryId
				+ ", businessCategoryName=" + businessCategoryName + "]";
	}

}
