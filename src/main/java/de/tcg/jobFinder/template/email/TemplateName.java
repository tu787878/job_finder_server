package de.tcg.jobFinder.template.email;

public enum TemplateName {
	APPLIEDJOBTOBUSINESS("AppliedJobToBusiness"), APPLIEDJOBTOUSER("AppliedJobToUser");

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
