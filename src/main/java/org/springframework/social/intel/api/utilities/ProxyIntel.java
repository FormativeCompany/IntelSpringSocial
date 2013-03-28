package org.springframework.social.intel.api.utilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.social.intel.api.Intel;
import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.intel.api.MeOperations;
import org.springframework.social.intel.api.impl.IntelTemplate;
import org.springframework.social.intel.context.api.ContextOperations;
import org.springframework.social.intel.curation.api.CurationOperation;
import org.springframework.social.intel.feedback.api.FeedbackOperation;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

abstract public class ProxyIntel implements Intel {
	
	public static final String url = IntelConstants.host+"/identity/v2/token";

	private IntelTemplate it;

	public ProxyIntel(IntelTemplate it) {
		this.it = it;
	}

	@Override
	public MeOperations meOperations() {
		return prox(it.meOperations(), MeOperations.class);
	}

	@Override
	public FeedbackOperation feedback() {
		return prox(it.feedback(), FeedbackOperation.class);
	}

	@Override
	public CurationOperation curation() {
		return prox(it.curation(), CurationOperation.class);
	}

	@Override
	public ContextOperations contextOperations() {
		return prox(it.contextOperations(), ContextOperations.class);
	}

	@SuppressWarnings("unchecked")
	private <T> T prox(final T target, Class<T> c) {
		ClassLoader classLoader = target.getClass().getClassLoader();
		Class<?>[] classes = new Class[] { c };
		T proxy = (T) Proxy.newProxyInstance(classLoader, classes, new InvocationHandler() {
			
			private Object wrap = target;
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					return limitedCounter(method, args, 4);
			}

			private Object limitedCounter(Method method, Object[] args, int count) throws Throwable {
				if(count == 0) {
					throw new StackOverflowError("Refreshing action might taking long time");
				}
				
				Object pi = invokeIt(wrap, method, args);
				if(pi instanceof ProxyIntel) {
					ProxyIntel b = (ProxyIntel) pi;
					if(wrap instanceof MeOperations) {
						wrap = b.meOperations();
					} else if(wrap instanceof ContextOperations) {
						wrap = b.contextOperations();
					} else if(wrap instanceof CurationOperation) {
						wrap = b.curation();
					} else if(wrap instanceof FeedbackOperation) {
						wrap = b.feedback();
					}
					return limitedCounter(method, args, --count);
				} else {
					return pi;
				}
			}
		});
		return proxy;
	}

	public Object invokeIt(Object original, Method method, Object[] args) throws Throwable {
		try {
			return method.invoke(original, args);
		} catch (InvocationTargetException  e1) {
			Throwable cause = e1.getCause();
			
			if(cause instanceof org.springframework.web.client.HttpClientErrorException) {
				HttpClientErrorException e = (HttpClientErrorException) cause;
				if (!HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
					throw cause;
				} else {
					if(!isExpired()) {
						throw cause;
					}
					ProxyIntel b = doRefresh();
					if (b == null) {
						throw cause;
					} else {
						return b;
					}
				}
			} else {
				throw cause;
			}
		} 
	}

	@SuppressWarnings("unchecked")
	protected Map<String, String> refreshToken(String clientId, String secretKey, String refreshToken) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("client_id", clientId);
		params.set("client_secret", secretKey);
		params.set("refresh_token", refreshToken);
		params.set("scope", "user:details");
		params.set("grant_type", "refresh_token");

		RestTemplate template = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		Map<String, String> value = (Map<String, String>)template.postForObject(url, params, Map.class);
		return value;
	}

	abstract protected boolean isExpired();
	abstract protected ProxyIntel doRefresh();
}