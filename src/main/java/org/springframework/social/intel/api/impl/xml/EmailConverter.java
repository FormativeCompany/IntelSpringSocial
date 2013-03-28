package org.springframework.social.intel.api.impl.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Converter to bind to email elements
 * 
 * @author Michael Lavelle
 */
public class EmailConverter implements Converter {

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Email email = (Email) value;
        writer.setValue(email.getEmail());
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Email email = new Email();
        email.setEmail(reader.getValue());
        return email;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Email.class);
    }
}