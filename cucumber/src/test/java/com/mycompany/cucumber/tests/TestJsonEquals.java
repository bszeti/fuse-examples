package com.mycompany.cucumber.tests;

import static com.mycompany.cucumber.utils.JsonEquals.equalsIgnoreArrayOrdering;
import static com.mycompany.cucumber.utils.JsonEquals.equalsJson;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

//These unit tests a run in case of "mvn test" without any profile
public class TestJsonEquals {
	
	@Test
	public void checkEquals() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		
		assertTrue(equalsJson(jsonA,jsonB));
		assertTrue(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}
	
	@Test
	public void checkEqualsDifferentOrder() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/different-order.json"),Charset.forName("UTF-8"));
		
		assertTrue(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}
	
	@Test
	public void checkDiffExtraField() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/extra-field.json"),Charset.forName("UTF-8"));
		
		assertFalse(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}	
	
	@Test
	public void checkDiffExtraElement() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/extra-element.json"),Charset.forName("UTF-8"));
		
		assertFalse(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}	

	@Test
	public void checkDiffMissingField() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/missing-field.json"),Charset.forName("UTF-8"));
		
		assertFalse(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}	
	@Test
	public void checkDiffMissingElement() throws Exception {
		String jsonA = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/a.json"),Charset.forName("UTF-8"));
		String jsonB = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/missing-element.json"),Charset.forName("UTF-8"));
		
		assertFalse(equalsIgnoreArrayOrdering(jsonA,jsonB));
		
	}	

}
