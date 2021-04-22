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

import model.Fund;

@Path("/Funds")
public class FundService {
	
	Fund fund = new Fund();

	@GET	
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItem(){
		return fund.readFunds();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertData(@FormParam("companyName") String companyName,
			@FormParam("researchID") String researchID,
			@FormParam("researcherName") String researcherName,
			@FormParam("investAmount") String investAmount,
			@FormParam("description") String description) {
		String output = fund.insertFunds(companyName, researchID, researcherName, investAmount, description);
		return output;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunds(String fundData) {
		
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
		
		String id = fundObject.get("id").getAsString();
		String companyName = fundObject.get("companyName").getAsString();
		String researchID = fundObject.get("researchID").getAsString();
		String researcherName = fundObject.get("researcherName").getAsString();
		String investAmount = fundObject.get("investAmount").getAsString();
		String description = fundObject.get("description").getAsString();
		
		String output = fund.updateFunds(id, companyName, researchID, researcherName, investAmount, description);

		return output;
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunds(String fundData) {
		Document doc  =  Jsoup.parse(fundData, "", Parser.xmlParser());
		String id = doc.select("id").text();
		String output = fund.deleteFund(id);
		return output;
	}
	
	
	
	
}
