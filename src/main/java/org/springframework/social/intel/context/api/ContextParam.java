package org.springframework.social.intel.context.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.social.intel.api.ApiUtilities;

public class ContextParam {

	private ArrayList<String[]> items = new ArrayList<String[]>(10);
	private int limit = -1;
	private Set<String> orderBy = new HashSet<String>();
	
	/**
	 * List of IDs to be fetched, separated by ampersands (&).
	 * @param id
	 */
	public ContextParam addIds(String id){
		return add("ids",id);
	}
	
//		no	URN	Return data with a "contextTypes" attribute in a list separated by ampersands (&).
	public ContextParam addTypes(ContextResources r) {
		return add("contextTypes",r.getIntelUri());
	}
	
	//owners	no	string	ID of the user who owns the data item.
	public ContextParam addOwners(String id){
		return add("owners",id);
	}
	
	/**
	 * IDs, presented in a list, of the applications that generate the data item.
	 * @param id
	 */
	public ContextParam addProviders(String id){
		return add("providers",id);
	}
//	clientCreatedTimeBefore	no	UTC time in ISO format	Return items that have a "clientCreatedTime" attribute earlier than the specified value.
//	clientCreatedTimeAfter	no	UTC time in ISO format	Return items that have a "clientCreatedTime" attribute later than the specified value.
	public ContextParam addClientCreated(Date d, boolean before){
		if(before) {
			return add("clientCreatedTimeBefore",d);
		} else {
			return add("clientCreatedTimeAfter",d);
		}
	}

//	serverCreatedTimeBefore	no	UTC time in ISO format	Return items that have a "serverCreatedTime" attribute earlier than the specified value.
//	serverCreatedTimeAfter	no	UTC time in ISO format	Return items that have a "serverCreatedTime" attribute later than the specified value.

	public ContextParam addServerCreated(Date d, boolean before){
		if(before) {
			return add("serverCreatedTimeBefore",d);
		} else {
			return add("serverCreatedTimeAfter",d);
		}
	}

//	serverModifiedTimeBefore	no	UTC time in ISO format	Return items that have a "serverModifiedTime" attribute earlier than the specified value.
//	serverModifiedTimeAfter	no	UTC time in ISO format	Return items that have a "serverModifiedTime" attribute later than the specified value.

	public ContextParam addServerModified(Date d, boolean before){
		if(before) {
			return add("serverModifiedTimeBefore",d);
		} else {
			return add("serverModifiedTimeAfter",d);
		}
	}
//	$limit	no	string	Maximum number of items to return.
	public ContextParam limit(int limit){
		this.limit = limit;
		return this;
	}
	
//	$orderby	no	enumeration	Sort order for return items by clientCreatedTime, serverCreatedTime, or serverModifiedTime. Defaults to serverCreatedTime.
	public ContextParam orderBy(String items){
		this.orderBy.add(items);
		return this;
	}
	
	private ContextParam add(String name, String value) {
		items.add(new String[]{name,value});
		return this;
	}

	private ContextParam add(String name, Date d) {
		return add(name,ApiUtilities.format(d));
	}
	
	public String toParamString() {
		StringBuilder sb = new StringBuilder();
		boolean q = false;
		String[] i;
		int len = items.size();
		
		if(len > 0){
			i = items.get(0);
			sb.append('?').append(i[0]).append('=').append(i[1]);
			q = true;
			
			for (int j = 1; j < len; j++) {
				i = items.get(j);
				sb.append('&').append(i[0]).append('=').append(i[1]);
			}
		}
		
		if(limit > 0) {
			q = amp(sb, q);
			sb.append("$limit").append('=').append(limit);
		} 
		
		if(!orderBy.isEmpty()) {
			q = amp(sb, q);
			
			sb.append("$orderBy").append('=');
			Iterator<String> o = orderBy.iterator();
			if(o.hasNext()) {
				sb.append(o.next());
			}
			while (o.hasNext()) {
				sb.append(',').append(o.next());
			}
		}
		return sb.toString();
	}

	private boolean amp(StringBuilder sb, boolean q) {
		if(q) {
			sb.append('&');
		}
		else {
			sb.append('?');
			q = true;
		}
		return q;
	}
}
