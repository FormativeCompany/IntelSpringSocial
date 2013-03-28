package org.springframework.social.intel.context.api.json;

import java.math.BigDecimal;

public class Location {
	BigDecimal lat;// not null, minimum = -90.0, max = 90.0
	BigDecimal lon;// not null min = -180, max= 180.0
	double accuracy;// Not null for Checkin

	public Location() {
	}
	
	public Location(double lat, double lon) {
		this.lat = new BigDecimal(lat);
		this.lon = new BigDecimal(lon);
	}

	public Location(double lat, double lon, int accuracy) {
		this.lat = new BigDecimal(lat);
		this.lon = new BigDecimal(lon);
		this.accuracy = accuracy;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

}