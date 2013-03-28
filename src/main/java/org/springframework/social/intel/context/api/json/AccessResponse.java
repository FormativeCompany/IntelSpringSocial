package org.springframework.social.intel.context.api.json;

import java.util.Date;

public class AccessResponse {
//  "lang": "en",
	String lang;
	
//  "privacyPolicyUrl": null,
	String privacyPolicyUrl;
	
//  "requesterInstanceId": "test instance",
	String requesterInstanceId;
	
//  "resourceOwnerId": "test-owner",
	String resourceOwnerId;
	
//  "requesterUserId": "test-user",
	String requesterUserId;
	
//  "state": "approved",
	String state;
	
//  "optionLabel": null,
	String optionLabel;
	
//  "reason": "These permissions are needed to enable access policy management",
	String reason;
	
//  "modifiedDate": "2012-09-19T22:49:15Z",
	Date modifiedDate;
	
//  "requesterClientId": "test-client",
	String requesterClientId;
	
//  "createdDate": "2012-09-19T22:49:15Z",
	Date createdDate;
	
//  "id": "7ce9e320075011e292840a9b74ce8e6a",
	String id;

	AccessPermission[] permissions;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPrivacyPolicyUrl() {
		return privacyPolicyUrl;
	}

	public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}

	public String getRequesterInstanceId() {
		return requesterInstanceId;
	}

	public void setRequesterInstanceId(String requesterInstanceId) {
		this.requesterInstanceId = requesterInstanceId;
	}

	public String getResourceOwnerId() {
		return resourceOwnerId;
	}

	public void setResourceOwnerId(String resourceOwnerId) {
		this.resourceOwnerId = resourceOwnerId;
	}

	public String getRequesterUserId() {
		return requesterUserId;
	}

	public void setRequesterUserId(String requesterUserId) {
		this.requesterUserId = requesterUserId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOptionLabel() {
		return optionLabel;
	}

	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRequesterClientId() {
		return requesterClientId;
	}

	public void setRequesterClientId(String requesterClientId) {
		this.requesterClientId = requesterClientId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccessPermission[] getPermissions() {
		return permissions;
	}

	public void setPermissions(AccessPermission[] permissions) {
		this.permissions = permissions;
	}
}