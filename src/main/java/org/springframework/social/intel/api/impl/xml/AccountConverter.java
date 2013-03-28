package org.springframework.social.intel.api.impl.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Converter to bind to account elements
 * 
 * @author Michael Lavelle
 */
public class AccountConverter implements Converter {

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Account account = (Account) value;
        writer.addAttribute("provider", account.getProvider().toString());
        writer.setValue(account.getName());
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Account account = new Account();
        account.setProvider(reader.getAttribute("provider"));

        account.setName(reader.getValue());
        return account;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Account.class);
    }
}