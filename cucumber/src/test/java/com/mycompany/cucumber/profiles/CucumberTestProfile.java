package com.mycompany.cucumber.profiles;

import java.io.IOException;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.junit.Cucumber;

public class CucumberTestProfile extends Cucumber {
	private static final Logger log = LoggerFactory.getLogger(CucumberTestProfile.class);

	static {
		System.setProperty("spring.profile.active", "test");
		// Set any other system properties here, e.g. javax.net.ssl.trustStore
	}

	public CucumberTestProfile(Class clazz) throws InitializationError, IOException {
		super(clazz);
	}

}
