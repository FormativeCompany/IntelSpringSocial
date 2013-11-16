package org.springframework.social.intel.context.api;

import java.util.Date;
import java.util.List;

import org.springframework.social.intel.context.api.json.AccessResponse;
import org.springframework.social.intel.context.api.json.InstanceInfo;
import org.springframework.social.intel.context.api.json.PossiblePoi;
import org.springframework.social.intel.context.api.json.PossibleStep;
import org.springframework.social.intel.context.api.json.UserInstanceInfo;
import org.springframework.social.intel.context.api.json.ValueHolder;
import org.springframework.social.intel.context.api.json.Watch;

public interface ContextOperations {
	//POST https://api.intel.com/context/v1/accessrequests
	//REQUEST {
//    "data":
//    {
//        "reason": { "en": "These permissions are needed to enable access policy management"},
//        "permissions": [
//            {
//                "access": [
//                    "create",
//                    "read"
//                 ],
//                 "resourceUri": "urn:x-intel:context:type:location:route"
//               }
//            ] 
//     }
//}
	
	//response->data->AccessResponse
	
//RESPONSE {
//    "data": {
//    "lang": "en",
//    "privacyPolicyUrl": null,
//    "requesterInstanceId": "test instance",
//    "resourceOwnerId": "test-owner",
//    "requesterUserId": "test-user",
//    "state": "approved",
//    "optionLabel": null,
//    "reason": "These permissions are needed to enable access policy management",
//    "modifiedDate": "2012-09-19T22:49:15Z",
//    "requesterClientId": "test-client",
//    "createdDate": "2012-09-19T22:49:15Z",
//    "id": "7ce9e320075011e292840a9b74ce8e6a",
//    "permissions": [
//        {
//            "access": [
//                "create",
//                "read",
//            ],
//            "resourceUri": "urn:x-intel:context:type:location:route"
//        }
//    ]
//}
//}}
	
	AccessResponse createAccessRequest(ContextResources resources, String reason, Access... access);
	
	AccessResponse createAccessRequest(ContextResources[] resources, String reason, Access... access);
	
	//GET https://api.intel.com/context/v1/accessrequests/{id}
	//RESPONSE {
//    "data": {
//        "lang": "en",
//        "privacyPolicyUrl": null,
//        "requesterInstanceId": "test-instance",
//        "resourceOwnerId": "test-owner",
//        "requesterUserId": "test-user",
//        "state": "approved",
//        "optionLabel": null,
//        "reason": "These permissions are needed to enable access policy management",
//        "modifiedDate": "2012-09-19T22:49:15Z",
//        "requesterClientId": "test-client",
//        "createdDate": "2012-09-19T22:49:15Z",
//        "id": "7ce9e320075011e292840a9b74ce8e6a",
//        "permissions": [
//            {
//                "access": [
//                    "create",
//                    "read",
//                    "update",
//                    "delete"
//                ],
//                "resourceUri": "urn:x-intel:context:type:location:route"
//            }
//        ]
//    }
//}}
	
	AccessResponse getAccessRequest(String id);
	
	//DELET https://api.intel.com/context/v1/accessrequests/{id}
	void deleteAccessRequest(String id);
	
	//////////////////USER API///////////////
//	GET https://api.intel.com/context/v1/users/{id}
	/*RESPONSE {
    "data": {
        "id": "c",
        "instanceIds": [
            "b",
            "e",
            "d"
        ]
    }
}
	 * 
	 */
	/**
	 * Returns a list of application instance IDs associated with a specific user
	 * @param id
	 */
	UserInstanceInfo getUserInfo(String id);
	
	/**
	 * Returns information about a particular installation of an application running on behalf of a logged-in user. This call provides the user and the client, and enables you to differentiate the same client and user on different systems.
	 * @param instanceId
	 */
	//GET https://api.intel.com/context/v1/instances/{id}
	/*
	 * RESPONSE {
    "data": {
        "id": "test-instance",
        "owner": "test-owner",
        "clientId": "test-client"
    }
}
	 */
	InstanceInfo getInstanceInfo(String instanceId);
	
	/**
	 * Obtains the list of context resource types supported by Context Services,
	 * as well as schemas, descriptions, and documentation. The list is
	 * read-only to all clients.
	 */
	//GET https://api.intel.com/context/v1/typescatalog
	String getTypeCatalog();
	
	//GET https://api.intel.com/context/v1/typescatalog?identifierBeginsWith={string}
//	None	no	 	No query string causes the services to return all of the type values present in the service.
//			ids	no	string	One or more specific identifiers that, when included, return associated resource types.
//			identifierBeginsWith	no	string	Beginning of identifier, which, when included, gets the types from the services with an identifier starting with the specified string.
//	GET https://api.intel.com/context/v1/typescatalog?ids=urn:x-intel:context:location:current&ids=urn:x-intel:context:type:location:route
//	GET https://api.intel.com/context/v1/typescatalog?identifierBeginsWith=urn:x-intel:context:location:
	String getTypeCatalog(String name);
	
	/**
	 * Querying /items returns results filtered by the caller's access
	 * permission and then by specified filter parameters (see table below).
	 * 
	 * Query responses have the same representation as responses from POST
	 * operations.
	 */
	//GET https://api.intel.com/context/v1/items[?{filter_params}]
	//GET https://api.intel.com/context/v1/items?clientCreatedTimeAfter=2012-01-19T17:20:00Z&clientCreatedTimeBefore=2012-01-19T17:30:00Z
	ValueHolder<?>[] getItems(ContextParam param);
	
	/**
	 * Uploads data to Context Services by posting a set of context item
	 * structures to the /items collection.
	 * 
	 * Items with multiple data types can be included in the same POST.
	 * 
	 * ContextType, clientCreatedTime, and value are provided by the client;
	 * serverCreatedTime and serverModifiedTime are set by the service. Owner
	 * and provider need not be passed because they are taken from the request
	 * header.
	 * 
	 * All items in the post will be uploaded successfully, or none at all. On
	 * successful upload, the response is the completely specified object for
	 * each of the newly stored items, including a unique ID, server time
	 * stamps, and more. The order of the results matches the order of the
	 * uploaded values.
	 */
	//POST https://api.intel.com/context/v1/items
	<T> ValueHolder<T> addItems(T value);
	
	//DELETE https://api.intel.com/context/v1/items[?{filter_params}]
	//DELETE DELETE https://api.intel.com/context/v1/items?clientCreatedTimeAfter=2012-01-19T17:20:00Z&clientCreatedTimeBefore=2012-01-19T17:30:00Z
	void deleteItems(ContextParam param);
	
	//GET https://api.intel.com/context/v1/items/{item_id}
	ValueHolder<?> getItem(String itemId);
	
	//DELETE https://api.intel.com/context/v1/items/{item_id}
	void deleteItem(String itemId);
	
	/**
	 * Returns the set of watch objects associated with the calling application.
	 * Each object represents a filter that will generate a notification to the
	 * caller.
	 * 
	 * @param itemId
	 */
//
//Input	Required	Input Type	Description
//id	no	string	The ID or list of IDs of the watch object.
	//GET https://api.intel.com/context/v1/watches[?{query_params}]
	//GET https://api.intel.com/context/v1/watches?id=4406e0ac08f511e2bea00a9b74ce8e68
	
	/* RESPONSE
	 * {
    "data": {
        "items": [{
            "id": "4406e0ac08f511e2bea00a9b74ce8e68"
            "contextType": "urn:x-intel:context:location:current",
            "owner": "6d617474000000000000000000000001",
            "provider: "6d617474000000000000000000000000",
            "callbackUrl": "http://api.intel.com/context/v1/testcallback/1000"
       ]
    }
}

	 */
//	GET https://api.intel.com/context/v1/watches/{watch_id}
	List<Watch> getWatches(String itemId);
	
	/**
	 * Creates a watch associated with the calling application. You can only
	 * create one watch by calling this URL.
	 * 
	 * You only need to provide contextType, owner, and provider. The
	 * callbackURL field is only needed if you want to use the asynchronous HTTP
	 * notification feature. The callbackUrl has to be in a valid URI format
	 * callable from the Internet.
	 * 
	 * Any one of the fields, contextType, owner, or provider, can be missing.
	 * Missing fields mean "don't care" or wildcard.
	 */
	//POST https://api.intel.com/context/v1/watches
	/* RESPONSE 
	 * 
	 * {
    "data": {
        "contextType": "urn:x-intel:context:type:search",         
        "owner": "6d617474000000000000000000000001",
        "provider: "6d617474000000000000000000000000",
        "callbackUrl": "http://api.intel.com/context/v1/testcallback/1000"      
    }
}
	 */
	Watch createWatche(Watch w);
	
	/**
	 * Deletes a specific watch.
	 * @param id
	 */
	//DELETE https://api.intel.com/context/v1/watches/{watch_id}
	void deleteWatch(String id);
	
	/**
	 * Returns new items for watches that have been set. Gets the new context
	 * items created since the last time a GET of /watches/newitems was
	 * performed. The /watches/newitems resource is a dynamic view on the /items
	 * resource. It contains context items contained in /items where all of the
	 * following are true:
	 * 
	 * <li>The context item properties match one of the watch patterns in the
	 * /watches resource. </li> 
	 * 
	 * <li>The user who created the watch has access to the new
	 * context items.</li> 
	 * 
	 * Context items are returned to the caller in the order in
	 * which they are posted to Context Services. When a context item is
	 * returned to the caller, it is removed from the /watches/newitems
	 * resource. The caller will never receive the same context item twice from
	 * subsequent GETs. The data returned to the caller has the same format as
	 * data returned from a GET of /items.
	 */
	//GET https://api.intel.com/context/v1/watches/newitems
	ValueHolder<?>[] getNewItems();
	
	//GET https://api.intel.com/context/v1/users/{userid}/models/behavior/places?lat={x}&lon={y}&timestamp={ts}
	/*
	 * RESPONSE 
	 * {
    "distance-factor": 0.232717611823722,
    "centroid": {
        "lon": -122.973328533333,
        "lat": 45.5199697333333
    },
    "weight": 7.56332238427096,
    "place-id": "abc10613-df3f-450c-b32a-f9168e14f291",
    "poi-type": "restaurant"
	},

ERROR

200	OK	The request was successful.
401	Access denied	The caller does not have access to the application instance information.
404	Not found	The user was not found.
500	Internal error	Servers are not working as expected. The request might be valid but needs to be retried later.
	 */
	/**
	 * Context Services can create a model that describes user habits based on
	 * information reported to the service. The models are based on sequences of
	 * events, each of which describes the transition from one context instance
	 * to the next. The models/behavior API can currently predict restaurant
	 * visits based on past user restaurant visits and time of visit
	 * information.
	 * 
	 * Lists the next possible location that the user might go by analyzing
	 * context that was gathered for that user.
	 * 
	 * @param userid
	 *            The ID of the user in the authentication token. You can also
	 *            substitute "me ." (mandatory)
	 * @param latitude
	 *            Longitude in degrees for the starting location. (Optional)
	 * @param longitude
	 *            Latitude in degrees for the starting location. (Optional)
	 * @param time
	 *            Time when the event begins. (Optional)
	 */
	PossibleStep getPossibleNextStep(String userid, double latitude, double longitude, Date time);
	
	/**
	 * Context Services can create a model that describes user habits based on
	 * information reported to the service. The models are based on sequences of
	 * events, each of which describes the transition from one context instance
	 * to the next. The models/behavior API can currently predict restaurant
	 * visits based on past user restaurant visits and time of visit
	 * information.
	 * 
	 * 
	 * Queries the model for point of interest attributes, particularly related
	 * to cuisine. Time and location parameters are optional, but including them
	 * improves the quality of the prediction.
	 * 
	 * @param userid
	 *            The ID of the user in the authentication token. You can also
	 *            substitute "me ."(Mandatory)
	 * @param lat
	 *            Latitude in degrees forthe location for which you want
	 *            POI.(Optional)
	 * @param lon
	 *            Longitude in degrees for the location for which you want the
	 *            POI.(Optional)
	 * @param time
	 *            Time of day when you want the suggestion for weighted POI.
	 *            (Optional)
	 */
	//GET https://api.intel.com/context/v1/users/{userid}/models/behavior/poi-weights?lat={x}&lon={y}&timestamp={ts}
	
	/* RESPONSE
	 * [[
    {
        "weight" : 67.91666666666667,
        "attributes" : {
            "price" : 1,
            "cuisine" : "Japanese"
        }
    },
    {
         "weight" : 54.375,
         "attributes" : {
             "price" : 1,
             "cuisine" : "Cafe"
        }
    },
    {
         "weight" : 22.29166666666667,
         "attributes" : {
             "price" : 2,
             "cuisine" : "French"
        }
    }
]
	 */
	
	/* ERROR
	 {
		code : XXX-nnnnn,
		transactionid: c962fe29-d4e8-11e1-bd04-0050568c00f9,
		message : Description of error message
	 }
	 */
	

//HTTP Code	Message	Description
//200	OK	The request was a success.
//201	Created	The newly created policy.
//204	No Content	Empty body.
//400	Bad request	Any case where a parameter is invalid or a required parameter is missing. This includes the case where no OAuth token is provided.
//401	Access Denied	The OAuth token was provided but was invalid.
//403	Forbidden	The caller does not have access to the application instance information because the caller does not share the same owner.
//404	Not found	Resource does not exist.
//405	Method not allowed	Attempting to use any other method different than GET.
//409	Conflict	Response when you attempt to create more than four watches per instance.
//410	Gone	Non-existent item or watch deleted.
//500	Internal server error	Servers are not working as expected. The request might be valid but needs to be retried later.
	PossiblePoi[] getPointOfInterest(String userid, double lat, double lon, Date time);
}
