package org.springframework.social.intel.curation.api;

import java.util.Map;

import org.json.simple.JSONArray;

public interface CurationOperation {
	/**
	 * Create curated list. This list is user-centric, and actions can range
	 * from adding items to selecting, organizing, and preserving groupings of
	 * objects from a Catalog.
	 */
//	POST https://api.intel.com/curation/v1/me/lists
//REQUEST	{
//		"Name": "Favorite Games" ,
//		"Fields":{},
//		"Public": "true"
//	}
//RESPONSE 
//	{
//		"ListId": "03265126-89c7-413a-b912-54e38f7907cf",
//		"UserId": "a9c87585-df66-413c-aab1-b9a9f0ee6deb",
//		"Name": "Favorite Games",
//		"Fields": {},
//		"Public": true,
//		"Items": [],
//		"Version": 1,
//		"_id": "03265126-89c7-413a-b912-54e38f7907cf.1"
//		}
//	
// ERROR
//	{ "Code": "CU60009",
//		"Details": "ApiKey is required."}
	void createList(String name, boolean isPublic);
	
	/**
	 * Add custom fields to a curated list, additional metadata be added.
	 * @param name
	 * @param listId
	 */
	//PUT https://api.intel.com/curation/v1/me/lists/{listId}
	
	//REQUEST {
//	"name":
//	{
//	"Type" : type
//	}
//	}
	void addFieldToList(String name, String type, String listId);
	
	/**
	 * Add items to a specific list.
	 * {CatalogUri} attribute in body help Curation services to integrate with Catalog items. This attribute is optional.
	 * @param itemId
	 */
	//POST https://api.intel.com/curation/v1/me/lists/{listId}/items
	//REQUEST {
//	"Name": "Angry Birds"
//	}
	//RESPONSE {
//	"Name": "Angry Birds",
//	"Id": "04c1e0f6-b383-41c3-b3a8-574d2b04818c"
//	}
	

	void addItem(String listId, Map<String, Object> object);

	//POST https://api.intel.com/curation/v1/me/lists/{listId}/items
	//REQUEST CatateUrl
//	{
//		{
//		"CatalogUri": "https://api.intel.com/catalog/
//		v1/books/db0a4b74-57e8-465c-94a2-597d2c4198f6"
//		}
//		}
//	RESPONSE : {
//	"CatalogUri": "https://api.intel.com/catalog/v1/
//		books/db0a4b74-57e8-465c-94a2-597d2c4198f6",
//		"Id": "adb50837-4714-4951-8f66-c974a650c1f9"
//		}
	void addItem(String listId, String catalogItemId);
	
	/**
	 * Get items of a particular List.
	 * @param listId
	 */
	JSONArray getItem(String listId);
	
	/**
	 * Remove it from her "Favorite Movies" list.
	 * @param listId
	 * @param itemId
	 */
	//DELETE https://api.intel.com/curation/v1/me/lists/{listId}/items/{itemId}
	void deleteItem(String listId,String itemId);
	
	/**
	 * Rename a list just in case it needs a new name.
	 * @param listId
	 * @param newName
	 */
	//PATCH https://api.intel.com/curation/v1/me/lists/{listId}
//	REQUEST {
//		"Name": "Applications"
//	}
	void renameList(String listId, String newName);
	
	/**
	 * Remove all versions of a list.
	 * @param listId
	 */
//	DELETE https://api.intel.com/curation/v1/me/lists/{listId}
	void deleteList(String listId);
	
	/**
	 * Show available versions of a list, including metadata like update date/time and count of items.
	 * @param listId
	 */
	//GET https://api.intel.com/curation/v1/me/lists/{listId}/versions
	//RESPONSE { "Versions" : [1, 2] }
	void getListVersions(String listId);
	
	/**
	 * Show available versions of a list, including metadata information like list name and fields.
	 * @param listId
	 */
	//GET https://api.intel.com/curation/v1/me/lists/{ListId}
	/*
	 {
"UserId": "0b34d411-d416-448d-aa21-06344fcb6091",
"Name": "Favorite Games",
"Fields": {},
"Public": true,
"Version": 2
"PublicItemsUrl": "https://api.intel.com/curation/v1/public/3FDA015C05FBBBB26743B06B1BCDA2A5EF17BF53A05DF54614CEBA147D2FD4D0/lists/03265126-89c7-413a-b912-54e38f7907cf/items/"
}
	 */
	void getListMetaData(String listId);
	
	/**
	 * Get the contents of a list for a particular version.
	 * @param listId
	 * @param versionId
	 */
	//GET https://api.intel.com/curation/v1/me/lists/{listId}/items/{versionId}
	
	/*
	 {
[
{
"Name": "Angry Birds",
"Id": "04c1e0f6-b383-41c3-
b3a8-574d2b04818c"
}
]
}
	 */
	Map<String,Object>[] getListItems(String listId, String versionId);
}
