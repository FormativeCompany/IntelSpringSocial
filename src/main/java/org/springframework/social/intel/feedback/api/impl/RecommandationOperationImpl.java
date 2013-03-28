package org.springframework.social.intel.feedback.api.impl;

import org.springframework.social.intel.feedback.api.RecommandationOperation;
import org.springframework.web.client.RestTemplate;

public class RecommandationOperationImpl implements RecommandationOperation {

	protected final RestTemplate restTemplate;
	protected final boolean isAuthorizedForUser;

	public RecommandationOperationImpl(RestTemplate restTemplate, boolean isAuthorizedForUser) {
		this.restTemplate = restTemplate;
		this.isAuthorizedForUser = isAuthorizedForUser;
	}

	@Override
	public void getAnonymousRecommendations(String itemId, String relationShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getEstimatedUserPreference(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRecommandationItem(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRecommendationForSimilarItems(String itemId) {
		// TODO Auto-generated method stub
		
	}

}
