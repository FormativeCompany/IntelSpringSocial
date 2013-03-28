package org.springframework.social.intel.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.intel.connect.OAuth2Legged;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

public class Base2leggedService {
	
	private RestTemplate temp;
	private OAuth2Legged oauth;
	
	public Base2leggedService(final OAuth2Legged oauth) {
		super();
		this.oauth = oauth;
		this.temp = new RestTemplate();
		
		
		this.temp.setRequestFactory(new OAuth2ClientHttpRequestFactory(oauth));
	}
	
	protected OAuth2Legged getOauth() {
		return oauth;
	}

	protected RestTemplate getTemp() {
		return temp;
	}

	public static final class OAuth2ClientHttpRequestFactory implements ClientHttpRequestFactory {
		private final OAuth2Legged oauth;
		private final ClientHttpRequestFactory delegate = ClientHttpRequestFactorySelector.getRequestFactory();

		private OAuth2ClientHttpRequestFactory(OAuth2Legged oauth) {
			this.oauth = oauth;
		}

		@Override
		public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
			return new OAuth2SigningRequest(delegate.createRequest(uri, httpMethod), oauth.getAccessToken(), OAuth2Version.BEARER);
		}
	}

	public static class OAuth2SigningRequest implements ClientHttpRequest {
		
		private final ClientHttpRequest delegate;

		private ByteArrayOutputStream bodyOutputStream;
		
		private final String accessToken;
		
		private final OAuth2Version oauth2Version;

		public OAuth2SigningRequest(ClientHttpRequest delegate, String accessToken, OAuth2Version oauth2Version) {
			this.delegate = delegate;
			this.accessToken = accessToken;
			this.oauth2Version = oauth2Version;
			this.bodyOutputStream = new ByteArrayOutputStream();
		}

		public ClientHttpResponse execute() throws IOException {
			byte[] bufferedOutput = bodyOutputStream.toByteArray();
			delegate.getBody().write(bufferedOutput);
			delegate.getHeaders().set("Accept","application/json");
			delegate.getHeaders().set("Authorization", oauth2Version.getAuthorizationHeaderValue(accessToken));
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
