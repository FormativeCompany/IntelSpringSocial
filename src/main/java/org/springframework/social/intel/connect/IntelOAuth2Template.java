package org.springframework.social.intel.connect;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Intel-specific extension of OAuth2Template
 * 
 * @author Michael Lavelle
 */
public class IntelOAuth2Template extends OAuth2Template {

	public IntelOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret,
				IntelConstants.host+"/identityui/v2/auth",
				IntelConstants.host+"/identity/v2/token");
		trustSelfSignedSSL();
	}

	/**
	 * TODO Temporary code which trusts Intel's Self Signed Cert Can be removed
	 * if keystores are imported into local chain, or once api cert is no longer
	 * self signed
	 */
	public static void trustSelfSignedSSL() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLContext.setDefault(ctx);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected AccessGrant createAccessGrant(String accessToken, String scope,
			String refreshToken, Integer expiresIn, Map<String, Object> response) {
		return super.createAccessGrant(accessToken, scope, refreshToken,
				expiresIn, response);
	}

	@Override
	public String buildAuthorizeUrl(GrantType grantType,
			OAuth2Parameters parameters) {
		parameters.add("scope", "user:details profile:basic profile:full profile:full:write profile:external:2a91db902e24b610a8b94e178b50d07d:read profile:external:2a91db902e24b610a8b94e178b50d07d:write");
		parameters.add("state", UUID.randomUUID().toString());
		return super.buildAuthorizeUrl(grantType, parameters);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl,
			MultiValueMap<String, String> parameters) {
		System.out.println("<!---IDP API-------->");
		
		String code = parameters.getFirst("code");
		String client_id = parameters.getFirst("client_id");
		String client_sec = parameters.getFirst("client_secret");
		String redir = parameters.getFirst("redirect_uri");
		String grantType = parameters.getFirst("grant_type");

		System.out.println("code="+code);
		System.out.println("client_id="+client_id);
		System.out.println("client_secret="+client_sec);
		System.out.println("redirect_uri="+redir);
		System.out.println("grant_type="+grantType);

		Map accessGrantResponse = getRestTemplate().postForEntity(
				accessTokenUrl, parameters, Map.class).getBody();
		
		String accessToken = (String) accessGrantResponse.get("access_token");
		String scope = (String) accessGrantResponse.get("scope");
		String refreshToken = (String) accessGrantResponse.get("refresh_token");
		Integer integerValue = getIntegerValue(accessGrantResponse, "expires_in");
		
		System.out.println("-----------------------------------");
		System.out.println("Access Token  : "+accessToken);
		System.out.println("Refresh Token : "+accessToken);
		System.out.println("Scope  : "+scope);
		System.out.println("Expire : "+integerValue);
		
		return createAccessGrant(accessToken, scope, refreshToken, integerValue, accessGrantResponse);
	}

	// Retrieves object from map into an Integer, regardless of the object's
	// actual type. Allows for flexibility in object type (eg, "3600" vs 3600).
	private Integer getIntegerValue(Map<String, Object> map, String key) {
		try {
			return Integer.valueOf(String.valueOf(map.get(key))); // normalize
																	// to String
																	// before
																	// creating
																	// integer
																	// value;
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
