package org.springframework.social.intel.context.api.json;

public class ItemHolder<T> {
	T items;
	
	public T getItems() {
		return items;
	}
	
	public void setItems(T items) {
		this.items = items;
	}
}