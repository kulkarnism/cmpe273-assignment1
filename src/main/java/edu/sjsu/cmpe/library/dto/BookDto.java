package edu.sjsu.cmpe.library.dto;

//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.BookDetails;
//import edu.sjsu.cmpe.library.domain.ReviewDetails;

//@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private BookDetails book;
    //private ReviewDetails rev;

    /**
     * @param book
     */
    public BookDto()
    {
    	super();
    }
    
    public BookDto(BookDetails book) {
	super();
	this.book = book;
    }

    /**
     * @return the book
     */
    public BookDetails getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookDetails book) {
	this.book = book;
    }
	
}
