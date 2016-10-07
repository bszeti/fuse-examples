package com.mycompany.cxf.restServer.utils;

import javax.ws.rs.core.Response;
import javax.xml.bind.UnmarshalException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This exception mapper can be added as a provider to handle exceptions during CXF processing
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
	private static final Logger log = LoggerFactory.getLogger(ExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {
		log.error("Error in CXF request/response handling");
		
		if (exception.getCause() instanceof UnmarshalException)
			return Response.status(405).entity("Wrong request body").build();
//		The returned entity can also be an object that CXF will try to marhsall using the available providers
		return Response.status(500).entity("Unexpected error").build();
	}

}
