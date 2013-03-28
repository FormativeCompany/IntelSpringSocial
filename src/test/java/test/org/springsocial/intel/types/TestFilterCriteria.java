package test.org.springsocial.intel.types;

import org.springframework.social.intel.catalog.api.FilterCriteria;

public class TestFilterCriteria {

	public static void main(String[] args) {
		FilterCriteria fc = new FilterCriteria();
		fc.field("name");
		fc.field("address");
		fc.le(true, "userId", "23s42423");
		fc.gt(true, "time", "23/T/T");
		fc.group();
		fc.le(false, "appId", "sdfsf");
		fc.group();
		fc.ne(false, "adsadf", "aaaa");
		fc.sAsc("name");
		fc.sDsc("address");
		fc.limit(234234);
		System.out.println(fc);
	}

}
