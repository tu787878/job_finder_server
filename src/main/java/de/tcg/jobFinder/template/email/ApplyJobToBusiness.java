package de.tcg.jobFinder.template.email;

import de.tcg.jobFinder.entity.Business;
import de.tcg.jobFinder.entity.Job;
import de.tcg.jobFinder.entity.User;

public class ApplyJobToBusiness {
	private User user;
	private Business business;
	private Job job;
	public ApplyJobToBusiness(User user, Business business, Job job) {
		super();
		this.user = user;
		this.business = business;
		this.job = job;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "ApplyJobToBusiness [user=" + user + ", business=" + business + ", job=" + job + "]";
	}

}
