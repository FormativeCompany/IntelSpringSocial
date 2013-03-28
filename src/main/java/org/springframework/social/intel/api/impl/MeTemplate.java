package org.springframework.social.intel.api.impl;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.intel.api.MeOperations;
import org.springframework.social.intel.api.impl.xml.IntelProfile;
import org.springframework.social.intel.api.impl.xml.IntelXmlProfile;
import org.springframework.social.intel.api.impl.xml.IntelXmlUser;
import org.springframework.social.intel.api.impl.xml.UserInfo;
import org.springframework.web.client.RestTemplate;
import static org.springframework.social.intel.api.IntelConstants.host;
/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * This is the central class for interacting with the currently signed in Intel
 * user
 * 
 * @author Michael Lavelle
 */
public class MeTemplate implements MeOperations {
	protected final RestTemplate restTemplate;
	protected final boolean isAuthorizedForUser;

	public MeTemplate(RestTemplate restTemplate,
			boolean isAuthorizedForUser) {
		this.restTemplate = restTemplate;
		this.isAuthorizedForUser = isAuthorizedForUser;
	}

	protected void requireAuthorization() {
		if (!isAuthorizedForUser) {
			throw new MissingAuthorizationException();
		}
	}

	@Override
	public UserInfo getUserInfo() {
		IntelXmlProfile xmlProfile = getUserProfileFull();
		IntelXmlUser    xmlUser = getUserBasicData();
		
		IntelProfile profile = xmlProfile.getBasic();

		UserInfo ui = new UserInfo();
		ui.setUserId(xmlUser.getUserId());
		ui.setFirstName(profile.getFirstName());
		ui.setLastName(profile.getLastName());
		populateOther(xmlUser, ui);
		return ui;
		
	}

	private void populateOther(IntelXmlUser xmlUser, UserInfo profile) {
		if (profile.getFirstName() != null && profile.getLastName() != null) {
			profile.setUserName(profile.getFirstName().toLowerCase() + profile.getLastName().toLowerCase());
			profile.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
		}
		
		if (!xmlUser.getEmails().isEmpty()) {
			String firstEmailAddress = xmlUser.getEmails().get(0).getEmail();
			profile.setEmailAddress(firstEmailAddress);
		}
	}

	@Override
	public IntelXmlProfile getUserProfileFull() {
		return restTemplate.getForObject(host+"/profile/v2/users/me/fullprofile", IntelXmlProfile.class);
	}

	@Override
	public IntelXmlProfile getUserProfileBasic() {
		return restTemplate.getForObject(host + "/profile/v2/users/me/basic", IntelXmlProfile.class);
	}

	@Override
	public void updateUserProfile(IntelXmlProfile prof) {
		restTemplate.put(host + "/profile/v2/users/me/fullprofile", prof);
	}

	@Override
	public void setData(String key, String value) {
		restTemplate.postForLocation(host+"/profile/v2/applications/self/users/me?key="+key, value);		
	}

	@Override
	public void updateData(String key, String value) {
		restTemplate.put(host+"/profile/v2/applications/self/users/me?key="+key, value);
	}

	@Override
	public String getData(String key) {
//		return restTemplate.getForObject(host+"/profile/v2/applications/self/users/me?key="+key, String.class);
		return restTemplate.getForObject(host+"/profile/v2/applications/self/users/me", String.class);
	}

	@Override
	public void deleteData(String key) {
		restTemplate.delete(host+"/profile/v2/applications/self/users/me?key="+key);
	}

	
	public void updateDeleteData(String key, String photoId) {
		if(photoId != null) {
			if(getData(key) != null) {
				deleteData(key);
			}
		} else {
			setData(key, photoId);
		}
	}

	@Override
	public IntelXmlUser getUserBasicData() {
		return restTemplate.getForObject(host+"/identity/v2/user", IntelXmlUser.class);
	}
	
	public String getScope() {
		return restTemplate.getForObject(host+"/identity/v2/user/scope", String.class);
		/*<scopesResult>
		<scopes>
		<scope>profile:basic</scope>
		<scope>profile:full</scope>
		</scopes>
		</scopesResult>
		*/
	}

	@Override
	public void logOut() {
	}
}