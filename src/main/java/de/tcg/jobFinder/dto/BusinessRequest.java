package de.tcg.jobFinder.dto;

public class BusinessRequest {
	private String businessId;
	private String businessName;
	private String businessAddres;
	private String businessCategoryId;
	private String businessDescription;
	private String image;
	
	public BusinessRequest() {}

	public BusinessRequest(String businessId, String businessName, String businessAddres, String businessCategoryId,
			String businessDescription, String image) {
		this.businessId = businessId;
		this.businessName = businessName;
		this.businessAddres = businessAddres;
		this.businessCategoryId = businessCategoryId;
		this.businessDescription = businessDescription;
		this.image = image;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessAddres() {
		return businessAddres;
	}

	public void setBusinessAddres(String businessAddres) {
		this.businessAddres = businessAddres;
	}

	public String getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void setBusinessCategoryId(String businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "BusinessRequest [businessId=" + businessId + ", businessName=" + businessName + ", businessAddres="
				+ businessAddres + ", businessCategoryId=" + businessCategoryId + ", businessDescription="
				+ businessDescription + ", image=" + image + "]";
	};

	
}
