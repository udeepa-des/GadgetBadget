package com;

import javax.ws.rs.GET;
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
	
	
	
	
	
	
}
