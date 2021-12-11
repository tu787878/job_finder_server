package de.tcg.jobFinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="accountToken")
public class AccountToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tokenId")
	private String tokenId;
	
	@Column(name="accountId")
	private String accountId;
	
	@Column(name="accessToken")
	private String accessToken;
	
	@Column(name="isActive")
	private boolean isActive;
	
	public AccountToken() {}

	public AccountToken(String tokenId, String accountId, String accessToken, boolean isActive) {
		super();
		this.tokenId = tokenId;
		this.accountId = accountId;
		this.accessToken = accessToken;
		this.isActive = isActive;
	}

	public AccountToken(String accountId, String accessToken, boolean isActive) {
		this.tokenId = "AT-" + UUID.randomUUID().toString();
		this.accountId = accountId;
		this.accessToken = accessToken;
		this.isActive = isActive;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AccountToken [id=" + id + ", tokenId=" + tokenId + ", accountId=" + accountId + ", accessToken="
				+ accessToken + ", isActive=" + isActive + "]";
	}
	
	
	
	
}
