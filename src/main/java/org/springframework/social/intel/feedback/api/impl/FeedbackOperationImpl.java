package org.springframework.social.intel.feedback.api.impl;

import java.util.Date;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.intel.context.api.impl.BaseOperationCenter;
import org.springframework.social.intel.context.api.json.DataHolder;
import org.springframework.social.intel.context.api.json.ItemHolder;
import org.springframework.social.intel.context.api.json.ValueHolder;
import org.springframework.social.intel.feedback.api.FeedbackOperation;
import org.springframework.web.client.RestTemplate;

public class FeedbackOperationImpl  extends BaseOperationCenter implements FeedbackOperation {

	/*protected final RestTemplate restTemplate;
	protected final boolean isAuthorizedForUser;*/

	
	public FeedbackOperationImpl(RestTemplate restTemplate, boolean isAuthorizedForUser) {
		//this.restTemplate = restTemplate;
		//this.isAuthorizedForUser = isAuthorizedForUser;
		super(restTemplate,isAuthorizedForUser);
	}

	protected String getContextBaseUrl() {
		return IntelConstants.host+"/re/v1/feedback";
	}
	
	protected String getContextBaseUrl(String user) {
		return getContextBaseUrl()+"/"+user;
	}

	@Override
	public void createAction(String name) {
		String url=	getContextBaseUrl()+"/action/";
		restTemplate.put(url, name);
	}

	@Override
	public void acceptCrossSell(String item1, String item2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptUpSell(String item1, String item2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectCrossSell(String itemId, String itemId2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectUpSell(String itemId, String itemId2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedback(String action, String item2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignore(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reject(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rate(String itemId) {
		String rate = "4";
		Date date = new Date();
		String mood="";
		String location ="";
		String activity ="";
		
	    Context status = new Context();
	    status.setActivity(activity);
	    status.setLocation(location);
	    status.setMood(mood);
	    
	    ActivityPaylod payload = new ActivityPaylod();
	    payload.setDate(date);
	    payload.setRate(rate);
	    restTemplate.put(getContextBaseUrl()+"/me/rate/"+itemId, status,payload);
	}

	@Override
	public void purchage(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void review(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptRecommandation(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPurchage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAverageRate(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unused", "unchecked"})
	@Override
	public void getReviews(String itemId) {
	
	    setMessageConverter(new TypeReference<DataHolder<ItemHolder<ValueHolder<?>[]>>>(){});
		DataHolder<ItemHolder<ValueHolder<?>[]>> responseHolder = restTemplate.getForEntity(getContextBaseUrl()+"/action/review/"+itemId, DataHolder.class).getBody();
		ItemHolder<ValueHolder<?>[]> data = responseHolder.getData();
	
	}

	@Override
	public void blackList(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blackListRemove(String itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void status() {
		String mood ="";
		String location="";
		String activity="";
		String url=	getContextBaseUrl()+"/me/status/";
		Context contextStatus = new Context();
		contextStatus.setActivity(activity);
		contextStatus.setLocation(location);
		contextStatus.setMood(mood);
		restTemplate.put(url, contextStatus);
		
	}
	
}
