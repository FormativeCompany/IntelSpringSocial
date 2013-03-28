package org.springframework.social.intel.context.api.json;


/**
 * "lang": "en", "category": "context-type", "documentation":
 * "Current location reported by the application. This context is typically gathered using the device's location capabilities."
 * , "shortName": "Current Location", "identifier":
 * "urn:x-intel:context:type:location:current"
 */
public class LocationCurrent {
	double altitude;// in meters, optional
	double longitude;// decimal degree, mandatory;
	double latitude;// decimal degree, mandatory
	double altitudeAccuracy;// min 0, Estimated accuracy of reported altitude in
							// meters., optional
	double speed;// min 0,Ground speed in meters per second., optional
	double heading;// min=0, max=360, Direction in decimal degree measured in a
					// clockwise direction starting at true north.,optional
	double accuracy;// min=0,optional, Estimated accuracy of reported
					// latitude,longitude coordinate in meters.

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}

	public void setAltitudeAccuracy(double altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
}