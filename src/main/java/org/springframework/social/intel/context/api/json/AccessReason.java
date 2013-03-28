package org.springframework.social.intel.context.api.json;

public class AccessReason {
	String en;
	
	public AccessReason() {
	}
	
	public AccessReason(String reason) {
		this.en = reason;
	}
	
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	
}
