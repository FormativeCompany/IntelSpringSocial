package org.springframework.social.intel.api.impl.xml;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Pojo for binding to an XML Account element
 * 
 * @author Michael Lavelle
 */
public class Account {
	
	private String provider;
	private String name;
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
