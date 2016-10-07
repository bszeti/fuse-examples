package com.mycompany.cxf.restServer.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@XmlRootElement(name="response")
@JsonInclude(Include.NON_NULL)
public class ResponseData {
	
	
	private String text;
	private Integer number;
	private String details;
	
	@XmlElement
	@JsonProperty
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@XmlAttribute(name="myText")
	@JsonProperty("myTextProperty")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@XmlAttribute(name="myNumber")
	@JsonProperty("myNumberProperty")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ResponseData [text=" + text + ", number=" + number + ", details=" + details + "]";
	}

	

}
