package com.mycompany.cxf.restServer.route;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.component.bean.BeanInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.cxf.restServer.model.ResponseData;

public class RouteHelper {
	private static final Logger log = LoggerFactory.getLogger(RouteHelper.class);
	
	public static ResponseData getResponseData(Exchange exchange, @Header("text") String text , @Header("id") Integer id) {
		ResponseData responseData = new ResponseData();
		responseData.setText(String.format("Echo: %s", text));
		responseData.setNumber(id);
		if (Boolean.TRUE.equals(exchange.getIn().getHeader("addDetails",Boolean.class))){
			responseData.setDetails(String.format("Text: %s; Id: %d", text,id));
		} 
		return responseData;
	}

	public static void bindHeadersFromInvocation(@Headers Map<String,Object> headers, @Body BeanInvocation body){
		headers.put("id",body.getArgs()[0]);
		headers.put("text",body.getArgs()[1]);
		headers.put("addDetails",body.getArgs()[2]);
	}
}
