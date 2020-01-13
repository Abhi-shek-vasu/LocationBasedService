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
public class HeaterController {
	
	@RequestMapping(value = "/getHeaterData", produces = "application/xml")
	public String getHeaterData() throws ConnectorException, IOException {
		
		String xml = "";
		
		CoapClient client1 = new CoapClient("coap://localhost:5693/getHeaterData");
        
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
	@RequestMapping(method = RequestMethod.POST, value = "/setHeaterData", consumes = "application/xml")
	public void setHeaterData(@RequestBody String xml) throws ConnectorException, IOException {
		
		CoapClient client2 = new CoapClient("coap://localhost:5694/SetHeaterData");
		
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
