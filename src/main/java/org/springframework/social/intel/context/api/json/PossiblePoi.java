package org.springframework.social.intel.context.api.json;

import java.util.HashMap;

public class PossiblePoi {
//	{
//        "weight" : 67.91666666666667,
//        "attributes" : {
//            "price" : 1,
//            "cuisine" : "Japanese"
//        }
//    }
	
	double weight;
	
	//price, cuisine
	HashMap<String,String> attributes;

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
	
	
}
