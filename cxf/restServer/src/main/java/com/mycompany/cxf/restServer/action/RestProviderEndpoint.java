package com.mycompany.cxf.restServer.action;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.cxf.restServer.model.ResponseData;

@Path(value = "/provider/")
public interface RestProviderEndpoint {

	//With bindingStyle=SimpleConsumer
	//Path parameters are added automatically
	//Query parameters must be listed as method parameters, the argument name is ignored
	//The message body is a MessageContentsList by default with the first argument if only annotated values exist or an empty string
	//Boolean is parsed non-case-sensitive
	//The return type can also be the more generic javax.ws.rs.core.Response. CXF actually wraps the return object into a Response automatically.
	
	
	@GET
	@Path(value = "query/{id}") 
	//@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ResponseData restServerGetXml(@QueryParam("text") String arg1, @QueryParam("addDetails") Boolean arg2, String body);

}
