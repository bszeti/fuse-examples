package com.mycompany.cxf.utils;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 * This CXF provider always uses the jaxBContext set instead of looking up to
 * avoid OSGi class loading problems. It will generate a JSON output even is it's not set 
 *
 */

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MoxyCxfJsonProvider extends MOXyJsonProvider {
	JAXBContext jaxbContext;

	public JAXBContext getJaxbContext() throws JAXBException {
		if (jaxbContext == null)
			throw new JAXBException("JaxbContext was not set for the provider.");
		return jaxbContext;
	}

	public void setJaxbContext(JAXBContext jaxbContext) {
		this.jaxbContext = jaxbContext;
	}

	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, ?> httpHeaders) throws JAXBException{
		return getJaxbContext();
	}
}
