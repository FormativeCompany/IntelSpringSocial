package org.springframework.social.intel.context.api.json;

public class Point {
	Location location;// mandatory
	String datetime;// Date and time of origin of the route

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}