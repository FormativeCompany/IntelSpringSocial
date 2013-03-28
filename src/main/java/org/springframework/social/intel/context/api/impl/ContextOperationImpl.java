package org.springframework.social.intel.context.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.social.intel.api.ApiUtilities;
import org.springframework.social.intel.context.api.Access;
import org.springframework.social.intel.context.api.ContextOperations;
import org.springframework.social.intel.context.api.ContextParam;
import org.springframework.social.intel.context.api.ContextResources;
import org.springframework.social.intel.context.api.json.AccessPermission;
import org.springframework.social.intel.context.api.json.AccessReason;
import org.springframework.social.intel.context.api.json.AccessRequest;
import org.springframework.social.intel.context.api.json.AccessResponse;
import org.springframework.social.intel.context.api.json.DataHolder;
import org.springframework.social.intel.context.api.json.InstanceInfo;
import org.springframework.social.intel.context.api.json.ItemHolder;
import org.springframework.social.intel.context.api.json.PossiblePoi;
import org.springframework.social.intel.context.api.json.PossibleStep;
import org.springframework.social.intel.context.api.json.UserInstanceInfo;
import org.springframework.social.intel.context.api.json.ValueHolder;
import org.springframework.social.intel.context.api.json.Watch;
import org.springframework.web.client.RestTemplate;
import static org.springframework.social.intel.api.IntelConstants.host;

@SuppressWarnings("unchecked")
public class ContextOperationImpl extends BaseOperationCenter implements ContextOperations {
	
	public ContextOperationImpl(RestTemplate restTemplate, boolean authorized) {
		super(restTemplate,authorized);
	}
	
	@Override
	public AccessResponse createAccessRequest(ContextResources resources, String reason, Access... access) {
		ArrayList<String> saccess = asAccessList(access);
		
		AccessPermission perm = new AccessPermission();
		perm.setResourceUri(resources.getIntelUri());
		perm.setAccess(saccess);
		
		
		AccessRequest request = new AccessRequest();
		request.setPermissions(new AccessPermission[]{perm});
		request.setReason(new AccessReason(reason));
		
		DataHolder<AccessRequest> req = new DataHolder<AccessRequest>();
		req.setData(request);
		
		setMessageConverter(new TypeReference<DataHolder<AccessResponse>>(){});
		DataHolder<AccessResponse> responseWrapper = restTemplate.postForEntity(host+"/context/v1/accessrequests",req, DataHolder.class).getBody();
		AccessResponse response = responseWrapper.getData();
		return response;
	}

	private ArrayList<String> asAccessList(Access... access) {
		ArrayList<String> saccess = new ArrayList<String>(access.length);
		int accCount=access.length;
		for (int i = 0; i < accCount; i++) {
			saccess.add(access[i].name());
		}
		return saccess;
	}

	
	@Override
	public AccessResponse createAccessRequest(ContextResources[] resources, String reason, Access... access) {
		ArrayList<String> saccess = asAccessList(access);
		
		int resCount = resources.length;
		AccessPermission[] perm = new AccessPermission[resCount];
		ContextResources contextResources;
		for (int i = 0; i < resCount; i++) {
			contextResources = resources[i];
			perm[i] = new AccessPermission();
			perm[i].setResourceUri(contextResources.getIntelUri());
			perm[i].setAccess(saccess);
		}

		AccessRequest request = new AccessRequest();
		request.setPermissions(perm);
		request.setReason(new AccessReason(reason));
		
		DataHolder<AccessRequest> req = new DataHolder<AccessRequest>();
		req.setData(request);
		
		setMessageConverter(new TypeReference<DataHolder<AccessResponse>>(){});
		DataHolder<AccessResponse> responseWrapper = restTemplate.postForEntity(host+"/context/v1/accessrequests",req, DataHolder.class).getBody();
		AccessResponse response = responseWrapper.getData();
		return response;
	}


	@Override
	public AccessResponse getAccessRequest(String id) {
		setMessageConverter(new TypeReference<DataHolder<AccessResponse>>(){});
		DataHolder<AccessResponse> responseWrapper = restTemplate.getForEntity(host+"/context/v1/accessrequests/" +id, DataHolder.class).getBody();
		AccessResponse response = responseWrapper.getData();
		return response;
	}

	@Override
	public void deleteAccessRequest(String id) {
		restTemplate.delete(host+"/context/v1/accessrequests/" +id);
	}

	@Override
	public UserInstanceInfo getUserInfo(String id) {
		setMessageConverter(new TypeReference<DataHolder<UserInstanceInfo>>(){});
		DataHolder<UserInstanceInfo> responseWrapper = restTemplate.getForEntity(host+"/context/v1/users/" +id, DataHolder.class).getBody();
		UserInstanceInfo response = responseWrapper.getData();
		return response;
	}

	@Override
	public InstanceInfo getInstanceInfo(String id) {
		setMessageConverter(new TypeReference<DataHolder<InstanceInfo>>(){});
		DataHolder<InstanceInfo> responseWrapper = restTemplate.getForEntity(host+"/context/v1/instances/" +id, DataHolder.class).getBody();
		InstanceInfo response = responseWrapper.getData();
		return response;
	}

	@Override
	public String getTypeCatalog() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getTypeCatalog(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValueHolder<?>[] getItems(ContextParam param) {
		String url = host+"/context/v1/items"+param.toParamString();
		setMessageConverter(new TypeReference<DataHolder<ItemHolder<ValueHolder<?>[]>>>(){});
		DataHolder<ItemHolder<ValueHolder<?>[]>> responseHolder = restTemplate.getForEntity(url, DataHolder.class).getBody();
		ItemHolder<ValueHolder<?>[]> data = responseHolder.getData();
		if(data == null) return null;
		return data.getItems();
	}

	@Override
	public ValueHolder<?> getItem(String itemId) {
		setMessageConverter(new TypeReference<DataHolder<ValueHolder<?>>>(){});
		DataHolder<ValueHolder<?>> responseHolder = restTemplate.getForEntity(host+"/context/v1/items/"+itemId, DataHolder.class).getBody();
		return responseHolder.getData();
	}

	@Override
	public <T> ValueHolder<T> addItems(T value) {
		ValueHolder<T> valueHolder = new ValueHolder<T>();
		valueHolder.setValue(value);
		valueHolder.setContextType(ContextResources.url(value).getIntelUri());
		valueHolder.setClientCreatedTime(ApiUtilities.today());

		ItemHolder<ValueHolder<T>[]> itemHolder = new ItemHolder<ValueHolder<T>[]>();
		itemHolder.setItems(new ValueHolder[] { valueHolder });

		DataHolder<ItemHolder<ValueHolder<T>[]>> dataHolder = new DataHolder<ItemHolder<ValueHolder<T>[]>>();
		dataHolder.setData(itemHolder);

		setMessageConverter(new TypeReference<DataHolder<ItemHolder<ValueHolder<?>[]>>>(){});
		DataHolder<ItemHolder<ValueHolder<T>[]>> responseHolder = restTemplate.postForEntity(host+"/context/v1/items", dataHolder, dataHolder.getClass()).getBody();
		
		ItemHolder<ValueHolder<T>[]> responseItem = responseHolder.getData();
		if (responseItem == null)
			return null;
		
		ValueHolder<T>[] responseValue = responseItem.getItems();
		if (responseValue == null || responseValue.length == 0)
			return null;
		
		return responseValue[0];
	}

	@Override
	public void deleteItems(ContextParam param) {
		restTemplate.delete(host+"/context/v1/items"+param.toParamString());
	}
	
	@Override
	public void deleteItem(String itemId) {
		restTemplate.delete(host+"/context/v1/items/"+itemId);
	}

	@Override
	public List<Watch> getWatches(String id) {
		setMessageConverter(new TypeReference<DataHolder<ItemHolder<Watch[]>>>(){});
		DataHolder<ItemHolder<Watch[]>> responseWrapper = restTemplate.getForEntity(host+"/context/v1/watches?id="+id, DataHolder.class).getBody();
		ItemHolder<Watch[]> item = responseWrapper.getData();
		Watch[] response = item.getItems();
		return Arrays.asList(response);
	}

	@Override
	public Watch createWatche(Watch w) {
		DataHolder<Watch> request = new DataHolder<Watch>(w);
		
		setMessageConverter(new TypeReference<DataHolder<Watch>>(){});
		DataHolder<Watch> responseWrapper = restTemplate.postForEntity(host+"/context/v1/watches", request, DataHolder.class).getBody();
		Watch response = responseWrapper.getData();
		return response;

	}

	@Override
	public void deleteWatch(String id) {
		restTemplate.delete(host+"/context/v1/watches/"+id);
	}

	@Override
	public ValueHolder<?>[] getNewItems() {
		setMessageConverter(new TypeReference<DataHolder<ItemHolder<ValueHolder<?>[]>>>(){});
		DataHolder<ItemHolder<ValueHolder<?>[]>> responseWrapper = restTemplate.getForEntity(host+"/context/v1/watches/newitems", DataHolder.class).getBody();
		ItemHolder<ValueHolder<?>[]> data = responseWrapper.getData();
		if(data == null) return null;
		ValueHolder<?>[] response = data.getItems();
		return response;
	}

	@Override
	public PossibleStep getPossibleNextStep(String userid, double latitude, double longitude, Date time) {
		String url = String.format(host+"/context/v1/users/%1s/models/behavior/places?lat=%2s&lon=%3s&timestamp=%4s", userid, latitude,longitude,time);
		PossibleStep responseWrapper = restTemplate.getForEntity(url, PossibleStep.class).getBody();
		return responseWrapper;
	}

	@Override
	public PossiblePoi[] getPointOfInterest(String userid, double latitude, double longitude, Date time) {
		String url = String.format(host+"/context/v1/users/%1s/models/behavior/places?lat=%2s&lon=%3s&timestamp=%4s", userid, latitude,longitude,time);
		PossiblePoi[] responseWrapper = restTemplate.getForEntity(url, PossiblePoi[].class).getBody();
		return responseWrapper;
	}
}