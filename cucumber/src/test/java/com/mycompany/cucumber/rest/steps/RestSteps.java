package com.mycompany.cucumber.rest.steps;

import static com.jayway.jsonpath.JsonPath.read;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycompany.cucumber.repositories.RestResponse;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//A new instance of the step classes is created for each cucumber scenario  
@ContextConfiguration(value = "classpath:rest-context.xml")
public class RestSteps {
	private static final Logger log = LoggerFactory.getLogger(RestSteps.class);

	@Value("${url.path:/restservices/provider/query}")
	private String urlPath;

	@Value("${url.base}")
	private String urlBase;

	private RestTemplate restTemplate;

	// Steps in the same class can simply use a member to pass values
	private String id;
	private String text;

	// Steps in different classes require a spring bean to share data
	@Autowired
	private RestResponse restResponse;

	// It's run once per each scenario
	@Before()
	public void prepare() {
		// In this example we use Spring RestTemplate to call the rest service
		restTemplate = new RestTemplate();

		// Basic authentication can be added easily
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "pass"));

		// Avoid exceptions in case of a non-200 response - if we want to verify
		// error messages for example
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			protected boolean hasError(org.springframework.http.HttpStatus statusCode) {
				return false;
			};

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}

		});

		// Add customer http headers if needed
		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add("foo", "bar");
				return execution.execute(request, body);
			}
		});
	}

	@Given("^Id \"([^\"]*)\" and text \"([^\"]*)\"$")
	public void id_and_text(String id, String text) throws Throwable {
		this.id = id;
		this.text = text;
	}

	@When("^I call the rest service$")
	public void i_call_the_rest_service() throws Throwable {
		URI uri = UriComponentsBuilder.fromUriString(urlBase + urlPath).pathSegment(id).queryParam("text", text).build()
				.toUri();
		// Also can add query parameters from a Map<String,String>
		// MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
		// map.setAll(parametersMap);

		// Call service
		log.info("Calling: {}", uri.toString());
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		log.info("Response status: {}", response.getStatusCodeValue());
		log.info("Response body: {}", response.getBody());

		restResponse.setBody(response.getBody());
		restResponse.setStatusCode(response.getStatusCodeValue());
	}

	@Then("^the response contains$")
	public void the_response_contains(Map<String, String> expectedMap) throws Throwable {
		log.info("the_response_contains");

		String message = restResponse.getBody();

		for (String field : expectedMap.keySet()) {
			String expected = expectedMap.get(field);
			assertEquals(expected, read(message, "$." + field).toString());
		}
	}

}
