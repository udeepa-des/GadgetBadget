package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Fund;

@Path("/Funds")
public class FundService {
	
	Fund fund = new Fund();

	@GET	
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItem(){
		return fund.readItems();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertData(@FormParam("companyName") String companyName,
			@FormParam("researchID") int researchID,
			@FormParam("researcherName") String researcherName,
			@FormParam("investAmount") float investAmount,
			@FormParam("description") String description) {
		String output = fund.insertFunds(companyName, researchID, researcherName, investAmount, description);
		return output;
	}
	
}
