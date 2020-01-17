package com.abhi.LocationBasedService.Sensor;


import java.util.Random;


import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;
//creating   sensor class
//component is used to treat this class as bean class for component scan
@Component
public class DogPositionSensor {
	
	public DogPositionSensor() {
		
		
CoapServer server = new CoapServer(5685);//defining new server
		
		server.add(new getLocData());   //calling the constructor of inner class getLocData    

        server.start();  
        System.out.println("Dog position sensor started");
	}
	
	//inner  class
	public class getLocData extends CoapResource {
		
		
		public getLocData() {
			
			super("getLocData");
		}
		//inbuilt method of coapResource handel caop get request.logic to return sensor value comes under this
		@Override
        public void handleGET(CoapExchange exchange) {
			
			int [] posdata= {10,20,30,40,50,60,70,80,90,100,110,115,120};
       
			int rnd = new Random().nextInt(posdata.length);


    exchange.respond(ResponseCode.CONTENT, "{\"LocationData\":" + posdata[rnd] + "}", MediaTypeRegistry.APPLICATION_JSON); 
			
	}
	}

	
}


