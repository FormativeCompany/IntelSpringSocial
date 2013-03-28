package org.springframework.social.intel.context.api.json;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.intel.context.api.ContextResources;

public class ValueHolder<T> {
	//Item ID to be fetched.
	String id;
	
	// URN indicating the context type contained in the 'value' property. The
	// value must match a type found in /typescatalog.
	String contextType;
    
    //ID of the user who owns the data item.
    String owner;
    
//  IDs of the applications that generate the data item.
    String provider;
    
    //UTC time in ISO format that indicates when the client created the item.
    String clientCreatedTime;
    
    //UTC time in ISO format that indicates when the server created the item.
    String serverCreatedTime;
    
    //UTC time in ISO format that indicates when the server modified the item.
    String serverModifiedTime;
    
    T value;
    
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
		
		if(value instanceof Map<?, ?>) {
			convertValue(contextType);
		}
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

	public String getClientCreatedTime() {
		return clientCreatedTime;
	}

	public void setClientCreatedTime(String clientCreatedTime) {
		this.clientCreatedTime = clientCreatedTime;
	}

	public String getServerCreatedTime() {
		return serverCreatedTime;
	}

	public void setServerCreatedTime(String serverCreatedTime) {
		this.serverCreatedTime = serverCreatedTime;
	}

	public String getServerModifiedTime() {
		return serverModifiedTime;
	}

	public void setServerModifiedTime(String serverModifiedTime) {
		this.serverModifiedTime = serverModifiedTime;
	}

	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
		
		if(value instanceof Map<?, ?> && contextType != null) {
			convertValue(contextType);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void convertValue(String contextType) {
		ContextResources rc = ContextResources.parse(contextType);
		Class<?> type = rc.getType();
		value = (T) new ObjectMapper().convertValue((Map<?,?>)value, type);
	}
}