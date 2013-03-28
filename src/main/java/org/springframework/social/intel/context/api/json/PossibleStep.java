package org.springframework.social.intel.context.api.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class PossibleStep {
	double distanceFactor;// distance-factor;
	double weight;
	
	String placeId;// place-id
	
	String poiType;// poi-type
	
	Location centroid;

	@JsonProperty(value="distance-factor")
	public double getDistanceFactor() {
		return distanceFactor;
	}

	public void setDistanceFactor(double distanceFactor) {
		this.distanceFactor = distanceFactor;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@JsonProperty(value="place-id")
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@JsonProperty(value="poi-type")
	public String getPoiType() {
		return poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

	public Location getCentroid() {
		return centroid;
	}

	public void setCentroid(Location centroid) {
		this.centroid = centroid;
	}

}
