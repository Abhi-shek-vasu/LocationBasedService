package com.abhi.LocationBasedService.Controller;

import java.io.IOException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLocationController {
	
	@RequestMapping(value = "/getUserLocSensorData", produces = "application/xml")
	public String getSensorData() throws ConnectorException, IOException {
		
		String xml = "";
		
		CoapClient client1 = new CoapClient("coap://localhost:5690/getUserLocData");
        
        CoapResponse response1 = client1.get();
        
        if (response1!=null) {        
        	System.out.println( response1.getCode() );
        	System.out.println( response1.getOptions() );
        	System.out.println( response1.getResponseText() );
        	JSONObject json = new JSONObject(response1.getResponseText());
        	xml = XML.toString( json );
        	System.out.println( xml ); 
       	
        	if(json.getInt("UserLocationData")<=100) {  
        		System.out.println( json.getInt("UserLocationData") );
	        	CoapClient client2 = new CoapClient("coap://localhost:5692/setLightData");	
	  	        CoapResponse response2 = client2.post("{\"LightData\":\"ON\"}", MediaTypeRegistry.APPLICATION_JSON); 
	   	        System.out.println( response2.getResponseText() );
	   	     CoapClient client3 = new CoapClient("coap://localhost:5694/SetWindowData");	
	  	        CoapResponse response3 = client3.post("{\"windowData\":\"OPEN\"}", MediaTypeRegistry.APPLICATION_JSON); 
	   	        System.out.println( response3.getResponseText() );
	   	        
        }
        	else {
        		System.out.println( json.getInt("UserLocationData") );
        		CoapClient client2 = new CoapClient("coap://localhost:5692/setLightData");	
    	        CoapResponse response2 = client2.post("{\"LightData\":\"OFF\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response2.getResponseText() );
    	        CoapClient client3 = new CoapClient("coap://localhost:5694/SetWindowData");	
    	        CoapResponse response3 = client3.post("{\"windowData\":\"CLOSE\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response3.getResponseText() );
        	}
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
        
        return xml;
		
	}

}
