package de.tcg.jobFinder.model.admin;

public class AdminActionMethod extends AdminView{
	
	public AdminActionMethod( String viewFile, String name, String id) {
		super(name, id, viewFile);
	}
	
	public String toPermissionId() {
		return "." + this.getId();
	}

}
