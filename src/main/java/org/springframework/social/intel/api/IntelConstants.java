package org.springframework.social.intel.api;

public class IntelConstants {
	public static final String DEV = "https://api.intel.com:8081";
	public static final String PROD = "https://api.intel.com";
	public static final String host = System.getProperty("intel.url",DEV);
}
