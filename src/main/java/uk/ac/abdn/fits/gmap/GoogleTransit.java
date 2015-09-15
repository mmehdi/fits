package uk.ac.abdn.fits.gmap;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import uk.ac.abdn.fits.gmap.model.DirectionsResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GoogleTransit {

	public static ObjectMapper mapper = new ObjectMapper();
	
	public static int countNum = 0;
	
	public DirectionsResponse getDataBinding() {
		
		DirectionsResponse response = null;
		
		try {
			String dir = this.getClass().getClassLoader().getResource("").getPath();
//			System.out.println("binding: "+dir.toString().replace("/", "\\\\")+"transit"+countNum+".json");
			response = mapper.readValue(new File(dir.toString().replace("/", "\\\\")+"transit"+countNum+".json"), 
					DirectionsResponse.class);
			if(response == null){
				return null;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(response.getStatus().equals("OK")){
			System.out.println("data binding(json file) is done. Status: OK");
		}else{
			System.err.println("data binding(json file) is done. Status: " + response.getStatus());
		}
		countNum++;
		return response;
	}
	
	public DirectionsResponse getDataBinding(String filename) {
		
		DirectionsResponse response = null;
		try {
			String dir = this.getClass().getClassLoader().getResource("").getPath();
			response = mapper.readValue(new File(dir.toString().replace("/", "\\\\")+""+filename+".json"), 
					DirectionsResponse.class);
			if(response == null){
				return null;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			return null;
		}
		if(!response.getStatus().equals("OK")){
			System.err.println("data binding(json file) is done. Status: " + response.getStatus());
		}
		countNum++;
		return response;
	}
	
	
	public void saveGoogleTransitJsonFile(String url){
		
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost","proxy.abdn.ac.uk");
		System.setProperty("http.proxyPort", "8080");
		URL website = null;
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			website = new URL(url);
			rbc = Channels.newChannel(website.openStream());
			String dir = this.getClass().getClassLoader().getResource("").getPath();
			fos = new FileOutputStream(new File(dir.toString().replace("/", "\\\\")+"transit"+countNum+".json"));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.flush();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveGoogleTransitJsonFile(String filename, String url){
		
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost","proxy.abdn.ac.uk");
		System.setProperty("http.proxyPort", "8080");
		URL website = null;
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			website = new URL(url);
			rbc = Channels.newChannel(website.openStream());
			String dir = this.getClass().getClassLoader().getResource("").getPath();
			fos = new FileOutputStream(new File(dir.toString().replace("/", "\\\\")+""+filename+".json"));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.flush();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getURL(String origin, String destn, long departure_time, String mode){
		
		String url = "http://maps.googleapis.com/maps/api/directions/json?origin=";
		url += origin;
		url += "&destination=";
		url += destn;
		url += "&sensor=false";
		url += "&departure_time=";
		url += (departure_time/1000);
		url += "&mode=";
		url += mode;
		return url;
	}
	
	public String getURL_arrival_time(String origin, String destn, long arrival_time, String mode){
		
		String url = "http://maps.googleapis.com/maps/api/directions/json?origin=";
		url += origin;
		url += "&destination=";
		url += destn;
		url += "&sensor=false";
		url += "&arrival_time=";
		url += (arrival_time/1000);
		url += "&mode=";
		url += mode;
		return url;
	}
	
}
