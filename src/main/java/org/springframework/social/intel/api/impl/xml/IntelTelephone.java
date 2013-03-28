package org.springframework.social.intel.api.impl.xml;

public class IntelTelephone {
	// <mobile>9938373</mobile>
	// <home>Updated home number</home>
	// <work>Updated work number</work>

	private String mobile;
	private String home;
	private String work;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

}