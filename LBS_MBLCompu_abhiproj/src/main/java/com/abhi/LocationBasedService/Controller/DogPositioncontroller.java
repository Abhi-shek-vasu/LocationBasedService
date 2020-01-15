package com.abhi.LocationBasedService.Controller;



import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogPositioncontroller {

	@RequestMapping(value = "/getLocData", produces = "application/xml")
	public String getLocationData() {
		
		String xml = "";
		try {
		CoapClient client1 = new CoapClient("coap://localhost:5685/getLocData");
        
        CoapResponse response1 = client1.get();
        
        if (response1!=null) {        
        	System.out.println( response1.getCode() );
        	System.out.println( response1.getOptions() );
        	System.out.println( response1.getResponseText() );
        	JSONObject json = new JSONObject(response1.getResponseText());
        	xml = XML.toString( json );
        	System.out.println( xml ); 
        	
        	if(json.getInt("LocationData")>70) {   
        		System.out.println( json.getInt("LocationData") );
        		CoapClient client2 = new CoapClient("coap://localhost:5688/setAlarmData");	
    	        CoapResponse response2 = client2.post("{\"AlarmData\":\"ON\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response2.getResponseText() );
    	        
    	        CoapClient client3 = new CoapClient("coap://localhost:5689/setGateData");	
    	        CoapResponse response3 = client3.post("{\"GateData\":\"CLOSE\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response3.getResponseText() );
        	}
        	else {
        		System.out.println( json.getInt("LocationData") );
        		CoapClient client2 = new CoapClient("coap://localhost:5688/setAlarmData");	
    	        CoapResponse response2 = client2.post("{\"AlarmData\":\"OFF\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response2.getResponseText() );
    	        
    	        CoapClient client3 = new CoapClient("coap://localhost:5689/setGateData");	
    	        CoapResponse response3 = client3.post("{\"GateData\":\"OPEN\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response3.getResponseText() );
        	}
        		
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
		}
        catch(Exception e){
        	e.printStackTrace();
        }
        
		
        
        return xml;
		
	}
	
}
