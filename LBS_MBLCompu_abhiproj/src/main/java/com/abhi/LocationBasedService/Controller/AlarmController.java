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
public class AlarmController {
	
	

		@RequestMapping(value = "/getAlarmData", produces = "application/xml")
		public String getLocationData() throws ConnectorException, IOException {
			
			String xml = "";
			
			CoapClient client2 = new CoapClient("coap://localhost:5686/getAlarmData");
	        
	        CoapResponse response2 = client2.get();
	        
	        if (response2!=null) {        
	        	System.out.println( response2.getCode() );
	        	System.out.println( response2.getOptions() );
	        	System.out.println( response2.getResponseText() );
	        	JSONObject json = new JSONObject(response2.getResponseText());
	        	xml = XML.toString( json );
	        	System.out.println( xml );        	
	        }
	        
	        else {        	
	        	System.out.println("Request failed");        	
	        }
	        
	        return xml;
			
		}
		

}

