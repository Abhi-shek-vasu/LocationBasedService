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
public class WindowSensor {
	
public WindowSensor() {
		
		
		CoapServer server = new CoapServer(5693);
				
				server.add(new getWindowData());       

		        server.start(); 
		        System.out.println("Window sensors started");
			}
			
			
			public class getWindowData extends CoapResource {
				
				
				public getWindowData() {
					
					super("getWindowData");
				}
				
				@Override
		        public void handleGET(CoapExchange exchange) {
					
					
					StringBuilder WindowStatus= new StringBuilder();
		        	BufferedWriter bw = null;        	
		        	BufferedReader br = null;
		        	
		        	if(!(new File("WindowData.txt")).exists())
		        	{
		        		try {
		    	        	bw = new BufferedWriter(new FileWriter(new File("windowData.txt")));
		    	            bw.write("CLOSE");
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
		            	br = Files.newBufferedReader(Paths.get("windowData.txt").toAbsolutePath());

		                String line;
		                while ((line = br.readLine()) != null) {
		                	WindowStatus.append(line);
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

		            System.out.println(WindowStatus);

		    exchange.respond(ResponseCode.CONTENT, "{\"windowData\":" + WindowStatus + "}", MediaTypeRegistry.APPLICATION_JSON); 
					
			}
			}


}
