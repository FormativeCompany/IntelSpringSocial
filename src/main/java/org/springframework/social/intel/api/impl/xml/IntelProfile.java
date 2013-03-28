package org.springframework.social.intel.api.impl.xml;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Model class containing a Intel user's profile information.
 * 
 * @author Michael Lavelle
 */
public class IntelProfile {

	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
