package org.springframework.social.intel.curation.api.impl;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.intel.curation.api.CurationOperation;
import org.springframework.web.client.RestTemplate;

public class CurationOperationImpl implements CurationOperation {
	protected final RestTemplate restTemplate;
	protected final boolean isAuthorizedForUser;

	public CurationOperationImpl(RestTemplate restTemplate, boolean isAuthorizedForUser) {
		this.restTemplate = restTemplate;
		this.isAuthorizedForUser = isAuthorizedForUser;
	}
	
	protected String getContextBaseUrl() {
		return IntelConstants.host+"/curation/v1/me/lists";
	}
	

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void createList(String name, boolean isPublic) {
		JSONObject json = new JSONObject();
		
		json.put("Name",name);
		json.put("Fields","");
		json.put("Public",isPublic);
		
        JSONObject responseObject = restTemplate.postForEntity(getContextBaseUrl(), json, JSONObject.class).getBody();    
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void addFieldToList(String name, String type, String listId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}
		JSONObject fields = new JSONObject();
		fields.put("Type", type);
		
		JSONObject json = new JSONObject();
		json.put("Fields", fields);
		
		JSONObject responseObject = restTemplate.postForEntity(getContextBaseUrl()+"/"+listId, json, JSONObject.class).getBody();

	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void addItem(String listId, Map<String, Object> object) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/items
	  Map<String, Object> responseObject = restTemplate.postForEntity(getContextBaseUrl()+"/"+listId+"/items", object, Map.class).getBody();
		
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void addItem(String listId, String catalogItemId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/items
		JSONObject json = new JSONObject();
		json.put("CatalogUri", catalogItemId);
		JSONObject responseObject = restTemplate.postForEntity(getContextBaseUrl()+"/"+listId+"/items", json, JSONObject.class).getBody();

	}
	
	@Override
	public JSONArray getItem(String listId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/items
		JSONArray responseObject = restTemplate.getForEntity(getContextBaseUrl()+"/"+listId+"/items", JSONArray.class).getBody();
		return responseObject;
	}
	

	@Override
	public void deleteItem(String listId, String itemId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/items/{itemId}
	 restTemplate.delete(getContextBaseUrl()+"/"+listId+"/items/"+itemId);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void renameList(String listId, String newName) {
		//https://api.intel.com/curation/v1/me/lists/{listId}
		JSONObject json = new JSONObject();
		json.put("Name", newName);
		restTemplate.put(getContextBaseUrl()+"/"+listId, json);
	}

	@Override
	public void deleteList(String listId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}
		restTemplate.delete(getContextBaseUrl()+"/"+listId);

	}


	@SuppressWarnings("unused")
	@Override
	public void getListVersions(String listId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/versions
		JSONObject responseObject = restTemplate.getForEntity(getContextBaseUrl()+"/"+listId+"/versions", JSONObject.class).getBody();
	}

	@SuppressWarnings("unused")
	@Override
	public void getListMetaData(String listId) {
		//https://api.intel.com/curation/v1/me/lists/{ListId}
		JSONObject responseObject = restTemplate.getForEntity(getContextBaseUrl()+"/"+listId, JSONObject.class).getBody();

	}


	@SuppressWarnings("unused")
	@Override
	public Map<String, Object>[] getListItems(String listId, String versionId) {
		//https://api.intel.com/curation/v1/me/lists/{listId}/items/{versionId}
		JSONArray responseObject = restTemplate.getForEntity(getContextBaseUrl()+"/"+listId+"/items/"+versionId, JSONArray.class).getBody();
		return null;
	}

}
