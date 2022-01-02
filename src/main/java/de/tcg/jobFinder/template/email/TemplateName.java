package de.tcg.jobFinder.template.email;

public enum TemplateName {
	APPLIEDJOBTOUSER("ApplyJobToBusiness"), APPLIEDJOBTOBUSINESS("AppliedJobToUser");

	private String name;

	TemplateName() {

	}

	TemplateName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
