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
public class KidsAlertSensor {
	
public KidsAlertSensor() {
		
		
		CoapServer server = new CoapServer(6002);//defining new server
				
				server.add(new getKidsAlert());       //calling the constructor of inner class getAlert

		        server.start(); 
		        System.out.println("Kids Location sensor started");
			}
			
			//inner class
			public class getKidsAlert extends CoapResource {
				
				
				public getKidsAlert() {
					
					super("getKidsAlert");
				}
				//inbuilt method of coapResource handel caop get request.logic to return sensor value comes under this
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
