package de.tcg.jobFinder.template.email;

import de.tcg.jobFinder.entity.AppliedJob;

public class ApplyJobToBusiness extends CommonEmailInformation {
	private String businessEmail;
	private AppliedJob appliedJob;

	public ApplyJobToBusiness(String businessEmail, AppliedJob appliedJob) {
		super(businessEmail, "", TemplateName.APPLIEDJOBTOBUSINESS.getName());
		this.businessEmail = businessEmail;
		this.appliedJob = appliedJob;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public AppliedJob getAppliedJob() {
		return appliedJob;
	}

	public void setAppliedJob(AppliedJob appliedJob) {
		this.appliedJob = appliedJob;
	}

	@Override
	public String toString() {
		return "ApplyJobToBusiness [businessEmail=" + businessEmail + ", appliedJob=" + appliedJob + "]";
	}

}
