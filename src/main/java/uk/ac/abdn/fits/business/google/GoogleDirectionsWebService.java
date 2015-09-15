package uk.ac.abdn.fits.business.google;

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
import java.util.Calendar;

import uk.ac.abdn.fits.gmap.model.DirectionsResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GoogleDirectionsWebService {
	
	public static final String TRANSIT_MODE = "transit"; 
	public static final String DRIVING_MODE = "driving"; 
	
	private static GoogleDirectionsWebService instance = null;
	private static ObjectMapper mapper = new ObjectMapper();
	private static int countNum = 0;
	
	private GoogleDirectionsWebService(){
		
	}

	public static GoogleDirectionsWebService getInstance(){
		if(instance != null){
			return instance;
		}
		instance = new GoogleDirectionsWebService();
		return instance;
	}
	
	public DirectionsResponse query(String origin, String destn, String time_mode, Calendar time, String mode){
		
		try{
			System.out.println("google transit drive mode: calendar time - " + time.getTime().toGMTString());
			String url = getURL(origin, destn, time_mode, time.getTimeInMillis(), mode);
			fetchJsonFile(url);
		}catch(NullPointerException  e){
			System.out.println(e.toString());
		}
		
		return getDataBinding();
	}
	
	private DirectionsResponse getDataBinding(){
		
		DirectionsResponse response = null;
		try {
			String dir = this.getClass().getClassLoader().getResource("").getPath();
			System.out.println("binding: "+dir+"transit"+countNum+".json");
			response = mapper.readValue(new File(dir+"transit"+countNum+".json"), 
					DirectionsResponse.class);
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
	
	private void fetchJsonFile(String url){
		
//		System.setProperty("http.proxySet", "true");
//		System.setProperty("http.proxyHost","proxy.abdn.ac.uk");
//		System.setProperty("http.proxyPort", "8080");
		URL website = null;
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			website = new URL(url);
			rbc = Channels.newChannel(website.openStream());
			String dir = this.getClass().getClassLoader().getResource("").getPath();
			fos = new FileOutputStream(new File(dir+File.separator+"transit"+countNum+".json"));
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.flush();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("json file is done.");
	}

	
	private String getURL(String origin, String destn, String time_mode, long time, String mode){
		
		String url = "http://maps.googleapis.com/maps/api/directions/json?origin=";
		url += origin.replaceAll(" ", "+");
		url += "&destination=";
		url += destn.replaceAll(" ", "+");
		url += "&sensor=false";
		if(time_mode.equals("arrival_time")){
			url += "&arrival_time=";
			url += (time/1000);
		}else if(time_mode.equals("departure_time")){
			url += "&departure_time=";
			url += (time/1000);
		}else{// by default, using departure time
			url += "&departure_time=";
			url += (time/1000);
		}
		url += "&mode=";
		url += mode;
		System.out.println("google transit url: "+url);
		return url;
	}
	
	
	
	
}
