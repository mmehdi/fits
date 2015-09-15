package uk.ac.abdn.fits.query.operator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;


public abstract class Operator {
	
	protected String name;
	protected String description;
	protected String elig;
	protected String preferences;
	protected String tel;
	protected String website;
	protected String address;
	protected String email;
	protected String contactUs;
	protected String layer;
	
	
	public Operator(String name, String description, String elig, String preferences, String tel, String website, String address, 
			String email, String contactUs, String layer){
		this.name = name;
		this.description = description;
		this.elig = elig;
		this.preferences = preferences;
		this.tel = tel;
		this.website = website;
		this.address = address;
		this.email = email;
		this.contactUs = contactUs;
		this.layer = layer;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public  String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getPreferences(){
		return preferences;
	}

	public void setPreferences(String preferences){
		this.preferences = preferences;
	}
	
	public String getLayer(){
		return layer;
	}
	public void setLayer(String layer){
	}
	
	public String getWebsite(){
		return website;
	}
	public String getTel(){
		return tel;
	}
	
	public String getOperatorId(){
		return name.replaceAll(" ", "_");
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getEmail(){
		return email;
	}
	public String getContactUs(){
		return contactUs;
	}
	
	public String getEligInfo(){
		
		return elig;
	}
	
	public abstract boolean isCovered(Point point);
	
	public abstract boolean checkOperatingTime(Calendar c);
	
	public abstract boolean checkEligibility(PassengerAge age, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose purpose);
	
	public abstract String getDepartureTime();
	
	public abstract String getArrivalTime();
	
	public abstract String getFare();
	
	public abstract String getOperatingTime();
	
	public abstract String getOpeningTime();
	
	public abstract String getClosingTime();
	
	public abstract boolean relaxOpeningTime(Calendar c, int hour);
	
	public abstract boolean relaxClosingTime(Calendar c, int hour);
	
//	public abstract double relaxPassengerType(PassengerType passenger_type);
	
	public abstract double relaxPassengerType(PassengerAge age, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose purpose);
	
	public abstract String relaxiPassengerTypeInfo(PassengerAge age, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose purpose);
	
	public abstract boolean relaxOperatingArea(Point point, Direction direction, int miles);
	
	public abstract double getShortestDistanceToBoundary(Point point);
	
	public static Point[] getPoints(String points_str){
		
		Point[] points = null;
		Point point = null;
		ArrayList<Point> list = new ArrayList<Point> ();
		String[] latLng_pairs = points_str.split(",0\\.0");
		StringTokenizer st2 = null;
		String val1 = null;
		String val2= null;
		String token = null;
		LatLng latLng = null;
		OSRef osRef = null;
		for(int i=0; i< latLng_pairs.length; i++){
			token = latLng_pairs[i];
			st2 = new StringTokenizer(token, ",");
			val1 = st2.nextToken().trim();
			val2 = st2.nextToken().trim();
			latLng = new LatLng(new Double(val2), new Double(val1));// -3.2958984375,57.55120803456644  lng, lat
			osRef = latLng.toOSRef();
//			System.out.println(latLng.toString() + " " + osRef.getEasting() +"  " +osRef.getNorthing());
			point = new Point(osRef.getEasting(), osRef.getNorthing()); 
			list.add(point);
		}
		list.remove(list.size()-1);
		points = new Point[list.size()];
		points = list.toArray(points);
		return points;
	}
	
	
	
}
