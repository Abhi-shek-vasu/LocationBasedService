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
public class HeaterSensor {
	
public HeaterSensor() {
		
		
		CoapServer server = new CoapServer(5693);
				
				server.add(new getHeaterData());       

		        server.start(); 
		        System.out.println("Heater sensors started");
			}
			
			
			public class getHeaterData extends CoapResource {
				
				
				public getHeaterData() {
					
					super("getHeaterData");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					
					StringBuilder HeatStatus= new StringBuilder();
		        	BufferedWriter bw = null;        	
		        	BufferedReader br = null;
		        	
		        	if(!(new File("HeaterData.txt")).exists())
		        	{
		        		try {
		    	        	bw = new BufferedWriter(new FileWriter(new File("HeaterData.txt")));
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
		            	br = Files.newBufferedReader(Paths.get("HeaterData.txt").toAbsolutePath());

		                String line;
		                while ((line = br.readLine()) != null) {
		                	HeatStatus.append(line);
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

		            System.out.println(HeatStatus);

		    exchange.respond(ResponseCode.CONTENT, "{\"HeaterData\":" + HeatStatus + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}


}
