package org.springframework.social.intel.feedback.api;

//https://api.intel.com/re/v1/feedback/
public interface FeedbackOperation {
	/**
	 * Registers custom Action Types.
	 * 
	 * @param name
	 */
	//PUT https://api.intel.com/re/v1/feedback/action/{actionType}
	//
	void createAction(String name);
	
	/**
	 * This operation registers the acceptance of a proposed relation between
	 * two items. A Score is required to be sent for a recommendation’s
	 * relations (cross-sell, up-sell) to be used as recommendation’s feedback
	 * for the Engine.
	 * 
	 * @param item1
	 * @param item2
	 */
	//PUT https://api.intel.com/re/v1/feedback/accept-relation/{itemId}/cross-sell/{itemId}
	void acceptCrossSell(String item1, String item2);
	void acceptUpSell(String item1, String item2);
	
	/**
	 * Registers the rejection of a proposed relation between two items. A Score
	 * is required to be sent for recommendation’s relations (cross-sell,
	 * up-sell) to be used as recommendation’s feedback for the Engine.
	 * 
	 * @param itemId
	 * @param itemId2
	 */
	//PUT https://api.intel.com/re/v1/feedback/accept-relation/{itemId}/cross-sell/{itemId}
	//{ "Date": "2012-06-13T12:44:45Z", "UserId": 232223, "Score": "3.0", "Context": { "Mood": "A Mood Description", "Current Location": "A Location information", "Current Activity": "An activity description" } }
	void rejectCrossSell(String itemId, String itemId2);
	
	void rejectUpSell(String itemId, String itemId2);
	
	/**
	 * Registers a user’s event specific of the Api key. There may be other
	 * events that can be collected in order to feed the recommendation engine.
	 * This Api validates that the action type has been created before
	 * registering a generic action. Please, notice that the Api depends on the
	 * fact that each Item Id is unique among the Api key
	 * 
	 * Registers an user's event specific of the tenant. There may be other
	 * events that can be collected in order to feed to recommendation engine.
	 * This API validates the action type has been created before registering a
	 * generic action. Please notice that the API depends on the fact that each
	 * Item id is unique among the tenant.
	 * 
	 * @param action
	 * @param item2
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/{actionName}/{itemId}
	/*{ 
	 * "ActionPayload": { 
	 * 		"AtributeA" : 
	 * 		"aValue" 
	 * }, 
	 * "Context": { 
	 * 		"Mood": "A Mood Description", 
	 * 		"Current Location": "A Location information", 
	 * 		"Current Activity": "An activity description" 
	 * } 
	 *}*/
	void feedback(String action, String item2) ;
	
	/**
	 * Registers an item so that the item is not considered as part of the
	 * things the user likes. This would for instance come from an item the user
	 * has bought but it was a gift.
	 * 
	 * @param itemId
	 */
	//
	//PUT https://api.intel.com/re/v1/feedback/{userId}/ignore-item/{itemId}
	//{ "ActionPayload": { "Date" : "2012-06-13T12:44:45Z" }
	void ignore(String itemId);
	
	/**
	 * Rejection of a recommendation by the user. Please, refer to the
	 * considerations for Accept Recommendation.
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/reject-rec/{itemId}
	//{ "ActionPayload": { "Date" : "2012-06-13T12:44:45Z" }
	void reject(String itemId);
	
	/**
	 * Registers an explicit preference of a User Id. Remember that the User Id
	 * is universal (Api key independent) so you can have the same user in
	 * different Api keys or recommendation environments.
	 * 
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/rate/{itemId}
	//{ "ActionPayload": { "Rate" : "4", "Date" : "2012-06-13T12:44:45Z" },
//	"Context": { "Mood": "A Mood Description", "Current Location":
//		"A Location information", "Current Activity": "An activity
//		description" }, "Details": "The \"{0}\" element type is invalid." }
	void rate(String itemId);
	
	/**
	 * Registers an explicit purchase or subscription of a user to a specific item.
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/purchase/{itemId}
	//PUT https://api.intel.com/re/v1/feedback/{userId}/purchase/{item_type_name}.{item_id}

//{ "ActionPayload": { "Date" : "2012-06-13T12:44:45Z" }, "Context": {
//"Mood": "A Mood Description", "Current Location": "A Location
//information", "Current Activity": "An activity description" } , "Details": "Invalid elements in \"ActionPayload\"." }
	void purchage(String itemId);
	
	/**
	 * Registers a visit to a particular item, or an implicit preference for the item from a particular user.
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/visit/{itemId}
//	{ "ActionPayload": { "Rate" : "4" }, "Context": { "Mood": "A Mood
//		Description", "Current Location": "A Location information",
//		"Current Activity": "An activity description" }, "Details": "The \"{0}\" element type is invalid." }
	void visit(String itemId);
	
	/**
	 * Registers a user’s review on an item.
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/review/{itemId}
/*
 {

   "ActionPayload": {
      "Rate": "4",
      "Review": "This an Excellent Book!!"

   },

   "Context": {
      "Mood": "A Mood Description",
      "Current Location": "A Location information",
      "Current Activity": "An activity description"
   }
}
*/
	void review(String itemId);
	
	/**
	 * Registers the acceptance of a recommendation by the user. Please, notice
	 * the service always recommends items of a given item_type, since the ids
	 * (externals) are unique by Api key so the type can be ignored.
	 * 
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/{userId}/accept-rec/{itemId}
	//{ "Code": "RE40002", "ActionPayload": { "Date" : "2012-06-13T12:44:45Z"} }
	void acceptRecommandation(String itemId);
	
	/**
	 * Retrieves the list of rates the user has given as feedback.
	 */
	//GET https://api.intel.com/re/v1/feedback/{userId}/rate
//	
//	RESPONSE { "Data": { "rate": [ { "_id": "1df244e7fc1a4290b72b110051ebe091",
//		"X-ApiKey": "F0399437-FD0C-4A48-B101-F0314A6172E4", "UserId":
//		"7ACCDA7E-A82C-4C95-B41E-1140C63726C7", "ActionType": "rate",
//		"ItemId": "books.3454444", “Rate”: “3”,
//		"Context": { "_id": "ef67da9a40a646e1abba4a5312f2bdd6",
//		“Mood”: “A Mood Description”,
//		“Current Location”: “A Location
//		information”, "Current Activiy": "default", "X-ApiKey":
//		"F0399437-FD0C-4A48-B101-F0314A6172E4", "UserId":
//		"7ACCDA7E-A82C-4C95-B41E-1140C63726C7" }, "ActionPayload": { }
//		}, ] } , "Meta": { "Paging": { "TotalItems": 1, "Limit": 50,
//		"StartIndex": 0, "PageNumber": 0, "Previous": null, "Next": null
//		}, "Type": "List<rate>" }
	void getRate();
	
	/**
	 * This operation retrieves the list of items the user has purchased.
	 */
	//GET https://api.intel.com/re/v1/feedback/{userId}/purchase
	
//	RESPONSE { "Data": { "purchase":[
//	{ "_id": "1df244e7fc1a4290b72b110051ebe091",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "7ACCDA7E-A82C-4C95-B41E-1140C63726C7",
//	"ActionType": "purchase",
//	"ItemId": "books.3454444",
//	"Context": { "_id": "ef67da9a40a646e1abba4a5312f2bdd6",
//	"Mood": "A Mood Description",
//	"Current Location": "A Location information",
//	"Current Activiy": "default",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "7ACCDA7E-A82C-4C95-B41E-1140C63726C7" },
//	"ActionPayload": { } },
//	{ "_id": "4355b97a633a4ed1a48d676193d7307e",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "7ACCDA7E-A82C-4C95-B41E-1140C63726C7",
//	"ActionType": "purchase",
//	"ItemId": "books.3454455",
//	"Context": { "_id": "ef67da9a40a646e1abba4a5312f2bdd6",
//	"Mood": "A Mood Description",
//	"Current Location": "A Location information",
//	"Current Activiy": "default",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "7ACCDA7E-A82C-4C95-B41E-1140C63726C7" },
//	"ActionPayload": { } }
//	] }, "Meta": { "Paging": {
//	"TotalItems": 2,
//	"Limit": 50,
//	"StartIndex": 0,
//	"PageNumber": 0,
//	"Previous": null,
//	"Next": null },
//	"Type" : "List&lt;purchase&gt;" }
	void getPurchage();
	
	/**
	 * Retrieves the average rate of an Item. The average is calculated based on
	 * the feedback received by all users for that specific item.
	 * 
	 * @param itemId
	 */
	//GET https://api.intel.com/re/v1/feedback/action/rate/{ItemId}
	//RESPONSE { "ItemId": "movies.390002323", "AverageRate": 5, "TotalRates": 1 }
	void getAverageRate(String itemId);
	
	/**
	 * Retrieves the list of reviews the users have given as feedback.
	 * 
	 * @param itemId
	 */
	//GET https://api.intel.com/re/v1/feedback/action/review/{ItemId}
	/*
	//RESPONSE 
	 * { 
	 * 	"Data": { 
	 * 		"review": [
//				{ 
 * 					"_id": "E62FD583-59BB-42C5-AA2B-0F6C3A176B61",
//					"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//					"UserId": "3423334",
//					"ActionType": "review",
//					"ItemId": "movies.390002323",
//					"Context": { 
 * 							"AttirbuteA": "Some Data"
 * 
 * 					},
//					"ActionPayload": { 
 * 							"Date": "2012-07-30T18:15:31.667Z",
//							"Review": "Good Movie!!"
 * 					} 
 * 				}
//			] 
 * 		}, 
 * 		"Meta": { 
 * 			"Paging":
//				{ 
 * 					"TotalItems": 1,
//					"Limit": 50,
//					"StartIndex": 0,
//					"PageNumber": 0,
//					"Previous": null,
//					"Next": null 
 * 				},
//			"Type": "List<review>"
//		} 
 * 	}
	*/
	void getReviews(String itemId);
	
	/**
	 * Adds an item to the blacklist so it will not be recommended.
	 * 
	 * The blacklisted item does not impact on recommendations to related items.
	 * For instance, if we blacklist an actor, the actor itself will not be
	 * recommended but her movies would be still recommended. For Blacklist, the
	 * Item Id must be the Api key Unique Id.
	 * 
	 * @param itemId
	 */
	//PUT https://api.intel.com/re/v1/feedback/blacklist/{ItemId}
	void blackList(String itemId);
	
	/**
	 * Removes an item from the blacklist so it will be recommended again.
	 * 
	 * For Un-Blacklist an Item, the Item Id must be the Api key Unique Id.
	 * Note: The URI for applying this action is the same as the blacklist
	 * action, but with a different http verb.
	 * 
	 * @param itemId
	 */
	//DELETE https://api.intel.com/re/v1/feedback/blacklist/{ItemId}
	void blackListRemove(String itemId);
	
	//PUT PUT https://api.intel.com/re/v1/feedback/me/status
//	{
//		"Context": {
//			"Mood": "Happy",
//			"Current Location": "US",
//			"Current Activity": "Breakfast"
//		}
//	}
	void status();
}
