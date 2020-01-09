package com.abhi.LocationBasedService.Controller;

import java.io.IOException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import org.eclipse.californium.elements.exception.ConnectorException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightController {
	
	@RequestMapping(value = "/getLightData", produces = "application/xml")
	public String getLightData() throws ConnectorException, IOException {
		
		String xml = "";
		
		CoapClient client1 = new CoapClient("coap://localhost:5691/getLightData");
        
        CoapResponse response1 = client1.get();
        
        if (response1!=null) {        
        	System.out.println( response1.getCode() );
        	System.out.println( response1.getOptions() );
        	System.out.println( response1.getResponseText() );
        	JSONObject json = new JSONObject(response1.getResponseText());
        	xml = XML.toString( json );
        	System.out.println( xml ); 
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
        
        return xml;
		
	}


}
