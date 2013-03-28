package org.springframework.social.intel.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.intel.api.Intel;
import org.springframework.social.intel.api.MeOperations;
import org.springframework.social.intel.api.impl.xml.Account;
import org.springframework.social.intel.api.impl.xml.AccountConverter;
import org.springframework.social.intel.api.impl.xml.Email;
import org.springframework.social.intel.api.impl.xml.EmailConverter;
import org.springframework.social.intel.api.impl.xml.IntelXmlProfile;
import org.springframework.social.intel.api.impl.xml.IntelXmlUser;
import org.springframework.social.intel.context.api.ContextOperations;
import org.springframework.social.intel.context.api.impl.ContextOperationImpl;
import org.springframework.social.intel.curation.api.CurationOperation;
import org.springframework.social.intel.curation.api.impl.CurationOperationImpl;
import org.springframework.social.intel.feedback.api.FeedbackOperation;
import org.springframework.social.intel.feedback.api.RecommandationOperation;
import org.springframework.social.intel.feedback.api.impl.FeedbackOperationImpl;
import org.springframework.social.intel.feedback.api.impl.RecommandationOperationImpl;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.converters.ConverterMatcher;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * This is the central class for interacting with Intel
 * 
 * @author Michael Lavelle
 */
public class IntelTemplate extends AbstractOAuth2ApiBinding implements Intel {

	private ObjectMapper objectMapper;

	private MeOperations meOperations;
	private ContextOperations contextOperations1;
	private FeedbackOperation feedbackOperation;

	private RecommandationOperation recommandationOperation;
	private CurationOperation curationOperation;
	
	private void initSubApis(String accessToken) {
		meOperations = new MeTemplate(getRestTemplate(), isAuthorized());
		contextOperations1 = new ContextOperationImpl(getRestTemplate(), isAuthorized());
		feedbackOperation = new FeedbackOperationImpl(getRestTemplate(), isAuthorized());
		recommandationOperation = new RecommandationOperationImpl(getRestTemplate(), isAuthorized());
		curationOperation = new CurationOperationImpl(getRestTemplate(), isAuthorized());
	}

	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super
				.getMessageConverters();

		XStreamMarshaller marshaller = new XStreamMarshaller();

		Map<String, Object> aliases = new HashMap<String, Object>();
		aliases.put("userProfile", IntelXmlProfile.class.getName());
		aliases.put("user", IntelXmlUser.class.getName());

		aliases.put("email", Email.class.getName());
		aliases.put("account", Account.class.getName());
		aliases.put("accounts", List.class.getName());
		aliases.put("emails", List.class.getName());

		marshaller.setConverters(new ConverterMatcher[] {new EmailConverter(),new AccountConverter()});

		try {
			marshaller.setAliases(aliases);
		} catch (ClassNotFoundException e) {
			// Shouldn't happen
		}
		messageConverters.add(new MarshallingHttpMessageConverter(marshaller,
				marshaller));
		return messageConverters;
	}

	/**
	 * Create a new instance of IntelTemplate. This constructor creates a new
	 * IntelTemplate able to perform unauthenticated operations against Intel's
	 * API. Some operations do not require authentication.: A IntelTemplate
	 * created with this constructor will support those operations. Those
	 * operations requiring authentication will throw
	 * {@link NotAuthorizedException}.
	 */
	public IntelTemplate() {
		super(null);
		initialize(null);
	}

	/**
	 * Create a new instance of IntelTemplate. This constructor creates the
	 * IntelTemplate using a given access token.
	 * 
	 * @param accessToken
	 *            An access token given by Intel after a successful
	 *            authentication
	 */
	public IntelTemplate(String accessToken) {
		super(accessToken);
		initialize(accessToken);

	}

	// private helpers
	private void initialize(String accessToken) {
		registerIntelJsonModule(getRestTemplate());
		// Wrap the request factory with a BufferingClientHttpRequestFactory so
		// that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector
				.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis(accessToken);
	}

	private void registerIntelJsonModule(RestTemplate restTemplate2) {
		objectMapper = new ObjectMapper();
		List<HttpMessageConverter<?>> converters = getRestTemplate()
				.getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJacksonHttpMessageConverter) {
				MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
				jsonConverter.setObjectMapper(objectMapper);
			}
		}

	}

	@Override
	public MeOperations meOperations() {
		return meOperations;
	}

	@Override
	public FeedbackOperation feedback() {
		return feedbackOperation;
	}
	
	public RecommandationOperation recommandation() {
		return recommandationOperation;
	}
	
	@Override
	public CurationOperation curation() {
		return curationOperation;
	}
	
	@Override
	public ContextOperations contextOperations() {
		return contextOperations1;
	}
}
