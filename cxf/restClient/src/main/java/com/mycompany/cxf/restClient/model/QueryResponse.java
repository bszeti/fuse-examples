package com.mycompany.cxf.restClient.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "response")
@JsonIgnoreProperties(ignoreUnknown = true) //Ignore extra properties in response
public class QueryResponse {

	private String myText;
	private Integer myNumber;
	private String details;

	@XmlAttribute
	@JsonProperty("myTextProperty")
	public String getMyText() {
		return myText;
	}

	public void setMyText(String myText) {
		this.myText = myText;
	}

	@XmlAttribute
	@JsonProperty("myNumberProperty")
	public Integer getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(Integer myNumber) {
		this.myNumber = myNumber;
	}

	@XmlElement
	@JsonProperty
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
