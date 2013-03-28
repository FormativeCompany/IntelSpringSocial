package org.springframework.social.intel.context.api.json;


/**
Point of interest search history
"identifier": "urn:x-intel:context:type:search"
*/
public class ContextSearch {
	String name;//optional
	
	//Target service for the search E.g: google, tripIt
	String source_domain; //optional
	
	//Search used to perform the search
	String value;//optional
	
	//Date and time when the search was performed in local format including timezone
	String datetime;
	
	//Location where the search was performed
	Location location;
	
	//Type of search. E.g: travel, dinning
	String type;//optional

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource_domain() {
		return source_domain;
	}

	public void setSource_domain(String source_domain) {
		this.source_domain = source_domain;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}