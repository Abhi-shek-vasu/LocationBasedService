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
public class KidsController {

	@RequestMapping(value = "/getKidsData", produces = "application/xml")
	public String getLocationData() 
	{
		String xml = "";
		try {
		
		
		CoapClient client6 = new CoapClient("coap://localhost:6000/getKidsLocData");
        
        CoapResponse response6 = client6.get();
        
        if (response6!=null) {        
        	System.out.println( response6.getCode() );
        	System.out.println( response6.getOptions() );
        	System.out.println( response6.getResponseText() );
        	JSONObject json = new JSONObject(response6.getResponseText());
        	xml = XML.toString( json );
        	System.out.println( xml );  
        	
        	
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
		}
        catch(Exception e)
		{
		e.printStackTrace();
		}
        
        
        return xml;
		
	
	}
	@RequestMapping(method = RequestMethod.POST, value = "/setKidsData", consumes = "application/xml")
	public void setKidsData(@RequestBody String xml)  {
		try {
		CoapClient client7 = new CoapClient("coap://localhost:6001/setKidsData");
		
		JSONObject json = XML.toJSONObject(xml);
		
		System.out.println( xml );		
		System.out.println( json );
		
		System.out.println( json.getJSONObject("data") );
        
        CoapResponse response7=null;
		try {
			response7 = client7.post(json.getJSONObject("data").toString(), MediaTypeRegistry.APPLICATION_JSON);
		} catch (ConnectorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (response7!=null) {        
        	System.out.println( response7.getCode() );
        	System.out.println( response7.getOptions() );
        	System.out.println( response7.getResponseText() );        	
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
        
//        CoapClient client9 = new CoapClient("coap://localhost:6000/getKidsLocData");
//		
//		JSONObject jsonGet = new JSONObject();
//        
//        CoapResponse response9=client9.get();       
//        
//        if (response9!=null) {        
//        	System.out.println( response9.getCode() );
//        	System.out.println( response9.getOptions() );
//        	System.out.println( response9.getResponseText() );    
//        	jsonGet = new JSONObject(response9.getResponseText());
//        }
//        
//        else {        	
//        	System.out.println("Request failed");        	
//        }
        
        JSONObject jsonPost = new JSONObject();
        
        CoapClient client8 = new CoapClient("coap://localhost:6003/setAlertData");		
        
		if(json.getJSONObject("data").getInt("KidsCurrentData") > json.getJSONObject("data").getInt("KidsData"))
		{
			jsonPost.put("AlertData", "Alert! Kids out of range");
		}
		else
		{
			jsonPost.put("AlertData", "Kids Under Defined Location");
		}		
        
        CoapResponse response8=null;
		try {
			response8= client8.post(jsonPost.toString(), MediaTypeRegistry.APPLICATION_JSON);
		} catch (ConnectorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (response8!=null) {        
        	System.out.println( response8.getCode() );
        	System.out.println( response8.getOptions() );
        	System.out.println( response8.getResponseText() );        	
        }
        
        else {        	
        	System.out.println("Request failed");        	
        }
        
	}catch(Exception e)
		{
		e.printStackTrace();
		}
		}
}
