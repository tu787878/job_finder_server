package de.tcg.jobFinder.dto;

public enum PathId {
	upload("/upload"), sample("/sample");

	String path;

	PathId(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
