package de.tcg.jobFinder.dto;

public class ApplyJobRequest {
	
	private String jobId;

	private String status;

	public ApplyJobRequest(String jobId, String status) {
		super();
		this.jobId = jobId;
		this.status = status;
	}

	public ApplyJobRequest() {
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
