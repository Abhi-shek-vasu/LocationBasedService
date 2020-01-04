package com.abhi.LocationBasedService.Controller;



import java.io.IOException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping(value = "/getSensorData", produces = "application/xml")
	public String getSensorData() throws ConnectorException, IOException  {
		
		String xml = "";
		
		CoapClient client1 = new CoapClient("coap://localhost:5683/getSensorData");
        
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
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/setSensorData", consumes = "application/xml")
	public void addTest(@RequestBody String xml) throws ConnectorException, IOException {
		
		CoapClient client2 = new CoapClient("coap://localhost:5684/setSensorData");
		
		JSONObject json = XML.toJSONObject(xml);
		
		System.out.println( xml );		
		System.out.println( json );
        
        CoapResponse response2 = client2.post(json.toString(), MediaTypeRegistry.APPLICATION_JSON);
        
        if (response2!=null) {        
        	System.out.println( response2.getCode() );
        	System.out.println( response2.getOptions() );
        	System.out.println( response2.getResponseText() );        	
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
	}

}
