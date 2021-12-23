package de.tcg.jobFinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appliedJob")
public class AppliedJob  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "userId")
	private User userId;
	
	@Column(name = "businessId")
	private String businessId;

	@ManyToOne()
	@JoinColumn(name = "jobId")
	private Job job;

	@Column(name = "status")
	private String status;

	public AppliedJob(Long id, User userId, String businessId, Job job, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.businessId = businessId;
		this.job = job;
		this.status = status;
	}
	
	public AppliedJob(User userId, String businessId, Job job, String status) {
		super();
		this.userId = userId;
		this.businessId = businessId;
		this.job = job;
		this.status = status;
	}

	public AppliedJob() {
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Override
	public String toString() {
		return "AppliedJob [id=" + id + ", userId=" + userId + ", businessId=" + businessId + ", job=" + job
				+ ", status=" + status + "]";
	}

}
