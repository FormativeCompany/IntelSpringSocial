package org.springframework.social.intel.context.api.json;

public class Watch {
	// "id": "4406e0ac08f511e2bea00a9b74ce8e68"
	// "contextType": "urn:x-intel:context:location:current",
	// "owner": "6d617474000000000000000000000001",
	// "provider: "6d617474000000000000000000000000",
	// "callbackUrl": "http://api.intel.com/context/v1/testcallback/1000"
	String id;
	String contextType;
	String owner;
	String provider;
	String callbackUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

}
