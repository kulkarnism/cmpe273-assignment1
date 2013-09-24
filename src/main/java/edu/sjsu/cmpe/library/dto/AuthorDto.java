package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.AuthorDetails;


public class AuthorDto extends LinksDto{
	
	
	 private List<AuthorDetails> authors = new ArrayList<AuthorDetails>();
	 
	public AuthorDto(List<AuthorDetails> authors)
	{
		super();
		this.setAuthors(authors);
	}

	public List<AuthorDetails> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDetails> authors) {
		this.authors = authors;
	}
	
}