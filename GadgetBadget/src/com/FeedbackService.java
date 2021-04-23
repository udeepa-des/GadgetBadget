package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Feedback;

public class FeedbackService {

	// view feedback details
	
	Feedback fObj = new Feedback();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFb()
	 {
	 return fObj.readIFb();
	 }
	
	// insert feedback details

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFb(@FormParam("Name") String name,
	 @FormParam("ContactNumber") String contactnum,
	 @FormParam("Email") String email,
	 @FormParam("Comment") String comment,
	 @FormParam("overallExperience") String OvrlExp)
	{
	 String output = fObj.insertFb(name, contactnum, email, comment, OvrlExp);
	return output;
	}
	
	
	
	
}
