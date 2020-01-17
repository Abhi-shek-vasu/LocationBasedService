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
import org.springframework.stereotype.Component;

//creating   sensor class
//component is used to treat this class as bean class for component scan
@Component
public class LightSensor {
	
public LightSensor() {
		
		
		CoapServer server = new CoapServer(5691);//defining new server
				
				server.add(new getLightData());       //calling the constructor of inner class getLightData

		        server.start(); 
		        System.out.println("Light sensors started");
			}
			
			//inner class
			public class getLightData extends CoapResource {
				
				
				public getLightData() {
					
					super("getLightData");
				}
				//inbuilt method of coapResource handel caop get request.logic to return sensor value comes under this
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					
					StringBuilder Light = new StringBuilder();
		        	BufferedWriter bw = null;        	
		        	BufferedReader br = null;
		        	
		        	if(!(new File("LightData.txt")).exists())
		        	{
		        		try {
		    	        	bw = new BufferedWriter(new FileWriter(new File("LightData.txt")));
		    	            bw.write("OFF");
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
		            	br = Files.newBufferedReader(Paths.get("LightData.txt").toAbsolutePath());

		                String line;
		                while ((line = br.readLine()) != null) {
		                    Light.append(line);
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

		            System.out.println(Light);

		    exchange.respond(ResponseCode.CONTENT, "{\"LightData\":" + Light + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}


}
