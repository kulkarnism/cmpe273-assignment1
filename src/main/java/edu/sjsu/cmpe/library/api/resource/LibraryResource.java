package edu.sjsu.cmpe.library.api.resource;


import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





//import com.google.common.base.Optional;
//import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.regex.Pattern;


import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.domain.AuthorDetails;
import edu.sjsu.cmpe.library.domain.BookDetails;
import edu.sjsu.cmpe.library.domain.ReviewDetails;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LibraryResource{
	
	public LibraryResource(){		
	}
	private static long isbn = 0;
	private static int authid = 0;	
	private static final HashMap<Long,BookDetails> bookinfo = new HashMap<Long,BookDetails>();
	
	@POST	
	@Timed(name = "create-book")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(@Valid BookDetails book)
	{
		
		//Generate new isbnnum
		isbn = isbn+1;
		//Load book details into hashmap		
		//Read all the authors
		authid = 0;
		for(int i=0; i< book.getAuthors().length;i++)
		{
			authid=authid+1;
			book.getAuthors()[i].setAuthid(authid);
		}		
		book.setIsbn(isbn);		 
		if(book.getStatus() == null)
		{
			book.setStatus("available");
		}	
				
		bookinfo.put(isbn,book);		
		
		//Create Response		
		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-book", "/books/"   +   book.getIsbn(),"GET"));
	 	bookResponse.addLink(new LinkDto("update-book","/books/"  +  book.getIsbn(), "PUT"));
	 	bookResponse.addLink(new LinkDto("delete-book","/books/"  +   book.getIsbn(),"DELETE"));
	 	bookResponse.addLink(new LinkDto("create-review","/books/"+ book.getIsbn()+"/reviews","POST"));
	 	return Response.status(201).entity(bookResponse).build();
	 	//return bookResponse;
	}
		
	@GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
	public BookDto getBookByIsbn(@PathParam("isbn") Long isbn) {		
			
	 BookDetails bookone = new BookDetails();
     if (bookinfo.containsKey(isbn)==true){
        bookone = bookinfo.get(isbn);
     }
     else
     {
     	 System.out.println("No such book");
     }    
    	 	 
     BookDto bookResponse = new BookDto(bookone);     
 	 bookResponse.addLink(new LinkDto("view-book", "/books/" +  bookone.getIsbn(),"GET"));
 	 bookResponse.addLink(new LinkDto("update-book","/books/" + bookone.getIsbn(),"PUT"));
 	 bookResponse.addLink(new LinkDto("delete-book","/books/"+  bookone.getIsbn(),"DELETE"));
 	 bookResponse.addLink(new LinkDto("create-review","/books/"+  bookone.getIsbn()+"/reviews","POST"));
 	 if(bookone.getReviews().isEmpty() == false)
 	 bookResponse.addLink(new LinkDto("view-all-reviews","/books/"+  bookone.getIsbn()+"/reviews","GET"));
     
     return bookResponse;
		
	}
	
	@DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BookDto deleteBookByIsbn(@PathParam("isbn") Long isbn) {	
		//BookDetails bookone = new BookDetails();
		System.out.println("Inside delete");
	     if (bookinfo.containsKey(isbn)==true){
	        bookinfo.remove(isbn);
	        System.out.println("book removed::"+isbn);
	     }
	     
	     BookDto bookResponse = new BookDto();
	     bookResponse.addLink(new LinkDto("create-book", "/books" ,"POST"));
	     return bookResponse;		
	}
	
	@PUT
	@Path("/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "update-book")
	public BookDto updateBookStatus(@PathParam("isbn") Long isbn, @QueryParam("status")String status)
	{
		
		BookDetails book = new BookDetails();		
		String new_status = (String)status;
		System.out.println("status:"+status);
		if(bookinfo.containsKey(isbn))
		{
		System.out.println("status:"+status+"  isbn::"+isbn);
		book =bookinfo.get(isbn);
		book.setStatus(new_status);
		bookinfo.put(isbn, book);
		}
		 BookDto bookResponse = new BookDto(book);     
	 	 bookResponse.addLink(new LinkDto("view-book", "/books/" +  book.getIsbn(),"GET"));
	 	 bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(),"PUT"));
	 	 bookResponse.addLink(new LinkDto("delete-book","/books/"+  book.getIsbn(),"DELETE"));
	 	 bookResponse.addLink(new LinkDto("create-review","/books/"+  book.getIsbn()+"/reviews","POST"));
	 	 if(book.getReviews().isEmpty() == false)
	 	 bookResponse.addLink(new LinkDto("view-all-reviews","/books/"+  book.getIsbn()+"/reviews","GET"));
	     
	     return bookResponse;		
	}
	
	@POST
	@Path("/{isbn}/reviews")
	@Timed(name = "create-review")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBookReview(@PathParam("isbn") Long isbn,ReviewDetails r)
	{
		
		BookDetails book = new BookDetails();
		int revid = 0;
		System.out.println(r.getComment());
		System.out.println(isbn);
		System.out.println(book.getReviews().size());
		
		if(bookinfo.containsKey(isbn))
		{
			book = bookinfo.get(isbn);
			System.out.println(book.getTitle());
			
			    revid = book.getReviews().size();
			    revid=revid+1;
				r.setRid(revid);
				book.getReviews().add(r);				
		}				
		bookinfo.put(isbn,book);		
		
		//Create Response		
		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-review", "/books/"   +   book.getIsbn()+"/reviews/" + revid,"GET"));
	 	
		return Response.status(201).entity(bookResponse).build();
	 	//return bookResponse;
	 	}	
	
	@GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
	public ReviewDto getreviewbyId(@PathParam("isbn") Long isbn,@PathParam("id") Integer id) {		
			
	 int i =(int)(id-1);	
	 BookDetails bookone = new BookDetails();
     if (bookinfo.containsKey(isbn)==true){
        bookone = bookinfo.get(isbn);
     }
     else
     {
     	 System.out.println("No such book");
     }  
     
     List<ReviewDetails> reviews = new ArrayList<ReviewDetails>();
     ReviewDetails review = new ReviewDetails();
     
     review = bookone.getReviews().get(i);
     reviews.add(review);
     
    	 	 
     ReviewDto reviewResponse = new ReviewDto(reviews);     
     reviewResponse.addLink(new LinkDto("view-review", "/books/"   + isbn+"/reviews/" + id,"GET"));
     return reviewResponse;		
	}
	
	@GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-reviews")
	public ReviewDto getAllReviews(@PathParam("isbn") Long isbn) {		
			
	 	
	 BookDetails bookone = new BookDetails();
     if (bookinfo.containsKey(isbn)==true){
        bookone = bookinfo.get(isbn);
     }
     else
     {
     	 System.out.println("No such book");
     }  
     
     List<ReviewDetails> reviews = new ArrayList<ReviewDetails>();
     
     reviews = bookone.getReviews();	    	 	 
     ReviewDto reviewResponse = new ReviewDto(reviews);     
     //reviewResponse.addLink(new LinkDto("view-review", "/books/"   + isbn+"/reviews/" ,"GET"));
     return reviewResponse;		
	}
	
	@GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
	public AuthorDto getAuthorwbyId(@PathParam("isbn") Long isbn,@PathParam("id") Integer id) {				
	 int i =(int)(id-1);	
	 BookDetails bookone = new BookDetails();
     if (bookinfo.containsKey(isbn)==true){
        bookone = bookinfo.get(isbn);
     }
     else
     {
     	 System.out.println("No such book");
     }       
     AuthorDetails a= new AuthorDetails();     
     a = bookone.getAuthors()[i];   
     List<AuthorDetails> authors = new ArrayList<AuthorDetails>();
     authors.add(a);
    	 	 
     AuthorDto authorResponse = new AuthorDto(authors);     
     authorResponse.addLink(new LinkDto("view-author", "/books/"   + isbn+"/authors/" + id,"GET"));
     return authorResponse;		
	}
	
	//View all authors
	@GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-authors")
	public AuthorDto getAuthors(@PathParam("isbn") Long isbn) {				
	 	
	 BookDetails bookone = new BookDetails();
     if (bookinfo.containsKey(isbn)==true){
        bookone = bookinfo.get(isbn);
     }
     else
     {
     	 System.out.println("No such book");
     }       
     AuthorDetails []a= new AuthorDetails[20];     
     a = bookone.getAuthors();     
     System.out.println("arraysize:"+a.length);
     
     List<AuthorDetails> authors = new ArrayList<AuthorDetails>();
     for(int i=0;i<a.length;i++)
     authors.add(a[i]);
    	 	 
     AuthorDto authorResponse = new AuthorDto(authors);    
     return authorResponse;		
	}
	
}
	



