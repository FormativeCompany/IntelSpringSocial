package org.springframework.social.intel.feedback.api;

//ERROR { "Code": "RE50005", "Details": "Invalid limit query." }
//https://api.intel.com/re/v1/RE/
public interface RecommandationOperation {

	/**
	 * Get Anonymous Recommendations Based on a Seed Item. This operation
	 * recommends items to a particular user.
	 * 
	 * Retrieves a list of similar items based on a preprocessed item similarity
	 * algorithm that was run against the application catalog.
	 * 
	 * @param itemId
	 * @param relationShip
	 *            : It can be any of following the types: "similars",
	 *            "cross-sell" or "up-sale".
	 */
	//GET https://api.intel.com/re/v1/recs/item/{item_id}/{RecommendationRelationshipType}
	
	/*
	 * { "Data": {
"Recommendation": [
{
"_id": "BC5216B4-F95B-48CE-BCD3-CD6301DB26D4",
"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
"SourceItemId": "movies.tt0060196",
"TargetItemId": "movies.tt0064116",
"SimilarityFactor": "0.9831361416648776",
"Type": "similars",
"TargetItem":
{
"_id" : "movies.tt0064116",
"ItemId" : "tt0064116",
"Name" : "Once Upon a Time in the West",
"Director" : "Sergio Leone",
"Actors" : [ { "Name" : "Henry Fonda" },
{ "Name" : "Charles Bronson"} ]
}
} ]
}, "Meta": {
"Paging": {
"TotalItems": 1,
"Limit": 50,
"StartIndex": 0,
"PageNumber": 0,
"Previous": null,
"Next": null
}
}, "Type": "List<Recommendation>" }
	 */
	void getAnonymousRecommendations(String itemId, String relationShip);
	
	/**
	 * Get Estimation for Personal Preference
	 * 
	 * Retrieves the associations created for an item. The list of associations
	 * includes the list of URLs for association navigation. Note that the
	 * relation is restricted to a previously created association and previously
	 * created items.
	 * 
	 * @param itemId
	 */
	//GET https://api.intel.com/re/v1/recs/user/{user_id}/preference/ {item_id}
	//=3.9931979184007262
	void getEstimatedUserPreference(String itemId);
	
	/**
	 * Returns a list of recommended items for a particular user.
	 * @param itemId
	 */
	//GET https://api.intel.com/re/v1/recs/user/{user_id}? {$filter}& {$fields}& {$limit}& {$offset}
	
//	$filter
//	URI Path	optional	Filter condition.
//	$fields
//	URI Path	optional	List of entity fields.
//	$limit
//	URI Path	optional	
//	$offset
//	URI Path	optional
	
// RESPONSE
//	{ "Data": { "Recommendation": [
//	{ "_id": "BC5216B4-F95B-48CE-BCD3-CD6301DB26D4",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "232222",
//	"TargetItemId": "movies.tt0086250",
//	"UserEstimatedRate": "0.9931979184007262",
//	"Source": "algorithm",
//	"TargetItem": { "_id" : "movies.tt0086250",
//	"ItemId" : "tt0086250",
//	"Name" : "Scarface",
//	"Director" : "Brian de Palma",
//	"Actors" : [ { "Name" : "Al Pacino" },
//	{ "Name" : "Michelle Pfeiffer"} ] }
//	} ]
//	}, "Meta": { "Paging": {
//	"TotalItems": 1,
//	"Limit": 50,
//	"StartIndex": 0,
//	"PageNumber": 0,
//	"Previous": null,
//	"Next": null }
//	}, "Type": "List<Recommendation>" }
	void getRecommandationItem(String itemId);
	
	/**
	 * This operation recommends items to a particular user.
	 */
	//GET https://api.intel.com/re/v1/recs/user/{user_id}/related/ {item_id}? {$filter}& {$fields}& {$limit}& {$offset}
//	
//RESPONSE	{ "Data": { "Recommendation": [
//	{ "_id": "BC5216B4-F95B-48CE-BCD3-CD6301DB26D4",
//	"X-ApiKey": "55fd7bec-e496-4b2e-bbaa-14674011df41",
//	"UserId": "232222",
//	"TargetItemId": "movies.tt0068767",
//	"UserEstimatedRate": "0.9931979184007262",
//	"Source": "algorithm",
//	"TargetItem": { "_id" : "movies.tt0086250",
//	"ItemId" : "tt0068767",
//	"Name" : "The Chinese Connection",
//	"Director" : "Wei Lo",
//	"Actors" : [ { "Name" : "Bruce Lee" }, { "Name" : "Nora Miao" } ] }
//	}
//	] }, "Meta": {
//	"Paging": {
//	"TotalItems": 1,
//	"Limit": 50,
//	"StartIndex": 0,
//	"PageNumber": 0,
//	"Previous": null,
//	"Next": null
//	}
//	}, "Type": "List<Recommendation>" }
	void getRecommendationForSimilarItems(String itemId);
}
