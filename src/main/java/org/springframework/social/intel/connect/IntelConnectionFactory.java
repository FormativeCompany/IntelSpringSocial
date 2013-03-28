package org.springframework.social.intel.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.intel.api.Intel;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Intel ConnectionFactory implementation.
 * 
 * @author Michael Lavelle
 */
public class IntelConnectionFactory extends OAuth2ConnectionFactory<Intel> {

	public IntelConnectionFactory(String clientId, String clientSecret) {
		super("intel", new IntelServiceProvider(clientId, clientSecret),
				new IntelAdapter());
	}

}
