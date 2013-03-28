package org.springframework.social.intel.api.impl.xml;

public class IntelExtended {
	private IntelLocation location;
	private IntelTelephone telephone;

	public IntelLocation getLocation() {
		return location;
	}

	public void setLocation(IntelLocation location) {
		this.location = location;
	}

	public IntelTelephone getTelephone() {
		return telephone;
	}

	public void setTelephone(IntelTelephone telephone) {
		this.telephone = telephone;
	}


	public IntelTelephone ensureTelephone() {
		IntelTelephone telephone = getTelephone();
		if(telephone == null) {
			telephone = new IntelTelephone();
			setTelephone(telephone);
		}
		return telephone;
	}

	public IntelLocation ensureLocation() {
		IntelLocation location = getLocation();
		if(location == null) {
			location = new IntelLocation();
			setLocation(location);
		}
		return location;
	}

}