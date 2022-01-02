package de.tcg.jobFinder.template.email;

public abstract class CommonEmailInformation {
	private String emailTo;
	private String subtitle;
	private String templateEmail;

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTemplateEmail() {
		return templateEmail;
	}

	public void setTemplateEmail(String templateEmail) {
		this.templateEmail = templateEmail;
	}

	public CommonEmailInformation() {
		super();
	}


	public CommonEmailInformation(String emailTo, String subtitle, String templateEmail) {
		super();
		this.emailTo = emailTo;
		this.subtitle = subtitle;
		this.templateEmail = templateEmail;
	}

	@Override
	public String toString() {
		return "CommonEmailInformation [emailTo=" + emailTo + ", subtitle=" + subtitle + "]";
	}

}
