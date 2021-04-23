package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;

@Path("/User")
public class UserService {
	
	User user = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String read() {
		return user.readUser();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertData(@FormParam("Name") String Name,
			@FormParam("Address") String Address,
			@FormParam("Telephone") String Telephone,
			@FormParam("Email") String Email,
			@FormParam("UserName") String UserName,
			@FormParam("Password") String Password) {
		String output = user.insertUser(Name, Address, Telephone, Email, UserName,Password);
		return output;
		
	}
	
	
	
			
	
	
	
	

}
