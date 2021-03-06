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
	public String insert(@FormParam("researchID") String researchID,
			@FormParam("researchName") String researchName,
			@FormParam("Amount") String Amount,
			@FormParam("Description") String Description)
	{
		String output = cartObj.insert(researchID, researchName, Amount, Description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(String cartData)
	{
		//Convert string to a JSON object
		JsonObject cartObject = new JsonParser().parse(cartData).getAsJsonObject();
		//Read values from the JSON object
		String ID = cartObject.get("ID").getAsString();
		String researchID = cartObject.get("researchID").getAsString();
		String researchName = cartObject.get("researchName").getAsString();
		String Amount = cartObject.get("Amount").getAsString();
		String Description = cartObject.get("Description").getAsString();
		String output = cartObj.update(ID, researchID, researchName, Amount, Description);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String cartData)
	{
		//Convert inputs to an XML document
		Document doc = Jsoup.parse(cartData, "", Parser.xmlParser());
		//Read the value from the element <ID>
		String ID = doc.select("ID").text();
		String output = cartObj.delete(ID);
		return output;
	}
	
}


