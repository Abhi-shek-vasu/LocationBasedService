package com.abhi.LocationBasedService.Sensor;

import java.util.Random;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

//creating  sensor class
//component is used to treat this class as bean class for component scan
@Component
public class UserLocationSensor {
	
	public UserLocationSensor() {
		
		
		CoapServer server = new CoapServer(5690);//defining new server
				
				server.add(new getUserLocData());       //calling the constructor of inner class getUserLocData

		        server.start();  
		        System.out.println("User Location Sensor started");
			}
			
	//inner class
			public class getUserLocData extends CoapResource {
				
				
				public getUserLocData() {
					
					super("getUserLocData");
				}
				//inbuilt method of coapResource handel caop get request.logic to return sensor value comes under this
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					int [] posdata= {50,100,150,200,250,300,350,400,450,500};
		       
					int rnd = new Random().nextInt(posdata.length);


		    exchange.respond(ResponseCode.CONTENT, "{\"UserLocationData\":" + posdata[rnd] + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}

			

}
