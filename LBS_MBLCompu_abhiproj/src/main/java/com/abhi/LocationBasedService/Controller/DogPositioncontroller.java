package com.abhi.LocationBasedService.Controller;



import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*Rest controller spring anotation used for Rest call. It act as  a IOT Gateway(protocol translation)
 * It handel Http Get request from client(browser) */

@RestController
public class DogPositioncontroller {
//    Mapping Request URI and returning xml data 
	@RequestMapping(value = "/getLocData", produces = "application/xml")
	public String getLocationData() {
		
		String xml = "";
		try {
		CoapClient client1 = new CoapClient("coap://localhost:5685/getLocData");//uri to map coap resource
        
        CoapResponse response1 = client1.get();
        
        if (response1!=null) {        
        	System.out.println( response1.getCode() );
        	System.out.println( response1.getOptions() );
        	System.out.println( response1.getResponseText() );
        	JSONObject json = new JSONObject(response1.getResponseText());
        	xml = XML.toString( json );//json to xml coversion
        	System.out.println( xml ); 
        	
        	if(json.getInt("LocationData")>70) {   
       		
    	        CoapClient client3 = new CoapClient("coap://localhost:5689/setGateData");//coap post	
    	        CoapResponse response3 = client3.post("{\"GateData\":\"CLOSE\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response3.getResponseText() );
        	}
        	else {
	        
    	        CoapClient client3 = new CoapClient("coap://localhost:5689/setGateData");	// coap post
    	        CoapResponse response3 = client3.post("{\"GateData\":\"OPEN\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response3.getResponseText() );
        	}
        	
        	if(json.getInt("LocationData")>100) {   
        		System.out.println( json.getInt("LocationData") );
        		CoapClient client2 = new CoapClient("coap://localhost:5688/setAlarmData");	//coap post
    	        CoapResponse response2 = client2.post("{\"AlarmData\":\"ON\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response2.getResponseText() );
    	        
    	        
        	}
        	else {
        		System.out.println( json.getInt("LocationData") );
        		CoapClient client2 = new CoapClient("coap://localhost:5688/setAlarmData");//coap post	
    	        CoapResponse response2 = client2.post("{\"AlarmData\":\"OFF\"}", MediaTypeRegistry.APPLICATION_JSON); 
    	        System.out.println( response2.getResponseText() );
    	       
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
