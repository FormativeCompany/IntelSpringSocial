package org.springframework.social.intel.context.api;

import org.springframework.social.intel.context.api.json.ContextRating;
import org.springframework.social.intel.context.api.json.ContextSearch;
import org.springframework.social.intel.context.api.json.LocationCheckin;
import org.springframework.social.intel.context.api.json.LocationCurrent;
import org.springframework.social.intel.context.api.json.LocationRoute;
import org.springframework.social.intel.context.api.json.PossibleStep;

public enum ContextResources {
//	urn:x-intel:context:model:{model-name}:{model-capability}:v{revision-number}
	/**
	 * Points of interest search history
	 */
	SEARCH("urn:x-intel:context:type:search", ContextSearch.class),
	
	BEHAVOUR("urn:x-intel:context:behaviormodel", PossibleStep.class), //FiXME: PossiblePoi is 
	
	/**
	 * Navigation route history
	 */
	LOCATION_ROUT("urn:x-intel:context:type:location:route", LocationRoute.class),
	
	/**
	 * Point of interest check-ins
	 */
	LOCATION_CHECKIN("urn:x-intel:context:type:location:checkin", LocationCheckin.class),
	
	/**
	 * Current location
	 */
	LOCATION_CURRENT("urn:x-intel:context:type:location:current", LocationCurrent.class),
	
	/**
	 * Likes and Ratings
	 */
	LOCATION_RATING("urn:x-intel:context:type:rating", ContextRating.class);
	
	private String intelUri;
	private Class<?> type;
	
	private ContextResources(String intelUri, Class<?> type) {
		this.intelUri = intelUri;
		this.type = type;
	}
	
	private ContextResources(String model, String modelName, String modelCap,String revision) {
		this.intelUri = "urn:x-intel:context:"+model+":"+modelName+":"+modelCap+":"+revision;
	}
	
	public String getIntelUri() {
		return intelUri;
	}
	
	public static ContextResources parse(String value) {
		if(value == null) {
			throw new UnsupportedOperationException(value);
		}
		
		ContextResources[] cr = ContextResources.values();
		for (ContextResources contextResources : cr) {
			if(contextResources.getIntelUri().equals(value)) {
				return contextResources;
			}
		}
		throw new UnsupportedOperationException(value);
	}

	public Class<?> getType() {
		return type;
	}

	public static ContextResources url(Object value) {
		if(value == null) {
			throw new UnsupportedOperationException();
		}
		
		ContextResources[] cr = ContextResources.values();
		Class<? extends Object> class1 = value.getClass();
		for (ContextResources contextResources : cr) {
			if(contextResources.getType().isAssignableFrom(class1)) {
				return contextResources;
			}
		}
		throw new UnsupportedOperationException(class1.getCanonicalName());
	}
}
