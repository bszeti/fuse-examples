package com.mycompany.cxf.restClient.action;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycompany.cxf.restClient.model.QueryResponse;

public interface RestClientEndpoint {

	@GET
	@Produces({ MediaType.APPLICATION_XML})
	public QueryResponse restClientQuery();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public QueryResponse restClientQueryJson();

	
}
