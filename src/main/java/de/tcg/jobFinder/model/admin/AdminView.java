package de.tcg.jobFinder.model.admin;

public abstract class AdminView {
	private String name;
	private String id;
	private String path;
	
	public AdminView(String name, String id, String path) {
		this.name = name;
		this.id = id;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
