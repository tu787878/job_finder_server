package de.tcg.jobFinder.dto;

public class JobTagsRequest {

	private String jobTagsId;

	public String getJobTagsId() {
		return jobTagsId;
	}

	public void setJobTagsId(String jobTagsId) {
		this.jobTagsId = jobTagsId;
	}

	@Override
	public String toString() {
		return "JobTagsRequest [jobTagsId=" + jobTagsId + "]";
	}
	
}
