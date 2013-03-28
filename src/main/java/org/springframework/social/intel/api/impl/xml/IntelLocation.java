package org.springframework.social.intel.api.impl.xml;

public class IntelLocation {
	// <country>AR</country>
	// <city>Updated city</city>
	// <region>Updated region</region>
	// <street>Updated street</street>
	// <zipCode>Updated zip code</zipCode>
	
	private String country;
	private String city;
	private String region;
	private String street;
	private String zipCode;
	private String locality;
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
