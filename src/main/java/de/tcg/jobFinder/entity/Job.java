package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "job")
public class Job implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "job_id", unique = true)
	private String jobId;

	@Column(name = "job_name")
	private String jobName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "businessId", referencedColumnName = "businessId")
	private Business business;

	@Column(name = "job_description", length = 1000)
	private String jobDescription;

	@Column(name = "job_address")
	private String jobAddress;

	@Column(name = "working_time")
	private String workingTime;

	@Column(name = "note")
	private String note;

	@Column(name = "job_latitude")
	private long jobLatitude;

	@Column(name = "job_longitude")
	private long jobLongitude;

	@Column(name = "salary_from")
	private float salaryFrom;

	@Column(name = "salary_to")
	private float salaryTo;

	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "post_code")
	private int postCode;

	@Column(name = "created_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdTime;

	@Column(name = "expired_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime expiredTime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "job_category", referencedColumnName = "id")
	private JobCategory jobCategory;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city", referencedColumnName = "id")
	private City city;

	@Column(name = "job_requirements", length = 1000)
	private String jobRequirements;

	@Column(name = "job_benefits", length = 1000)
	private String jobBenefits;

	@ManyToMany
	@JoinTable(name = "job_job_tag", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "job_tag_id"))
	private Set<JobTag> jobTag;

	public Job() {
	}

	public Job(Long id, String jobId, String jobName, Business businessId, String jobDescription, String jobAddress,
			String workingTime, String note, long jobLatitude, long jobLongitude, float salaryFrom, float salaryTo,
			boolean isActive, int postCode, LocalDateTime createdTime, LocalDateTime expiredTime, JobCategory jobCategory, City city,
			String jobRequirements, String jobBenefits, Set<JobTag> jobTag) {
		this.id = id;
		this.jobId = jobId;
		this.jobName = jobName;
		this.business = businessId;
		this.jobDescription = jobDescription;
		this.jobAddress = jobAddress;
		this.workingTime = workingTime;
		this.note = note;
		this.jobLatitude = jobLatitude;
		this.jobLongitude = jobLongitude;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.isActive = isActive;
		this.postCode = postCode;
		this.createdTime = createdTime;
		this.expiredTime = expiredTime;
		this.jobCategory = jobCategory;
		this.city = city;
		this.jobRequirements = jobRequirements;
		this.jobBenefits = jobBenefits;
		this.jobTag = jobTag;
	}

	public Job(String jobId, String jobName, Business businessId, String jobDescription, String jobAddress,
			String workingTime, String note, long jobLatitude, long jobLongitude, float salaryFrom, float salaryTo,
			boolean isActive, int postCode, LocalDateTime createdTime, LocalDateTime expiredTime, JobCategory jobCategory, City city,
			String jobRequirements, String jobBenefits, Set<JobTag> jobTag) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.business = businessId;
		this.jobDescription = jobDescription;
		this.jobAddress = jobAddress;
		this.workingTime = workingTime;
		this.note = note;
		this.jobLatitude = jobLatitude;
		this.jobLongitude = jobLongitude;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.isActive = isActive;
		this.postCode = postCode;
		this.createdTime = createdTime;
		this.expiredTime = expiredTime;
		this.jobCategory = jobCategory;
		this.city = city;
		this.jobRequirements = jobRequirements;
		this.jobBenefits = jobBenefits;
		this.jobTag = jobTag;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business businessId) {
		this.business = businessId;
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

	public long getJobLatitude() {
		return jobLatitude;
	}

	public void setJobLatitude(long jobLatitude) {
		this.jobLatitude = jobLatitude;
	}

	public long getJobLongitude() {
		return jobLongitude;
	}

	public void setJobLongitude(long jobLongitude) {
		this.jobLongitude = jobLongitude;
	}

	public float getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(float salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public float getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(float salaryTo) {
		this.salaryTo = salaryTo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJobRequirements() {
		return jobRequirements;
	}

	public void setJobRequirements(String jobRequirements) {
		this.jobRequirements = jobRequirements;
	}

	public String getJobBenefits() {
		return jobBenefits;
	}

	public void setJobBenefits(String jobBenefits) {
		this.jobBenefits = jobBenefits;
	}

	public Set<JobTag> getJobTag() {
		return jobTag;
	}

	public void setJobTag(Set<JobTag> jobTag) {
		this.jobTag = jobTag;
	}

	public Long getId() {
		return id;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobId=" + jobId + ", jobName=" + jobName + ", businessId=" + business
				+ ", jobDescription=" + jobDescription + ", jobAddress=" + jobAddress + ", workingTime=" + workingTime
				+ ", note=" + note + ", jobLatitude=" + jobLatitude + ", jobLongitude=" + jobLongitude + ", salaryFrom="
				+ salaryFrom + ", salaryTo=" + salaryTo + ", isActive=" + isActive + ", postCode=" + postCode
				+ ", createdTime=" + createdTime + ", expiredTime=" + expiredTime + ", jobCategory=" + jobCategory
				+ ", city=" + city + ", jobRequirements=" + jobRequirements + ", jobBenefits=" + jobBenefits
				+ ", jobTag=" + jobTag + "]";
	}

}
