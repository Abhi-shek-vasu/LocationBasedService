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
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class Sensor {

	public static void main(String[] args) {
		
		CoapServer server = new CoapServer();
		
		server.add(new GetSensorData());       

        server.start();  
        
	}
	
	public static class GetSensorData extends CoapResource {
		
        public GetSensorData() {
        	
            super("getSensorData");
            
            getAttributes().setTitle("Get Sensor Data");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
        	
        	StringBuilder sb = new StringBuilder();
        	BufferedWriter bw = null;        	
        	BufferedReader br = null;
        	
        	if(!(new File("Data.txt")).exists())
        	{
        		try {
    	        	bw = new BufferedWriter(new FileWriter(new File("Data.txt")));
    	            bw.write("25");
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

            try {            	
            	br = Files.newBufferedReader(Paths.get("Data.txt").toAbsolutePath());

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } 
            
            catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
            
            finally {
            	try {
    				br.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            }

            System.out.println(sb);
        	
            exchange.respond(ResponseCode.CONTENT, "{\"SensorData\":" + sb + "}", MediaTypeRegistry.APPLICATION_JSON);
        }
    }

}
