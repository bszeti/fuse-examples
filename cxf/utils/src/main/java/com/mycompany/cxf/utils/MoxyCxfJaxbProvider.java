package com.mycompany.cxf.utils;

import java.lang.reflect.Type;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.cxf.jaxrs.provider.JAXBElementProvider;

/**
 * This CXF provider always uses the jaxBContext set instead of looking up to
 * avoid OSGi class loading problems.
 *
 */
public class MoxyCxfJaxbProvider<T> extends JAXBElementProvider<T> {
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
	public JAXBContext getJAXBContext(Class<?> type, Type genericType) throws JAXBException {
		return getJaxbContext();
	}

	@Override
	public JAXBContext getClassContext(Class<?> type, Type genericType) throws JAXBException {
		return getJaxbContext();
	}

	@Override
	public JAXBContext getPackageContext(Class<?> type, Type genericType) {
		try {
			return getJaxbContext();
		} catch (JAXBException e) {
			LOG.fine(e.getMessage());
		}
		return null;
	}
}
