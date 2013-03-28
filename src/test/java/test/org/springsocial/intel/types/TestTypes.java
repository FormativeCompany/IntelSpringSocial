package test.org.springsocial.intel.types;

import java.io.IOException;
import java.lang.reflect.Array;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.intel.api.utilities.NewJSonHttpMessageConverter;
import org.springframework.social.intel.context.api.ContextResources;
import org.springframework.social.intel.context.api.json.DataHolder;
import org.springframework.social.intel.context.api.json.ItemHolder;
import org.springframework.social.intel.context.api.json.LocationCurrent;
import org.springframework.social.intel.context.api.json.ValueHolder;

@SuppressWarnings({ "unchecked"})
public class TestTypes {

	public static void main(String[] args) {
		System.out.println(ContextResources.url(new LocationCurrent()));
		TypeReference<?> ref1;
		TypeReference<?> ref2;
		
//		ref1 = new TypeReference<DataHolder<AccessResponse>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<AccessResponse>>>() {};
//		testAll(AccessResponse.class, ref1, ref2, mAccessResponse());
//		
//		ref1 = new TypeReference<DataHolder<AccessRequest>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<AccessRequest>>>() {};
//		testAll(AccessRequest.class, ref1, ref2, mAccessRequest());
//
//		ref1 = new TypeReference<DataHolder<UserInstanceInfo>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<UserInstanceInfo>>>() {};
//		testAll(UserInstanceInfo.class, ref1, ref2, mUserInfo());
//		
//		ref1 = new TypeReference<DataHolder<InstanceInfo>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<InstanceInfo>>>() {};
//		testAll(InstanceInfo.class,ref1, ref2, mInstanceInfo());
//		
//		ref1 = new TypeReference<DataHolder<Watch>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<Watch>>>() {};
//		testAll(Watch.class, ref1, ref2, mWatches());
//		
//		ref1 = new TypeReference<DataHolder<PossibleStep>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<PossibleStep>>>() {};
//		testAll(PossibleStep.class, ref1, ref2, mPlace());
//		
//		ref1 = new TypeReference<DataHolder<PossiblePoi>>() {};
//		ref2 = new TypeReference<DataHolder<ItemHolder<PossiblePoi>>>() {};
//		testAll(PossiblePoi.class, ref1, ref2, mPoi());
		
		ref1 = new TypeReference<DataHolder<ValueHolder<?>>>() {};
		ref2 = new TypeReference<DataHolder<ItemHolder<ValueHolder<?>>>>() {};
		testAll(ValueHolder.class, ref1, ref2, mLocationCurrent());
	}
	
	static String mLocationCurrent() {
		return " {" + 
				"                \"id\": \"6e59f9e8db7311e183be0a407f65ef5d\"," + 
				"                \"contextType\": \"urn:x-intel:context:type:location:current\"," + 
				"                \"owner\": \"6d617474000000000000000000000001\"," + 
				"                \"provider\": \"6d617474000000000000000000000000\"," + 
				"                \"clientCreatedTime\": \"2012-01-19T17:23:15Z\"," + 
				"                \"serverModifiedTime\": \"2012-08-01T00:54:21Z\"," + 
				"                \"serverCreatedTime\": \"2012-08-01T00:54:21Z\"," + 
				"                \"value\":" + 
				"                    {\"latitude\":45.520082,\"longitude\": 122.676103,\"accuracy\":50}" + 
				"             }";
	}
	
	static String mPoi() {
		return "{" + 
				"         \"weight\" : 22.29166666666667," + 
				"         \"attributes\" : {" + 
				"             \"price\" : 2," + 
				"             \"cuisine\" : \"French\"" + 
				"        }" + 
				"    }";
	}
	
	static String mPlace() {
		return "{" + 
				"    \"distance-factor\": 0.232717611823722," + 
				"    \"centroid\": {" + 
				"        \"lon\": -122.973328533333," + 
				"        \"lat\": 45.5199697333333" + 
				"    }," + 
				"    \"weight\": 7.56332238427096," + 
				"    \"place-id\": \"abc10613-df3f-450c-b32a-f9168e14f291\"," + 
				"    \"poi-type\": \"restaurant\"" + 
				"	}";
	}

	static String mWatches() {
		return " {" + 
				"            \"id\": \"4406e0ac08f511e2bea00a9b74ce8e68\"," + 
				"            \"contextType\": \"urn:x-intel:context:location:current\"," + 
				"            \"owner\": \"6d617474000000000000000000000001\"," + 
				"            \"provider\": \"6d617474000000000000000000000000\"," + 
				"            \"callbackUrl\": \"http://api.intel.com/context/v1/testcallback/1000\"" + 
				"}";
	}
	
	static String mUserInfo() {
		return " {       \"id\": \"c\"," + 
				"        \"instanceIds\": [" + 
				"            \"b\"," + 
				"            \"e\"," + 
				"            \"d\"" + 
				"        ]" + 
				"    }" + 
				"";
	}
	
	static String mInstanceInfo() {
		return "{" + 
				"        \"id\": \"test-instance\"," + 
				"        \"owner\": \"test-owner\"," + 
				"        \"clientId\": \"test-client\"" + 
				"    }";
	}
	
	static String mAccessRequest(){
	    return "{"+
	        "\"reason\": { \"en\": \"These permissions are needed to enable access policy management\"},"+
	        "\"permissions\": ["+
	            "{"+
	                "\"access\": ["+
	                "    \"create\","+
	               "     \"read\""+
	              "   ],"+
	             "    \"resourceUri\": \"urn:x-intel:context:type:location:route\""+
	            "   }"+
	            "] "+
	     "}";
	}
	
	static String mAccessResponse() {
		return 
				"		    {" + 
				"		    	\"lang\": \"en\"," + 
				"		    	\"privacyPolicyUrl\": null," + 
				"		    	\"requesterInstanceId\": \"test instance\"," + 
				"		    	\"resourceOwnerId\": \"test-owner\"," + 
				"		    	\"requesterUserId\": \"test-user\"," + 
				"		    	\"state\": \"approved\"," + 
				"		    	\"optionLabel\": null," + 
				"		    	\"reason\": \"These permissions are needed to enable access policy management\"," + 
				"		    	\"modifiedDate\": \"2012-09-19T22:49:15Z\"," + 
				"		    	\"requesterClientId\": \"test-client\"," + 
				"		    	\"createdDate\": \"2012-09-19T22:58:15Z\"," + 
				"		    	\"id\": \"7ce9e320075011e292840a9b74ce8e6a\"," + 
				"		    	\"permissions\": [" + 
				"		        	{" + 
				"		            	\"access\": [" + 
				"		                	\"create\"," + 
				"		                	\"read\"" + 
				"		            	]," + 
				"		            \"resourceUri\": \"urn:x-intel:context:type:location:route\"" + 
				"		        	}" + 
				"		    	]" + 
				"			}" ;

	}
	
	static String data(String s) {
		return "{" + 
				"		    \"data\": " +s+ 
				"}";
	}
	
	static String item(String s) {
		return "{" + 
				"		    \"items\": " +s+ 
				"}";
	}
	
	//{data:}
	static <T> void testData(TypeReference<T> class1, String s1) {
		try {
			String s = data(s1);
			
			NewJSonHttpMessageConverter mj = new NewJSonHttpMessageConverter(class1);
			
			DataHolder<T> o = ((DataHolder<T>) mj.read(DataHolder.class, new Req(s)));
			System.out.println(o);
			System.out.println(o.getData().getClass());
			
			Res res = new Res();
			mj.write(o, MediaType.APPLICATION_JSON, res);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//{data:items:}
	private static <T> void testDataItems(TypeReference<?> class1, String s1) {
		try {
			String s = data(item(s1));
			NewJSonHttpMessageConverter mj = new NewJSonHttpMessageConverter(class1);
			DataHolder<ItemHolder<T>> o = ((DataHolder<ItemHolder<T>>) mj.read(DataHolder.class, new Req(s)));
			System.out.println(o);
			System.out.println(o.getData().getClass());
			System.out.println(o.getData().getItems().getClass());
			
			Res res = new Res();
			mj.write(o, MediaType.APPLICATION_JSON, res);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//{}
	static <T> void testObject(Class<T> t, String s) {
		try {
			MappingJacksonHttpMessageConverter mj = new MappingJacksonHttpMessageConverter();
			T o = (T) mj.read(t, new Req(s));
			System.out.println(o);
			
			Res res = new Res();
			mj.write(o, MediaType.APPLICATION_JSON, res);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//[{}]
	static <T> void testArray(Class<T> t, String s) {
		try {
			MappingJacksonHttpMessageConverter mj = new MappingJacksonHttpMessageConverter();
			T[] o = (T[]) mj.read(Array.newInstance(t, 1).getClass(), new Req("["+s+","+s+","+s+"]"));
			System.out.println(o);
			
			Res res = new Res();
			mj.write(o, MediaType.APPLICATION_JSON, res);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static <T> void testAll(Class<T> class1, TypeReference<?> ref1, TypeReference<?> ref2, String s1) {
		System.out.println("---------"+class1.getSimpleName()+"----------");
		testObject(class1, s1);
		System.out.println("-------------------");
		testArray(class1, s1);
		System.out.println("-------------------");
		testData(ref1, s1);
		System.out.println("-------------------");
		testDataItems(ref2, s1);
	}
}