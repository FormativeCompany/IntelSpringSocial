package org.springframework.social.intel.context.api.json;

import java.util.List;

public class AccessPermission {
	List<String> access;
	String resourceUri;
	
	public List<String> getAccess() {
		return access;
	}
	
	public void setAccess(List<String> access) {
		this.access = access;
	}
	
	public String getResourceUri() {
		return resourceUri;
	}
	
	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
}
