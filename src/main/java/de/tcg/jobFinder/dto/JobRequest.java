package de.tcg.jobFinder.dto;


public class JobRequest {
	private String jobName;
	private String businessId;
	private String jobDescription;
	private String jobAddress;
	private String workingTime;
	private String note;
	private String jobLatitude;
	private String jobLongitude;
	private String salaryFrom;
	private String salaryTo;
	private String createdTime;
	private String expiredTime;
	private String jobCategoryId;
	private String cityId;
	private String jobRequirements;
	private String jobTagsId;
	private String active;
	private String postCode;
	private String jobBenefits;
	public JobRequest(String jobName, String businessId, String jobDescription, String jobAddress, String workingTime,
			String note, String jobLatitude, String jobLongitude, String salaryFrom, String salaryTo,
			String createdTime, String expiredTime, String jobCategoryId, String cityId, String jobRequirements,
			String jobTagsId, String active, String postCode, String jobBenefits) {
		super();
		this.jobName = jobName;
		this.businessId = businessId;
		this.jobDescription = jobDescription;
		this.jobAddress = jobAddress;
		this.workingTime = workingTime;
		this.note = note;
		this.jobLatitude = jobLatitude;
		this.jobLongitude = jobLongitude;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.createdTime = createdTime;
		this.expiredTime = expiredTime;
		this.jobCategoryId = jobCategoryId;
		this.cityId = cityId;
		this.jobRequirements = jobRequirements;
		this.jobTagsId = jobTagsId;
		this.active = active;
		this.postCode = postCode;
		this.jobBenefits = jobBenefits;
	}
	public JobRequest() {
		super();
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobAddress() {
		return jobAddress;
	}
	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getJobLatitude() {
		return jobLatitude;
	}
	public void setJobLatitude(String jobLatitude) {
		this.jobLatitude = jobLatitude;
	}
	public String getJobLongitude() {
		return jobLongitude;
	}
	public void setJobLongitude(String jobLongitude) {
		this.jobLongitude = jobLongitude;
	}
	public String getSalaryFrom() {
		return salaryFrom;
	}
	public void setSalaryFrom(String salaryFrom) {
		this.salaryFrom = salaryFrom;
	}
	public String getSalaryTo() {
		return salaryTo;
	}
	public void setSalaryTo(String salaryTo) {
		this.salaryTo = salaryTo;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getJobCategoryId() {
		return jobCategoryId;
	}
	public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getJobRequirements() {
		return jobRequirements;
	}
	public void setJobRequirements(String jobRequirements) {
		this.jobRequirements = jobRequirements;
	}
	public String getJobTagsId() {
		return jobTagsId;
	}
	public void setJobTagsId(String jobTagsId) {
		this.jobTagsId = jobTagsId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getJobBenefits() {
		return jobBenefits;
	}
	public void setJobBenefits(String jobBenefits) {
		this.jobBenefits = jobBenefits;
	}
	@Override
	public String toString() {
		return "JobRequest [jobName=" + jobName + ", businessId=" + businessId + ", jobDescription=" + jobDescription
				+ ", jobAddress=" + jobAddress + ", workingTime=" + workingTime + ", note=" + note + ", jobLatitude="
				+ jobLatitude + ", jobLongitude=" + jobLongitude + ", salaryFrom=" + salaryFrom + ", salaryTo="
				+ salaryTo + ", createdTime=" + createdTime + ", expiredTime=" + expiredTime + ", jobCategoryId="
				+ jobCategoryId + ", cityId=" + cityId + ", jobRequirements=" + jobRequirements + ", jobTagsId="
				+ jobTagsId + ", active=" + active + ", postCode=" + postCode + ", jobBenefits=" + jobBenefits + "]";
	}
	
}
