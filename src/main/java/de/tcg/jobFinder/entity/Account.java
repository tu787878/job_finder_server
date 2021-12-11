package de.tcg.jobFinder.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="accountId")
	private String accountId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	
	@Column(name = "lastUsed", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;
	
	@Column(name="isBusiness")
	private boolean isBusiness;
	
	@Column(name="businessId")
	private String businessId;
	
	@Column(name="userId")
	private String userId;

	public Account(String accountId, String userName, String password, Date lastUsed, boolean isBusiness,
			String businessId, String userId) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.lastUsed = lastUsed;
		this.isBusiness = isBusiness;
		this.businessId = businessId;
		this.userId = userId;
	}
	
	public Account() {
		
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public boolean isBusiness() {
		return isBusiness;
	}

	public void setBusiness(boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountId=" + accountId + ", userName=" + userName + ", password=" + password
				+ ", lastUsed=" + lastUsed + ", isBusiness=" + isBusiness + ", businessId=" + businessId + ", userId="
				+ userId + "]";
	}
	
	
}
