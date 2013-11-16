package org.springframework.social.intel.catalog.api;

import java.util.List;
import java.util.Map;


public interface CatalogOperation {
	public void testRefresh();
	
//	POST https://api.intel.com/catalog/v1/{item_types plural name}
//	POST https://api.intel.com/catalog/v1/books	
	public void createItemType(String name);
	
//	GET https://api.intel.com/catalog/v1
//	{
//		"Data": {
//		"ItemType": [
//		{
//		"_id": "50f4c7d02f8c46edaf3e6f302428ef9b",
//		"Name": "movies",
//		"ApiKey": "55fd7bec-e496-4b2ebbaa-14674011df41"
//		},
//		{
//		"_id": "ef399f5d9a414d8aa4128dc73027c232",
//		"Name": "games",
//		"ApiKey": "55fd7bec-e496-4b2ebbaa-
//		14674011df41"
//		},
//		{
//		"_id": "f1c607f67c4842369dbad2c1546f0441",
//		"Name": "photos",
//		"ApiKey": "55fd7bec-e496-4b2ebbaa-
//		14674011df41"
//		},
//		{
//		"_id": "bc16c2661a0c4ae9a11ffa458957cb1b",
//		"Name": "books",
//		"ApiKey": "55fd7bec-e496-4b2ebbaa-
//		14674011df41"
//		}
//		]
//		},
//		"Meta": {
//		"Paging": {
//		"TotalItems": 5,
//		"Limit": 100,
//		"StartIndex": 0,
//		"PageNumber": 0,
//		"Previous": null,
//		"Next": null },
//		"Type": "list<itemType>"
//		}
//		}
	
	
	//https://api.intel.com/catalog/v1/{item_types plural name}
	public List<Map<?,?>> listItemType();
	
	
//	{
//
//		   "Data": {
//
//		      "books": [
//
//		         {
//
//		            "_id": "books.db4f3f7db9d7-480d-9eaf-721d3baaae95",
//
//		            "ItemId": "db4f3f7db9d7-480d-9eaf-721d3baaae95",
//
//		            "Name": "The Godfather",
//
//		            "Year": "1972",
//
//		            "Description": "An excellent adaptation of the original classic."
//
//		         },
//
//		         {
//
//		            "_id": "books.db0a4b74-57e8-465c-94a2-597d2c4198f6",
//
//		            "ItemId": "db0a4b74-57e8-465c-94a2-597d2c4198f6",
//
//		            "Name": "The lord of the rings: The fellowship of the ring",
//
//		            "Year": "1954",
//
//		            "Description": "The first chapter in the book begins in a light vein, following the tone of The Hobbit."
//
//		         }
//
//		      ]
//
//		   },
//
//		   "Meta": {
//
//		      "Paging": {
//
//		         "TotalItems": 2,
//
//		         "Limit": 50,
//
//		         "StartIndex": 0,
//
//		         "PageNumber": 0,
//
//		         "Previous": null,
//
//		         "Next": null
//
//		      },
//
//		      "Type": "List<books>"
//
//		   }
//
//		}
	//GET https://api.intel.com/catalog/v1/{item_types plural name}
	public List<Map<?,?>> getItem(String name);
	
//	POST https://api.intel.com/catalog/v1/{item_types plural name}
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
	 * Create or Update a list of items in the catalog of a particular Item
	 * Type. This operation is based on simple convention that works under two
	 * modes (for managing Items Id):
	 * 
	 *  All Items on the list include the Item Id sending the "ItemId"
	 * attribute: This is when the Item Id is identified to be universal, and it
	 * won't be used again for another item.
	 * 
	 *  None of the Items in the list include the "ItemId" field: This is the
	 * case the Application want the Catalog Services to create and register an
	 * Item with a generated universall Id for it.
	 * 
	 * 
	 * Important Note: If the Item Type does not exist, this action creates it
	 * as well. In the case of an existing Item Type, this action appends the
	 * new set of Items to the current Item Type collection.
	 * 
	 * @param name
	 * @return
	 */
	public String createItems(String name, Map<String, Object> object);
	
	//PUT https://api.intel.com/catalog/v1/{item_types plural name}
	/*
	 {
		"ItemId": "db4f3f7d-b9d7-480d-9eaf-721d3baaae95",
		"Name": "The Godfather",
		"Year": "1972",
		"Description": "An excellent adaptation of the
		original classic.",
		"In stock":"Yes"
	}
	*/
	public void updateItem(String name, Map<?,?> item) throws IllegalArgumentException;
	
	//Retrieves a specific item. In this case, the ItemId must be the item to search for.
	//https://api.intel.com/catalog/v1/{item_type}/{ItemId}
	
	/*{
		"_id":
		"books.db0a4b74-57e8-465c-94a2-597d2c4198f6",
		"ItemId": "db0a4b74-57e8-465c-94a2-597d2c4198f6",
		"Name": "The lord of the rings: The fellowship of the ring",
		"Year": "1954",
		"Description": "The first chapter in the book begins in a light vein, following the tone of The Hobbit."
		}*/
	public Map<?,?> getItem(String name, String id);
	
	//https://api.intel.com/catalog/v1/books?$filter=Year gt '1970'
	//GET https://api.intel.com/catalog/v1/books?$fields=Name,Description
	//https://api.intel.com/catalog/v1/books?$limit=100&$offset=101
	/*{
	"Data": {
	"books": [
	{
	"_id":
	"books.db0a4b74-57e8-465c-94a2-597d2c4198f6",
	"ItemId":
	"db0a4b74-57e8-465c-94a2-597d2c4198f6",
	"Name": "The lord of the rings: The fellowship
	of the ring",
	"Year": "1954",
	"Description": "The first chapter in the book
	begins in a light vein, following the tone of The Hobbit."
	},
	{
	"_id": "books.db4f3f7db9d7-
	480d-9eaf-721d3baaae95",
	"ItemId": "db4f3f7db9d7-
	480d-9eaf-721d3baaae95",
	"Name": "The Godfather",
	"Year": "1972",
	"Description": "An excellent adaptation of the
	original classic."
	}
	]
	},
	| Intel Cloud Services Platform beta Catalog Services REST Developer Guide | 12
	"Meta": {
	"Paging": {
	"TotalItems": 2,
	"Limit": 50,
	"StartIndex": 0,
	"PageNumber": 0,
	"Previous": null,
	"Next": null
	},
	"Type": "List<books>"
	}
	}*/
	//
	public List<Map<?,?>> browse(String name, FilterCriteria q);
	
}
