package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String UserData) {
		
		//Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(UserData).getAsJsonObject();
		
		//Read the values from the JSON object
		String ID = userObject.get("ID").getAsString();
		String Name = userObject.get("Name").getAsString();
		String Address = userObject.get("Address").getAsString();
		String Telephone = userObject.get("Telephone").getAsString();
		String Email = userObject.get("Email").getAsString();
		String UserName = userObject.get("UserName").getAsString();
		String Password = userObject.get("Password").getAsString();
		
		String output = user.UpdateUser(ID, Name, Address, Telephone, Email, UserName, Password);
		return output;
		
		
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String UserData) {
		
		//Convert the input string to an XML document
				Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());
				String ID = doc.select("ID").text();
				String output = user.deleteUser(ID);
				return output;
		
	}
	
	
	
			
	
	
	
	

}
