package com.abhi.LocationBasedService.Sensor;

import java.util.Random;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

@Component
public class UserLocationSensor {
	
	public UserLocationSensor() {
		
		
		CoapServer server = new CoapServer(5690);
				
				server.add(new getUserLocData());       

		        server.start();  
		        System.out.println("User Location Sensor started");
			}
			
			
			public class getUserLocData extends CoapResource {
				
				
				public getUserLocData() {
					
					super("getUserLocData");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					int [] posdata= {50,100,150,200,250,300,350,400,450,500};
		       
					int rnd = new Random().nextInt(posdata.length);


		    exchange.respond(ResponseCode.CONTENT, "{\"UserLocationData\":" + posdata[rnd] + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}

			

}
