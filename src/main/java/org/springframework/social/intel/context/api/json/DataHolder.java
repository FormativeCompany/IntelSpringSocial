package org.springframework.social.intel.context.api.json;


public class DataHolder<T> {
	T data;
	
	public DataHolder() {
	}
	
	public DataHolder(T w) {
		this.data = w;
	}

	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}