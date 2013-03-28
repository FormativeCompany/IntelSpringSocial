package org.springframework.social.intel.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ApiUtilities {
//	protected final static DateFormat dateFormat = gmt();
	
	public static DateFormat gmt() {
		DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		gmtFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return gmtFormat;
	}

	public static DateFormat utc() {
		DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		gmtFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return gmtFormat;
	}

	public static Date parseDate(String date) {
		try {
			return gmt().parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String format(Date date) {
		return gmt().format(date);
	}

	public static String today() {
		return format(new Date());
	}

	public static String toJson(Object map) {
		return new JSONSerializer().serialize(map);
	}

	public static HashMap<String, Object> parseJson(String json) {
		return new JSONDeserializer<HashMap<String, Object>>().deserialize(json);
	}

	public static void main(String[] args) {
		String d = "2013-02-10T13:42:30Z";
		System.out.println(today());
		Date dd = parseDate(d);
		System.out.println(dd);
		System.out.println(format(dd));
	}

	public static Date parseDate(Object string, Date date) {
		if(string == null) return date;
		if(string instanceof Date) return (Date)string;
		String newDate = (String)string;
		try {
			return gmt().parse(newDate);
		} catch (ParseException e) {
			return date;
		}
	}
}