package com;

import model.Cart;
import model.Research;
//For REST Service ----
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON ---
import com.google.gson.*;

//For XML ---
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/research")
public class ResearchService {

	Research researchObj = new Research();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch()
	{
		return researchObj.readResearch();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("rName") String rName,
			@FormParam("rPhone") String rPhone,
			@FormParam("rEmail") String rEmail,
			@FormParam("rAddress") String rAddress,
			@FormParam("projectName") String projectName,
			@FormParam("rCost") String rCost)
	{
		String output = researchObj.insertResearch(rName, rPhone, rEmail, rAddress, projectName, rCost);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String researchData)
	{
		//Convert the input string to a JSON object
		JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject();
		//Read the values from the JSON object
		String RID = researchObject.get("RID").getAsString();
		String rName = researchObject.get("rName").getAsString();
		String rPhone = researchObject.get("rPhone").getAsString();
		String rEmail = researchObject.get("rEmail").getAsString();
		String rAddress = researchObject.get("rAddress").getAsString();
		String projectName = researchObject.get("projectName").getAsString();
		String rCost = researchObject.get("rCost").getAsString();
		String output = researchObj.updateResearch(RID, rName, rPhone, rEmail, rAddress,projectName, rCost);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String researchData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researchData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String RID = doc.select("RID").text();
		String output = researchObj.deleteResearch(RID);
		return output;
	}
	
}
