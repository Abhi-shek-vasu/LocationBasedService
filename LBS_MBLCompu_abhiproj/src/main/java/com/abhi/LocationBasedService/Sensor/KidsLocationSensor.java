package com.abhi.LocationBasedService.Sensor;

 
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Random;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.stereotype.Component;


@Component
public class KidsLocationSensor {
	
public KidsLocationSensor() {
		
		
		CoapServer server = new CoapServer(6000);
				
				server.add(new getKidsLocData());       

		        server.start();  
		        System.out.println("Kids Location Sensor started");
			}
			
			
			public class getKidsLocData extends CoapResource {
				
				
				public getKidsLocData() {
					
					super("getKidsLocData");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
				
					
					ArrayList<String> data = new ArrayList<>();
					data.add("5");data.add("10");data.add("20");data.add("30");data.add("50");data.add("40");
                     
		
			
			StringBuilder kidval = new StringBuilder();
        	BufferedWriter bw = null;        	
        	BufferedReader br = null;
        	
        	if(!(new File("KidsData.txt")).exists())
        	{
        		try {
    	        	bw = new BufferedWriter(new FileWriter(new File("KidsData.txt")));
    	            bw.write("15");
    	            
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
	            	br = Files.newBufferedReader(Paths.get("KidsData.txt").toAbsolutePath());

	                String line;
	                while ((line = br.readLine()) != null) {
	                	kidval.append(line);
	                	
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

			    data.add(kidval.toString());
			    
			    int val = new Random().nextInt(data.size());
			    String newval1=data.get(val);
		    exchange.respond(ResponseCode.CONTENT, "{\"KidsData\":" +newval1+ "}", MediaTypeRegistry.APPLICATION_JSON); 
                       	
			}
			}


}
