package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	
	//update feedback details
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFb(String FeedbackData)
	{
	//Convert the input string to a JSON object
		
	 JsonObject feedbObject = new JsonParser().parse(FeedbackData).getAsJsonObject();
	//Read the values from the JSON object
	 
	 String ID = feedbObject.get("ID").getAsString();
	 String name = feedbObject.get("Name").getAsString();
	 String contactnum = feedbObject.get("ContactNumber").getAsString();
	 String email = feedbObject.get("Email").getAsString();
	 String comment = feedbObject.get("Comment").getAsString();
	 String OvrlExp = feedbObject.get("overallExperience").getAsString();
	 String output = fObj.updateFb(ID, name, contactnum, email, comment, OvrlExp);
	return output;
	}
	
	
	
}
