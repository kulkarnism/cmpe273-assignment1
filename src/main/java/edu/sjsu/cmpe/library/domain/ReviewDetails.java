package edu.sjsu.cmpe.library.domain;

public class ReviewDetails
{

private int rating;
private String comment;
private int rid;
//private long isbn;
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
/*public long getIsbn() {
	return isbn;
}
public void setIsbn(long isbn) {
	this.isbn = isbn;
}*/

}