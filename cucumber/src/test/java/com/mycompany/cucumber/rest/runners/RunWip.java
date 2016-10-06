package com.mycompany.cucumber.rest.runners;

import org.junit.runner.RunWith;

import com.mycompany.cucumber.profiles.CucumberTestProfile;

import cucumber.api.CucumberOptions;

@RunWith(CucumberTestProfile.class)
@CucumberOptions(
		plugin = {"json:target/cucumber.json"},
		tags = {"@wip"},
		glue = {"com.mycompany.cucumber.rest.steps", "com.mycompany.cucumber.steps"}, //Using service specific and common steps
		features = {"classpath:features/"}, //Path for feature files
		strict=true)
public class RunWip {

}
