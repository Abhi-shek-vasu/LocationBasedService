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
public class LightActuator {
	

	public LightActuator() {
			
			CoapServer server = new CoapServer(5692);
			
			server.add(new SetLightData()); //creating coap resource
			System.out.println("Light Actuator started");

	        server.start();

		}
		
		public static class SetLightData extends CoapResource {
	        public SetLightData() {
	        	
	            super("setLightData");
	            
	            getAttributes().setTitle("Set Sensor Data");
	        }

	        //inbuilt method of coapResource handel caop post request
	          
	        @Override
	        public void handlePOST(CoapExchange exchange) {	
	        	
				System.out.println(exchange.getRequestText());			
				exchange.respond(ResponseCode.CONTENT, "{\"message\":\"POST_REQUEST_SUCCESS\"}", MediaTypeRegistry.APPLICATION_JSON);
				
				JSONObject json = new JSONObject(exchange.getRequestText());
				String data = json.get("LightData").toString();
				BufferedWriter bw = null;
		        
		        try {
		        	bw = new BufferedWriter(new FileWriter(new File("LightData.txt")));
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
