package com.mycompany.cxf.restServer.action;

import com.mycompany.cxf.restServer.model.ResponseData;

public interface OsgiService {
	//Bean annotation can be used from Camel 2.16 only to bind method parameters to message automatically
	public ResponseData query(Integer id, String text, Boolean addDetails);
}
