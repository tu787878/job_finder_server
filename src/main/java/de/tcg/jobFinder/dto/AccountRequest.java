package de.tcg.jobFinder.dto;

public class AccountRequest {
	private String userName;
	private String password;
	boolean isBusiness;

	public AccountRequest(String userName, String password, boolean isBusiness) {
		super();
		this.userName = userName;
		this.password = password;
		this.isBusiness = isBusiness;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBusiness() {
		return isBusiness;
	}

	public void setBusiness(boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	@Override
	public String toString() {
		return "AccountRequest [username=" + userName + ", password=" + password + ", isBusiness=" + isBusiness + "]";
	}
}
