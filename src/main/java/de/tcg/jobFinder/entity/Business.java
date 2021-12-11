package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "business")
public class Business implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "businessId", unique = true)
	private String businessId;

	@Column(name = "businessName")
	private String businessName;

	@Column(name = "businessAdress")
	private String businessAdress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "businessCategoryId", referencedColumnName = "businessCategoryId")
	private BusinessCategory businessCategory;

	@Column(name = "businessDescription")
	private String businessDescription;

	@Column(name = "businessLogoPath")
	private String businessLogoPath;

	public Business() {
	}

	public Business(String businessName, String businessAdress,
			BusinessCategory businessCategory, String businessDescription, String businessLogoPath) {
		this.businessId = "B-" + UUID.randomUUID().toString();
		this.businessName = businessName;
		this.businessAdress = businessAdress;
		this.businessCategory = businessCategory;
		this.businessDescription = businessDescription;
		this.businessLogoPath = businessLogoPath;
	}

	public Business(String businessId, String businessName, String businessAdress, BusinessCategory businessCategory, String businessDescription,
			String businessLogoPath) {
		super();
		this.businessId = businessId;
		this.businessName = businessName;
		this.businessAdress = businessAdress;
		this.businessCategory = businessCategory;
		this.businessDescription = businessDescription;
		this.businessLogoPath = businessLogoPath;
	}

	public String getbusinessId() {
		return businessId;
	}

	public void setbusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getbusinessName() {
		return businessName;
	}

	public void setbusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getbusinessAdress() {
		return businessAdress;
	}

	public void setbusinessAdress(String businessAdress) {
		this.businessAdress = businessAdress;
	}

	public BusinessCategory getbusinessCategory() {
		return businessCategory;
	}

	public void setbusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getbusinessDescription() {
		return businessDescription;
	}

	public void setbusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getbusinessLogoPath() {
		return businessLogoPath;
	}

	public void setbusinessLogoPath(String businessLogoPath) {
		this.businessLogoPath = businessLogoPath;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", businessId=" + businessId + ", businessName=" + businessName
				+ ", businessAdress=" + businessAdress + ", businessCategory=" + businessCategory
				+ ", businessDescription=" + businessDescription + ", businessLogoPath=" + businessLogoPath + "]";
	}


}
