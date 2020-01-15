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



@Component
public class KidsAlertSensor {
	
public KidsAlertSensor() {
		
		
		CoapServer server = new CoapServer(6002);
				
				server.add(new getKidsAlert());       

		        server.start(); 
		        System.out.println("Kids Location sensor started");
			}
			
			
			public class getKidsAlert extends CoapResource {
				
				
				public getKidsAlert() {
					
					super("getKidsAlert");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					
					StringBuilder Alert = new StringBuilder();
		        	BufferedWriter bw = null;        	
		        	BufferedReader br = null;
		        	
		        	if(!(new File("KidsAlert.txt")).exists())
		        	{
		        		try {
		    	        	bw = new BufferedWriter(new FileWriter(new File("KidsAlert.txt")));
		    	            bw.write("Kids Under Defined Location");
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
		            	br = Files.newBufferedReader(Paths.get("KidsAlert.txt").toAbsolutePath());

		                String line;
		                while ((line = br.readLine()) != null) {
		                    Alert.append(line);
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

		            System.out.println(Alert);

		    exchange.respond(ResponseCode.CONTENT, "{\"AlertData\":" + Alert + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}

}
