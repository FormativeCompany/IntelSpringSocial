package org.springframework.social.intel.api;

import org.springframework.social.intel.api.impl.xml.IntelXmlProfile;
import org.springframework.social.intel.api.impl.xml.IntelXmlUser;
import org.springframework.social.intel.api.impl.xml.UserInfo;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Defines operations for interacting with the current authenticated Intel User
 * 
 * @author Michael Lavelle
 */
public interface MeOperations {
	public UserInfo getUserInfo();
	
	//https://api.intel.com/profile/v2/users/{user-id}/fullprofile
	//PUT
	
	public void updateUserProfile(IntelXmlProfile prof);
	
	//GET https://api.intel.com/profile/v2/users/me/fullprofile
		/**
	 <userProfile>
		<basic>
			<firstName>Jhon</firstName>
			<lastName>Doe</lastName>
		</basic>
		<personal>
			<birthday>1971/09/08</birthday>
			<maritalStatus>Single</maritalStatus>
			<education>PHD</education>
			<occupation>Technical Leader</occupation>
			<language>English</language>
			<gender>male</gender>
		</personal>
		<extended>
			<location>
				<country>US</country>
				<!--ISO 3166-1 alpha-2: United States -->
				<locality>New York</locality>
				<region>none</region>
				<street>5th Avenue</street>
				<zipCode>90210</zipCode>
			</location>
			<telephone>
				<mobile>9938373</mobile>
				<home>4545848</home>
				<work/>
			</telephone>
		</extended>
	</userProfile>
		 */
	public IntelXmlProfile getUserProfileFull();
	
	//https://api.intel.com/profile/v2/users/{user-id}/basic
	public IntelXmlProfile getUserProfileBasic();
	
	//https://api.intel.com/profile/v2/applications
	
	
	//POST https://api.intel.com/profile/v2/applications/{client_id}/users/{user-id}?key={key} {content String}
	
//	POST https://api.intel.com/profile/v2/applications/self/users/me
//		?key=myKey HTTP/1.1
//		Authorization: Bearer gAAAACBqxbNM6gQ-uZVs5IDM
//		Content-type: image/png
//		V2tSS1Zsb3hiRmxUYlhoS1VqTm9jMWRxU25Oa2JVcHVVRlF3UFE9PQ==
	public void setData(String key, String value);
	
//	PUT https://api.intel.com/profile/v2/applications/{client_id}/users/{user-id} ?key={key}
//	PUT https://api.intel.com/profile/v2/applications/self/users/me
//		?key=myKey HTTP/1.1
//		Authorization: Bearer gAAAACBqxbNM6gQ-uZVs5IDM
//		Content-type: image/png
//		V2tSS1Zsb3hiRmxUYlhoS1VqTm9jMWRxU25Oa2JVcHVVRlF3UFE9PQ==
	public void updateData(String key, String value);
	
	
	//GET https://api.intel.com/profile/v2/applications/{client_id}/users/{user-id}
	public String getData(String key);
	
	//DELETE https://api.intel.com/profile/v2/applications/{client_id}/users/{user-id} ?key={key}
	public void deleteData(String key);
	
	//GET https://api.intel.com/identity/v2/user
/*	<user>
		<claimId>2b519ec9-e6da-11e1-8df1-0050569440a9</claimId>
		<accounts>
		<account provider="intelId">username</account>
		<account provider="google">ProviderName</account>
		</accounts>
		<validated>true</validated>
		<!-- true/false/derived. Derived means that this account was validated by an external Identity Provider, such as Facebook-->
	</user>
*/
	public IntelXmlUser getUserBasicData();
	
	//https://api.intel.com/identity/v2/logout?callbackUrl={callbackUrl}&clientIdentifier={clientIdentifier}
	public void logOut();
	
	public void updateDeleteData(String key, String photoId);
}
