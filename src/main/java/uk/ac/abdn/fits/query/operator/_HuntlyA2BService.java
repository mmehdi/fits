package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;

import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class _HuntlyA2BService extends Operator{
	
	public static final String POLYGON_STR= ""; 

	private Polygon ploygon;
	
	PassengerType[] eligiblePassengers; 
	
	public static final String operatingTime = ""; // NaN 
	
	public _HuntlyA2BService(String name, String description, String elig, 
			String preferences, String tel, String website, String address, String email, String contactUs, String layer) {
		super(name, description, tel, elig, preferences, website, address, email, contactUs, layer);
		// TODO Auto-generated constructor stub
	}

	
	public _HuntlyA2BService() {
		super("Huntly A2B service"
				, ""
				, ""
				, ""
				, ""
				, ""
				, ""
				, ""
				, ""
				, "North Aberdeenshire on-demand bus services");
		
		ploygon = new Polygon(Operator.getPoints(POLYGON_STR));
	}
	
	@Override
	public boolean isCovered(Point point) {
		
		return ploygon.contains(point);
	}


	@Override
	public boolean checkOperatingTime(Calendar c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getDepartureTime() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getArrivalTime() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getFare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperatingTime() {
		
		return operatingTime;
	}


	@Override
	public boolean relaxOpeningTime(Calendar c, int hour) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		// TODO Auto-generated method stub
		return false;
	}

	


	@Override
	public boolean checkEligibility(PassengerAge age,
			PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double relaxPassengerType(PassengerAge age,
			PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean relaxOperatingArea(Point point, Direction direction,
			int miles) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getOpeningTime() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getClosingTime() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double getShortestDistanceToBoundary(Point point) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String relaxiPassengerTypeInfo(PassengerAge age,
			PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose) {
		// TODO Auto-generated method stub
		return null;
	}
}
