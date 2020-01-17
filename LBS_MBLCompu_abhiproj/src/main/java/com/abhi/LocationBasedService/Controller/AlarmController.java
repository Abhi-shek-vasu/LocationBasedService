package com.abhi.LocationBasedService.Controller;

import java.io.IOException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Rest controller spring anotation used for Rest call. It act as  a IOT Gateway(protocol translation)
 * It handel Http Get request from client(browser) */
@RestController
public class AlarmController {
	
//  Mapping Request URI and returning xml data 

		@RequestMapping(value = "/getAlarmData", produces = "application/xml")
		public String getAlarmData() throws ConnectorException, IOException {
			
			String xml = "";
			try {
			CoapClient client2 = new CoapClient("coap://localhost:5686/getAlarmData");
	        
	        CoapResponse response2 = client2.get();
	        
	        if (response2!=null) {        
	        	System.out.println( response2.getCode() );
	        	System.out.println( response2.getOptions() );
	        	System.out.println( response2.getResponseText() );
	        	JSONObject json = new JSONObject(response2.getResponseText());
	        	xml = XML.toString( json );//json to xml conversion 
	        	System.out.println( xml );       	
	        }
	        
	        else {        	
	        	System.out.println("Request failed");        	
	        }
			}
			catch(Exception e) {
				
				e.printStackTrace();
			}
	        
	        return xml;
			
		}
		

}

