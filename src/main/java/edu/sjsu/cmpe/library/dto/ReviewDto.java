package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.ReviewDetails;

 //import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 
 
 public class ReviewDto extends LinksDto
 {
	 
	 private List<ReviewDetails> reviews = new ArrayList<ReviewDetails>();
	 
	 
	 public ReviewDto(List<ReviewDetails> reviews)
	    {
	    	super();
	    	this.setRev(reviews);
	    }
	 
	 public List<ReviewDetails> getRev() {
			return reviews;
		}
		public void setRev(List<ReviewDetails> review) {
			this.reviews = review;
		}
 }