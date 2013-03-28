package org.springframework.social.intel.context.api.json;

public class UserInstanceInfo {
	String id;
	String instanceIds[];
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String[] getInstanceIds() {
		return instanceIds;
	}
	
	public void setInstanceIds(String[] instanceIds) {
		this.instanceIds = instanceIds;
	}
}
