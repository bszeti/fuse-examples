package com.mycompany.cucumber.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;

import com.mycompany.cucumber.repositories.RestResponse;

import cucumber.api.java.en.Then;

//A new instance of the step classes is created for each cucumber scenario  
@ContextConfiguration(value = "classpath:rest-context.xml")
public class CommonSteps {
	private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);
	
	@Autowired
	private RestResponse restResponse;

	@Then("^status code is \"([^\"]*)\"$")
	public void status_code_is(Integer expectedStatus) throws Throwable {
		assertEquals(expectedStatus, restResponse.getStatusCode());
	}

}
