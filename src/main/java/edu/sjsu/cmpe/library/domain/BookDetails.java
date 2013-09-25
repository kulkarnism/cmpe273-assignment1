package edu.sjsu.cmpe.library.domain;

//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;






import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;




//import java.util.ArrayList;
//import java.util.List;
import edu.sjsu.cmpe.library.domain.AuthorDetails;


public class BookDetails
{
	private Long isbn = (long) 0;
	@NotEmpty
	private String title;
	@NotEmpty
	@JsonProperty("publication-date")
	private String pub_date;	
	private String language;
	@JsonProperty("num-pages")
	private int nop = 0;
	
	@Pattern(regexp="(available|checked-out|in-queue|lost)", flags={Pattern.Flag.CASE_INSENSITIVE})
	@DefaultValue("available")
	private String status;
	
	private AuthorDetails[] authors = new AuthorDetails[20];
	private List<ReviewDetails> reviews = new ArrayList<ReviewDetails>();
		
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getNop() {
		return nop;
	}
	public void setNop(int nop) {
		this.nop = nop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AuthorDetails[] getAuthors() {
		return authors;
	}
	public void setAuthors(AuthorDetails[] authors) {
		this.authors = authors;
	}
	public List<ReviewDetails> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewDetails> reviews) {
		this.reviews = reviews;
	}	
	
}
