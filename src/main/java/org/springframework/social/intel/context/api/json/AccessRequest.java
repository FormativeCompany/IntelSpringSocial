package org.springframework.social.intel.context.api.json;

public class AccessRequest {
	AccessPermission[] permissions;
	AccessReason reason;
	
	
	public AccessPermission[] getPermissions() {
		return permissions;
	}
	
	public void setPermissions(AccessPermission[] permissions) {
		this.permissions = permissions;
	}
	
	public AccessReason getReason() {
		return reason;
	}
	
	public void setReason(AccessReason reason) {
		this.reason = reason;
	}
	
}
