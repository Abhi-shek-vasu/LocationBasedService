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
public class KidsAlertController {
	
	@RequestMapping(value = "/getKidsAlert", produces = "application/xml")
	public String getLocationData() throws ConnectorException, IOException {
		
		String xml = "";
		
		CoapClient client3 = new CoapClient("coap://localhost:6002/getKidsAlert");
        
        CoapResponse response3 = client3.get();
        
        if (response3!=null) {        
        	System.out.println( response3.getCode() );
        	System.out.println( response3.getOptions() );
        	System.out.println( response3.getResponseText() );
        	JSONObject json = new JSONObject(response3.getResponseText());
        	xml = XML.toString( json );
        	System.out.println( xml );        	
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
        
        return xml;
		
	}

}
