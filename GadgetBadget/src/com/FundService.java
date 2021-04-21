package com;

import javax.ws.rs.GET;
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
	
}
