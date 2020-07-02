package com.abhi.LocationBasedService.Actuator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

//creating  alarm actuator class
//component is used to treat this class as bean class for component scan
@Component
public class WindowActuator {
	
	public WindowActuator() {
		
		CoapServer server = new CoapServer(5694);
		
		server.add(new SetWindowData()); //creating coap resource
		

        server.start();

	}
	
	public static class SetWindowData extends CoapResource {
        public SetWindowData() {
        	
            super("SetWindowData");
            
            getAttributes().setTitle("Set Window Data");
        }

        //inbuilt method of coapResource handel caop post request
          
        @Override
        public void handlePOST(CoapExchange exchange) {	
        	
			System.out.println(exchange.getRequestText());			
			exchange.respond(ResponseCode.CONTENT, "{\"message\":\"POST_REQUEST_SUCCESS\"}", MediaTypeRegistry.APPLICATION_JSON);
			
			JSONObject json = new JSONObject(exchange.getRequestText());
			String data = json.get("windowData").toString();
			BufferedWriter bw = null;
	        
	        try {
	        	bw = new BufferedWriter(new FileWriter(new File("windowData.txt")));
	            bw.write(data);
	        } 
	        
	        catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        } 
	        
	        finally {
	        	try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
			
			
		}
}

}
