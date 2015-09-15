package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;

import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class TaxiOperator extends Operator{
	
	
	public TaxiOperator(String name, String description, String elig, String preferences, String tel, String website, String address, 
			String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	
	
	public TaxiOperator(){
		
		super("Taxi service"
				, "taxi service" //; fewer constraints
				, ""
				, "social, shopping and all kinds of need"
				, ""
				, ""
				, ""
				, ""
				, ""
				, "taxi service");
		
	}

	@Override
	public boolean isCovered(Point point) {
		
		return true;
	}

	@Override
	public boolean checkOperatingTime(Calendar c) {
		
		return true; // flexible
	}

	

	@Override
	public String getDepartureTime() {
		
		return "Various hours";
	}

	@Override
	public String getArrivalTime() {
		
		return "Various hours";
	}

	@Override
	public String getFare() {
		
		return "£0.40 flag drop + £0.80 per mile";
	}


	@Override
	public String getOperatingTime() {
		// TODO Auto-generated method stub
		return "Flexible";
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
		return true;
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
		return "n/a";
	}

}
