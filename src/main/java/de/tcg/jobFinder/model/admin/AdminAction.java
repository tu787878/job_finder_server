package de.tcg.jobFinder.model.admin;

import java.util.List;

public class AdminAction extends AdminView{
	
	List<AdminActionMethod> methods;
	private String level;
	
	public AdminAction(String name, String id, String viewFile, String level, List<AdminActionMethod> methods) {
		super(name, id, viewFile);
		this.level = level;
		this.methods = methods;
	}

	public List<AdminActionMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<AdminActionMethod> methods) {
		this.methods = methods;
	}
	
	public String toPermissionIdANY() {
		return this.getId() + ".ANY";
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
