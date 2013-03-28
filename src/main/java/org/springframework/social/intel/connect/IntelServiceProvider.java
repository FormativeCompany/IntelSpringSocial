package org.springframework.social.intel.connect;

import org.springframework.social.intel.api.Intel;
import org.springframework.social.intel.api.impl.IntelTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Intel ServiceProvider implementation.
 * 
 * @author Michael Lavelle
 */
public class IntelServiceProvider extends AbstractOAuth2ServiceProvider<Intel> {

	public IntelServiceProvider(String clientId, String clientSecret) {
		super(new IntelOAuth2Template(clientId, clientSecret));
	}

	@Override
	public Intel getApi(String accessToken) {
		return new IntelTemplate(accessToken);
	}

}
