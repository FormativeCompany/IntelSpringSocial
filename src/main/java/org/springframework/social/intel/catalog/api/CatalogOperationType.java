package org.springframework.social.intel.catalog.api;

import java.util.List;

import org.springframework.social.intel.catalog.api.CatalogOperationType.CatalogItem;

public interface CatalogOperationType<T extends CatalogItem> {
	String createItem(T item);
	T getItems(String id);
	List<T> getItems();
	List<T> browseItems(FilterCriteria criteria);
	void updateItems(T item);
	
	interface CatalogItem {
		String getItemId();
		void setItemId(String id);
	}
}
