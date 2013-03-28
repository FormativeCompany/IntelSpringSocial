package org.springframework.social.intel.context.api.json;


/**
                "category": "context-type",
                "documentation": "Records a user's \"like\" or rating events (such as \"+1\") on social networks.",
                "shortName": "Likes & Ratings",
                "identifier": "urn:x-intel:context:type:rating"
				*/
				
public class ContextRating {
	int rating;//min=0, optional
	Target target;//mandatory
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
}

