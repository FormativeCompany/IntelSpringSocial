package org.springframework.social.intel.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.intel.api.Intel;
import org.springframework.social.intel.api.impl.xml.UserInfo;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Intel ApiAdapter implementation.
 * 
 * @author Michael Lavelle
 */
public class IntelAdapter implements ApiAdapter<Intel> {

	@Override
	public UserProfile fetchUserProfile(Intel intel) {
		UserInfo intelProfile = intel.meOperations().getUserInfo();
		return new UserProfileBuilder().setName(intelProfile.getDisplayName())
				.setUsername(intelProfile.getUserName())
				.setFirstName(intelProfile.getFirstName())
				.setLastName(intelProfile.getLastName()).setEmail(intelProfile.getEmailAddress()).build();
		
	}

	@Override
	public void setConnectionValues(Intel intel, ConnectionValues values) {
		UserInfo intelProfile = intel.meOperations().getUserInfo();
		values.setProviderUserId(intelProfile.getUserId());
		values.setDisplayName(intelProfile.getDisplayName());
		values.setProfileUrl(null);
		values.setImageUrl(null);
	}

	@Override
	public boolean test(Intel intel) {
		try {
			intel.meOperations().getUserProfileBasic();
			return true;
		} catch (ApiException e) {
			return false;
		}
	}

	@Override
	public void updateStatus(Intel intel, String arg1) {

	}

}
