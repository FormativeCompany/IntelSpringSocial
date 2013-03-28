package org.springframework.social.intel.context.api.json;


/*
               "shortName": "Point of interest check-ins and visits",
                "identifier": "urn:x-intel:context:type:location:checkin"
                "lang": "en",
                "category": "context-type",
                "documentation": "Records visits to points of interest reported either through manual check ins or because the POI is  automatically detected by an application.",
*/
public class LocationCheckin {
	Location location;
	
	//Date and time of start the route
	String datetime;
	
	//Internal identifier of the Point of Interest
	String pointId;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String date) {
		this.datetime = date;
	}

	public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
}