package org.springframework.social.intel.api.impl.xml;

import java.util.List;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Pojo to bind to an Intel user
 * 
 * @author Michael Lavelle
 */
public class IntelXmlUser {
	
	private String userId;
	private String claimId;
	private List<Email> emails;
	private List<Account> accounts;
	private boolean validated;

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}


	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
