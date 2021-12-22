package de.tcg.jobFinder.dto;

public enum StatusQuery {
	TOP("top"), NORMAL("normal");
	
	private String key;
	
	StatusQuery(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}
