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
public class GateSensor {
	

	public GateSensor() {
		
		
		CoapServer server = new CoapServer(5687);
				
				server.add(new getGateData());       

		        server.start();  
		        System.out.println("gate Actuator started");
			}
			
			
			public class getGateData extends CoapResource {
				
				
				public getGateData() {
					
					super("getGateData");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
					StringBuilder sb = new StringBuilder();
		        	BufferedWriter bw = null;        	
		        	BufferedReader br = null;
		        	
		        	if(!(new File("GateData.txt")).exists())
		        	{
		        		try {
		    	        	bw = new BufferedWriter(new FileWriter(new File("GateData.txt")));
		    	            bw.write("OPEN");
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
		            	br = Files.newBufferedReader(Paths.get("GateData.txt").toAbsolutePath());

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
				
		    exchange.respond(ResponseCode.CONTENT, "{\"GateData\":" + sb + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}


}
