package com.abhi.LocationBasedService.Sensor;


import java.util.Random;


import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;

@Component
public class DogPositionSensor {
	
	public DogPositionSensor() {
		
		
CoapServer server = new CoapServer(5685);
		
		server.add(new getLocData());       

        server.start();  
        System.out.println("Dog position sensor started");
	}
	
	
	public class getLocData extends CoapResource {
		
		
		public getLocData() {
			
			super("getLocData");
		}
		
		@Override
        public void handleGET(CoapExchange exchange) {
			
			int [] posdata= {10,20,30,40,50,60,70,80,90,100};
       
			int rnd = new Random().nextInt(posdata.length);


    exchange.respond(ResponseCode.CONTENT, "{\"LocationData\":" + posdata[rnd] + "}", MediaTypeRegistry.APPLICATION_JSON); 
			
	}
	}

	
}


