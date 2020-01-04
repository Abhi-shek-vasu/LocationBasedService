package com.abhi.LocationBasedService.Sensor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.abhi.LocationBasedService.Sensor.Sensor.GetSensorData;

public class CarLocation {
	
	public CarLocation() {
		
CoapServer server = new CoapServer();
		
		server.add(new GetCarLocData());       

        server.start();  
		
	}


	public static class GetCarLocData extends CoapResource {
		
        public GetCarLocData() {
        	
            super("getCarLocData");
            
           
        }

//        @Override
//        public void handleGET(CoapExchange exchange) {
//        	
//        	
//        	
//            exchange.respond(ResponseCode.CONTENT, "{\"SensorData\":" +  + "}", MediaTypeRegistry.APPLICATION_JSON);
//        }
}
}
