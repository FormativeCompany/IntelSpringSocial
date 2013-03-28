package org.springframework.social.intel.catalog.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.social.intel.api.ApiUtilities;
import org.springframework.social.intel.api.Base2leggedService;
import org.springframework.social.intel.api.IntelConstants;
import org.springframework.social.intel.connect.OAuth2Legged;
import org.springframework.web.client.HttpClientErrorException;

public class CatalogOperationImpl extends Base2leggedService implements CatalogOperation {

	public CatalogOperationImpl(OAuth2Legged oauth) {
		super(oauth);
	}

	public void testRefresh() {
		throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
	}
	
	@Override
	public void createItemType(String name) {
		String url = IntelConstants.host + "/catalog/v1/" + name;
		getTemp().postForObject(url, "", String.class);
	}
	
//	POST https://api.intel.com/catalog/v1/{item_type’s plural name}
// Request Data	
//	{
//		"Items": [
//		{
//		"Name": "The Godfather",
//		"Year": "1972",
//		"Description": "An excellent adaptation of
//		the original classic."
//		},
//		{
//		"Name": "The lord of the rings: The
//		fellowship of the ring",
//		"Year": "1954",
//		"Description": "The first chapter in the
//		book begins in a light vein, following the tone of The Hobbit."
//		}
//		]
//		}
	
	// Returns {
//	"Items": [
//	"books.db4f3f7d-b9d7-480d-9eaf-721d3baaae95",
//	"books.db0a4b74-57e8-465c-94a2-597d2c4198f6"
//	]
//	}
	
	/**
	 * Object must not contains ItemId and _id. We will remove it.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String createItems(String name, Map<String,Object> object) {
		object.remove("ItemId");
		object.remove("_id");
		
		String json = ApiUtilities.toJson(object);
		json = "{\"Items\":["+json+"]}";
		
		String url = IntelConstants.host + "/catalog/v1/"+name;
		String s= getTemp().postForObject(url, json, String.class);

		Map<String,Object> s1 = ApiUtilities.parseJson(s);
		ArrayList<Object> al = (ArrayList<Object>) s1.get("Items");
		return (String)al.get(0);
	}

	//PUT https://api.intel.com/catalog/v1/{item_type’s plural name}
		/*
		 {
			"ItemId": "db4f3f7d-b9d7-480d-9eaf-721d3baaae95",
			"Name": "The Godfather",
			"Year": "1972",
			"Description": "An excellent adaptation of the original classic.",
			"In stock":"Yes"
		}
		*/
	/**
	 * Result must contains ItemId
	 * Input map must not contains _id attribute; It will be removed
	 */
	@Override
	public void updateItem(String name, Map<?,?> values) throws IllegalArgumentException {
		if(values.get("ItemId")==null) throw new IllegalArgumentException("Update Items must contains ItemId");
		values.remove("_id");
		String url = IntelConstants.host + "/catalog/v1/"+name;
		getTemp().put(url, values);
	}

/*	{
	    "Data": {
	        "ItemType": [{
	            "_id": "4FB3FCDE-0970-461F-8D73-F1E3093E78A7",
	            "Name": "userprofile",
	            "ApiKey": "2ADAD762-1145-1FC4-5333-02393FF0F178"
	        }, {
	            "_id": "5C121EE5-8850-4385-B4EA-5A04FDB30EE8",
	            "Name": "userprofile1",
	            "ApiKey": "2ADAD762-1145-1FC4-5333-02393FF0F178"
	        }]
	    },
	    "Meta": {
	        "Paging": {
	            "TotalItems": 2,
	            "Limit": 50,
	            "StartIndex": 0,
	            "PageNumber": 0,
	            "Previous": null,
	            "Next": null
	        },
	        "Type": "List<ItemType>"
	    }
	}*/
	
	/**
	 * Map contains {_id, Name, ApiKey}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<?,?>> listItemType() {
		String url = IntelConstants.host + "/catalog/v1";;
		Map<?,?> m = getTemp().getForObject(url, Map.class);
		m = (Map<?, ?>) m.get("Data");
		ArrayList<Map<?,?>> list = (ArrayList<Map<?, ?>>) m.get("ItemType");
		return list;
	}

	/**
	 * Map contains {_id, ItemId and all attribute of entity}
	 * This returns only 50 records, for more specific result use browse
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<?,?>> getItem(String name) {
		String url = IntelConstants.host + "/catalog/v1/" + name;
//		{ "Data" : { "UserProfile" : [{ "_id" : "userprofile.22e7d24b-6aad-4bd8-ad9c-043326643fb3", "name3" : "Rajesh4", "name" : "Rajesh1", "name1" : "Rajesh2", "name2" : "Rajesh3", "ItemId" : "22e7d24b-6aad-4bd8-ad9c-043326643fb3" }, { "_id" : "userprofile.97edb0ef-e4ff-466c-b0bc-9d1a517ff79c", "name3" : "Rajesh4", "name" : "Rajesh1", "name1" : "Rajesh2", "name2" : "Rajesh3", "ItemId" : "97edb0ef-e4ff-466c-b0bc-9d1a517ff79c" }, { "_id" : "userprofile.8a864209-1863-4ee6-99cf-673ab2b22d73", "name3" : "Rajesh4", "name" : "Rajesh1", "name1" : "Rajesh2", "name2" : "Rajesh3", "ItemId" : "8a864209-1863-4ee6-99cf-673ab2b22d73" }] }, "Meta" : { "Paging" : { "TotalItems" : 3, "Limit" : 50, "StartIndex" : 0, "PageNumber" : 0, "Previous" : null, "Next" : null }, "Type" : "List<UserProfile>" } }
		
		Map<?,?> m = getTemp().getForObject(url, Map.class);
		m = (Map<?,?>)m.get("Data");
		ArrayList<Map<?,?>> al = ((ArrayList<Map<?,?>>) m.get(name));
		return al;
	}

	@Override
	public Map<?,?> getItem(String name, String id) {
		String url = IntelConstants.host + "/catalog/v1/" + name + "/" + id;
		return getTemp().getForObject(url, Map.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<?,?>> browse(String name, FilterCriteria q) {
		String url = IntelConstants.host + "/catalog/v1/" + name + "?" + q.toString();

		Map<?,?> m = getTemp().getForObject(url, Map.class);
		m = (Map<?,?>)m.get("Data");
		ArrayList<Map<?,?>> al = (ArrayList<Map<?,?>>) m.get(name);
		return al;
	}
}