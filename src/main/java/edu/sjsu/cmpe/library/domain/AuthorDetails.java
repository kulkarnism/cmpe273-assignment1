package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthorDetails
{
private int authid;
@NotEmpty
private String name;
public int getAuthid() {
	return authid;
}
public void setAuthid(int authid) {
	this.authid = authid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}



}
