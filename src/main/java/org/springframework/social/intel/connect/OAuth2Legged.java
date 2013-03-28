package org.springframework.social.intel.connect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

public class OAuth2Legged {
	private String accessToken;
	private String refreshToken;
	private String scope;
	private int expireIn;
	
	private boolean authorized = false;

	private String apiKey;
	private String secretKey;

	private Date created;
	
	public OAuth2Legged(String apiKey, String secretKey) {
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public String getSecretKey() {
		return secretKey;
	}

	public Date getCreated() {
		return created;
	}
	
	public int getExpireIn() {
		return expireIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public String getScope() {
		return scope;
	}
	
	public boolean isAuthorized() {
		return authorized;
	}
	
	public void authorized() {
		this.created = new Date();
		Map<String, Object> values = build();
		this.scope = (String)values.get("scope");
		this.accessToken = (String)values.get("token");
		this.refreshToken = (String)values.get("refresh_token");
		this.expireIn = ((Number)values.get("expires_in")).intValue();
		this.authorized = true;
		
		System.out.println("Catalog Token Refreshed "+created);
		System.out.println("-------------------------------------");
		System.out.println("Access Token  : "+accessToken);
		System.out.println("Refresh Token : "+refreshToken);
		System.out.println("Expire Time   : "+expireIn);
		System.out.println("Token Scope   : "+scope);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String,Object> build() {
		String url =  String.format("%s/oauth20/token?client_id=%s&client_secret=%s&grant_type=%s&scope=%s",IntelConstants.host,apiKey,secretKey,"client_credentials","catalog:basic");
		Map body = new RestTemplate(new OAuth2ClientHttpRequestFactory()).postForEntity(url, null, Map.class).getBody();
		body = (Map)body.get("OAuth20");
		body = (Map)body.get("access_token");
		return body;
	}
	
	public void refresh() {

	}
	
	public static final class OAuth2ClientHttpRequestFactory implements ClientHttpRequestFactory {
		private final ClientHttpRequestFactory delegate = ClientHttpRequestFactorySelector.getRequestFactory();

		private OAuth2ClientHttpRequestFactory() {
		}

		@Override
		public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
			return new OAuth2SigningRequest(delegate.createRequest(uri, httpMethod));
		}
	}

	public static class OAuth2SigningRequest implements ClientHttpRequest {
		
		private final ClientHttpRequest delegate;

		private ByteArrayOutputStream bodyOutputStream;
		
		public OAuth2SigningRequest(ClientHttpRequest delegate) {
			this.delegate = delegate;
			this.bodyOutputStream = new ByteArrayOutputStream();
		}

		public ClientHttpResponse execute() throws IOException {
			byte[] bufferedOutput = bodyOutputStream.toByteArray();
			delegate.getBody().write(bufferedOutput);
			delegate.getHeaders().set("Accept","application/json");
			return delegate.execute();
		}

		public URI getURI() {
			return delegate.getURI();
		}

		public HttpMethod getMethod() {
			return delegate.getMethod();
		}

		public HttpHeaders getHeaders() {
			return delegate.getHeaders();
		}

		public OutputStream getBody() throws IOException {
			return bodyOutputStream;
		}
		
	}

}
