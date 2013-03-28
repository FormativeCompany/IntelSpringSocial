package org.springframework.social.intel.api.impl.xml;


/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Wrapper for XML unmarshalling
 * 
 * @author Michael Lavelle
 */
public class IntelXmlProfile {

	private IntelProfile basic;
	

	private IntelPersonal personal;
	
	private IntelExtended extended;


	public IntelProfile getBasic() {
		return basic;
	}

	public void setBasic(IntelProfile basic) {
		this.basic = basic;
	}
	
	public IntelExtended getExtended() {
		return extended;
	}
	
	public IntelPersonal getPersonal() {
		return personal;
	}
	
	public void setExtended(IntelExtended extended) {
		this.extended = extended;
	}
	
	public void setPersonal(IntelPersonal personal) {
		this.personal = personal;
	}
	
	public IntelPersonal ensurePersonal() {
		IntelPersonal personal = getPersonal();
		if(personal == null) {
			personal= new IntelPersonal();
			setPersonal(personal);
		}
		return personal;
	}

	public IntelExtended ensureExtended() {
		IntelExtended extended = getExtended();
		if(extended == null) {
			extended = new IntelExtended();
			setExtended(extended);
		}
		return extended;
	}

	public IntelProfile ensureBasic() {
		IntelProfile basic = getBasic();
		if(basic == null) {
			basic = new IntelProfile();
			setBasic(basic);
		}
		return basic;
	}

}
