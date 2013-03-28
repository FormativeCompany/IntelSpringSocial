package org.springframework.social.intel.api.utilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.social.intel.catalog.api.CatalogOperation;
import org.springframework.social.intel.catalog.api.CatalogOperationImpl;
import org.springframework.social.intel.connect.OAuth2Legged;
import org.springframework.web.client.HttpClientErrorException;

public class RefreshableCatalog {
	private OAuth2Legged oauthToken;

	private int maximumRefreshEffort = 4;
	
	public int getMaximumRefreshEffort() {
		return maximumRefreshEffort;
	}
	
	public void setMaximumRefreshEffort(int maximumRefreshEffort) {
		this.maximumRefreshEffort = maximumRefreshEffort;
	}
	
	public RefreshableCatalog(OAuth2Legged l) {
		this.oauthToken = l;
	}

	public CatalogOperation getRefreshable() {
		return prox(new CatalogOperationImpl(oauthToken), CatalogOperation.class);
	}

	@SuppressWarnings("unchecked")
	private <T> T prox(final T target, Class<T> c) {
		ClassLoader classLoader = target.getClass().getClassLoader();
		Class<?>[] classes = new Class[] { c };
		T proxy = (T) Proxy.newProxyInstance(classLoader, classes, new InvocationHandler() {

			private Object wrap = target;

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return limitedCounter(method, args, getMaximumRefreshEffort());
			}

			private Object limitedCounter(Method method, Object[] args, int count) throws Throwable {
				if (count == 0) {
					throw new StackOverflowError("Refreshing action might taking long time");
				}

				Object pi = invokeIt(wrap, method, args);
				if (pi instanceof CatalogOperation) {
					wrap = (CatalogOperation) pi;
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
		} catch (InvocationTargetException e1) {
			Throwable cause = e1.getCause();

			if (cause instanceof org.springframework.web.client.HttpClientErrorException) {
				HttpClientErrorException e = (HttpClientErrorException) cause;
				if (!HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
					throw cause;
				} else {
					if (!isExpired()) {
						throw cause;
					}
					CatalogOperation b = doRefresh();
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

	protected boolean isExpired() {
		int i = oauthToken.getExpireIn();
		Date d = new Date();
		return d.getTime() > oauthToken.getCreated().getTime() + i;
	}

	protected CatalogOperation doRefresh() {
		OAuth2Legged oa = new OAuth2Legged(oauthToken.getApiKey(), oauthToken.getSecretKey());
		oa.authorized();
		CatalogOperation co = new CatalogOperationImpl(oa);
		this.oauthToken = oa;
		return co;
	}

}
