package org.springframework.social.intel.context.api.impl;

import java.util.ArrayList;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.intel.api.utilities.NewJSonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings({"rawtypes"})
public class BaseOperationCenter {

	protected RestTemplate restTemplate; 
	
	protected boolean authorized;

	public BaseOperationCenter(RestTemplate restTemplate, boolean authorized) {
		this.restTemplate = restTemplate;
		this.authorized = authorized;
	}
	
	protected void setMessageConverter() {
		MappingJacksonHttpMessageConverter mc = new NewJSonHttpMessageConverter();
		ArrayList<HttpMessageConverter<?>> amc = new ArrayList<HttpMessageConverter<?>>();
		amc.add(mc);
		
		restTemplate.setMessageConverters(amc);
	}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	public boolean isAuthorized() {
		return authorized;
	}
	
	protected void setMessageConverter(Class c, Class c1) {
		MappingJacksonHttpMessageConverter mc = new NewJSonHttpMessageConverter(c, new Class[]{c1});
		ArrayList<HttpMessageConverter<?>> amc = new ArrayList<HttpMessageConverter<?>>();
		amc.add(mc);
		
		restTemplate.setMessageConverters(amc);
	}
	
	protected void setMessageConverter(TypeReference<?> ref) {
		MappingJacksonHttpMessageConverter mc = new NewJSonHttpMessageConverter(ref);
		ArrayList<HttpMessageConverter<?>> amc = new ArrayList<HttpMessageConverter<?>>();
		amc.add(mc);
		
		restTemplate.setMessageConverters(amc);
	}
	
	protected void setMessageConverter(Class c, Class[] c1) {
		MappingJacksonHttpMessageConverter mc = new NewJSonHttpMessageConverter(c, c1);
		ArrayList<HttpMessageConverter<?>> amc = new ArrayList<HttpMessageConverter<?>>();
		amc.add(mc);
		
		restTemplate.setMessageConverters(amc);
	}
	
	protected void setMessageConverter(Class c[], Class[][] c1) {
		MappingJacksonHttpMessageConverter mc = new NewJSonHttpMessageConverter(c, c1);
		ArrayList<HttpMessageConverter<?>> amc = new ArrayList<HttpMessageConverter<?>>();
		amc.add(mc);
		
		restTemplate.setMessageConverters(amc);
	}
}
