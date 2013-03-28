package org.springframework.social.intel.context.api.json;


/**
 * identifier: "urn:x-intel:context:type:location:route" 
 * shortName:Navigation
 * routing history category: "context-type",
 * 
 * Context that is added whenever users make a Get Route request in the
 * Location-based Services. This type can also be used to report a route that
 * was traveled by an end user.
 */
public class LocationRoute {
	Point origin; //mandatory
	
	Point destination;
	//Type of algorithm to use for calculating route, e.g., FASTEST (driving) (default),SHORTEST (driving), PEDESTRIAN. These are case-sensitive parameters and must be entered exactly as shown.
	String routeAlgorithm;//String
	
	//A list of road types to avoid, e.g., FERRY, HIGHWAY, TOLLWAY, UNPAVED. These are case-sensitive parameters and must be entered exactly as shown.
	String[] avoid; //items{[strings]}
	
	//The service uses this value to set the polyline simplification to reduce the number of points returned
	double zoom;
	
	//A list of preferred road types, e.g., FERRY, HIGHWAY, TOLLWAY, UNPAVED. These are case-sensitive parameters and must be entered exactly as shown.
	String[] preferred; //items{[strings]}
	
	//Imperial or metric, e.g., METRIC (default), IMPERIAL, IMPERIAL_FEET. These are case-sensitive parameters and must be entered exactly as shown.
	String measurement;
	
	//Prefered response type json or xml
	String alt;
	
	//Boolean to request that route information include traffic conditions.
	boolean trafficAware;
	
	//Ensure route includes indicated coordinate points
	Location viaPoints; //{items{[location]}}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	public String getRouteAlgorithm() {
		return routeAlgorithm;
	}

	public void setRouteAlgorithm(String routeAlgorithm) {
		this.routeAlgorithm = routeAlgorithm;
	}

	public String[] getAvoid() {
		return avoid;
	}

	public void setAvoid(String[] avoid) {
		this.avoid = avoid;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public String[] getPreferred() {
		return preferred;
	}

	public void setPreferred(String[] preferred) {
		this.preferred = preferred;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public boolean isTrafficAware() {
		return trafficAware;
	}

	public void setTrafficAware(boolean trafficAware) {
		this.trafficAware = trafficAware;
	}

	public Location getViaPoints() {
		return viaPoints;
	}

	public void setViaPoints(Location viaPoints) {
		this.viaPoints = viaPoints;
	}
}