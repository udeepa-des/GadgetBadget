package com;

import model.Cart;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/carts")
public class CartService {

	Cart cartObj = new Cart();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String read()
	{
		return cartObj.read();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert(@FormParam("projCode") String projCode,
			@FormParam("projName") String projName,
			@FormParam("Amount") String Amount,
			@FormParam("Description") String Description)
	{
		String output = cartObj.insert(projCode, projName, Amount, Description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(String cartData)
	{
		//Convert the input string to a JSON object
		JsonObject cartObject = new JsonParser().parse(cartData).getAsJsonObject();
		//Read the values from the JSON object
		String ID = cartObject.get("ID").getAsString();
		String projCode = cartObject.get("projCode").getAsString();
		String projName = cartObject.get("projName").getAsString();
		String Amount = cartObject.get("Amount").getAsString();
		String Description = cartObject.get("Description").getAsString();
		String output = cartObj.update(ID, projCode, projName, Amount, Description);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String cartData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(cartData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String ID = doc.select("ID").text();
		String output = cartObj.delete(ID);
		return output;
	}
	
}


