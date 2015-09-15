package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class BABSCarService extends Operator{
	
	
	public static final String POLYGON_STR= "-3.3432769775390625,57.661932761103536,0.0 -3.35906982421875,57.647237938677804,0.0 -3.234100341796875,57.58140567442557,0.0 -3.140716552734375,57.55231326770647,0.0 -2.9168701171875,57.5220914920081,0.0 -2.8516387939453125,57.53462551320812,0.0 -2.8255462646484375,57.556733864952214,0.0 -2.7980804443359375,57.6034856434917,0.0 -2.7898406982421875,57.68066002977235,0.0 -2.7925872802734375,57.68304614367449,0.0 -2.789154052734375,57.70212940067202,0.0 -2.801513671875,57.7068986445324,0.0 -2.84820556640625,57.7089162124802,0.0 -3.0919647216796875,57.68139423539569,0.0 -3.316497802734375,57.66376919548108,0.0 -3.3432769775390625,57.661932761103536,0.0"; 

	private Polygon ploygon;
	
//	private PassengerType[] eligiblePassengers;  //Hospital appointments but no other  restrictions
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	
	public static final String operatingTime = "Various hours; Mon to Fri"; 

	private String fare = "&#163;7 + 45p per mile"; //" 7 pounds + 45p per mile (dead and live mileage)";
	
	public BABSCarService(String name, String description, String elig, String preferences, String tel, String website, String address,
			String email, String contactUs,
			String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	
	public BABSCarService() {
		super("BABS car service"
				, "Car hire with driver service.  Buckie, Cullen, Fochabers and Keith areas to Elgin and local hospitals. "
				, "Hospital appointments but no other restrictions"
				, "Medical appointments only"
				, "" // booking hotline
				, "" //http://www.dotrural.ac.uk/fits/
				, "#" // address
				, "#" //email
				, "Phone: (01542) 835800" // contact us
				, "On demand car services");
		
		ploygon = new Polygon(Operator.getPoints(POLYGON_STR));
		eligibility();
	}
	
	private void eligibility(){
		
		age_group.put(PassengerAge.under_16, 1.0);
		age_group.put(PassengerAge.from_16_to_21, 1.0);
		age_group.put(PassengerAge.from_22_to_54, 1.0);
		age_group.put(PassengerAge.from_55_to_59, 1.0);
		age_group.put(PassengerAge.over_60, 1.0);
		mobility_status.put(PassengerMobilityStatus.Able_bodied, 1.0);
		mobility_status.put(PassengerMobilityStatus.Disabled_wheelchair_user, 1.0);
		mobility_status.put(PassengerMobilityStatus.Disable_other, 1.0);
		mobility_status.put(PassengerMobilityStatus.Mobility_impaired_unable_to_use_regular_PT, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Health_appointment, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Shopping, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Social_care, 1.4);
		journey_purpose.put(PassengerJourneyPurpose.School_or_education, 1.4);
		journey_purpose.put(PassengerJourneyPurpose.Work_commuting, 1.4);
		journey_purpose.put(PassengerJourneyPurpose.Other, 1.4);
		this.eligibility_cal = new EligibilityCalculator(age_group, mobility_status, journey_purpose);
	}

	@Override
	public boolean isCovered(Point point) {
		
		return ploygon.contains(point);
	}
	
	@Override
	public boolean checkOperatingTime(Calendar c) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
				return true;
			
		}
		
		return false;
	}

	@Override
	public String getDepartureTime() {
		
		return operatingTime;
	}

	@Override
	public String getArrivalTime() {
		
		return operatingTime;
	}

	@Override
	public String getFare() {
		
		return fare;
	}

	@Override
	public String getOperatingTime() {
		
		return operatingTime;
	}

	@Override
	public boolean relaxOpeningTime(Calendar c, int hour) {
		
		return true; // no need to relax the opening time, always return true
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		
		return true; // no need to relax the closing time, always return true
	}

	
	

	@Override
	public boolean checkEligibility(PassengerAge age,
			PassengerMobilityStatus mobility,
			PassengerJourneyPurpose purpose) {
		if(eligibility_cal.get_disutil(age, mobility, purpose) == 1.0){
			return true;
		}
		return false;
	}

	@Override
	public double relaxPassengerType(PassengerAge age,
			PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose) {
		
		return eligibility_cal.get_disutil(age, mobility_status, purpose);
	}

	@Override
	public boolean relaxOperatingArea(Point point, Direction direction,
			int miles) {
		
		OperatingAreaRelaxation relaxation = OperatingAreaRelaxation.getInstance();
		Polygon p = relaxation.relax(this.ploygon, direction, miles);
		if(p.contains(point)){
			return true;
		}
		return false;
	}

	@Override
	public String getOpeningTime() {
		return "6:00";
	}

	@Override
	public String getClosingTime() {
		// TODO Auto-generated method stub
		return "22:00";
	}
	
	@Override
	public String relaxiPassengerTypeInfo(PassengerAge age,
			PassengerMobilityStatus mobility_status,
			PassengerJourneyPurpose purpose) {
		
		StringBuilder sb = new StringBuilder("");
		if(age_group.get(age) > 1.0){
			sb.append("age group is relaxed to include " + age.toString(age));
		}
		if(this.mobility_status.get(mobility_status) > 1.0){
			if(sb.length() > 0){
				sb.append("; ");
			}
			sb.append("passenger mobility status is relaxed to include " + mobility_status.toString(mobility_status));
		}
		if(journey_purpose.get(purpose) > 1.0){
			if(sb.length() > 0){
				sb.append("; ");
			}
			sb.append("journey purpose is relaxed to include " + purpose.toString(purpose));
		}
		return sb.toString();
	}
	
	
	@Override
	public double getShortestDistanceToBoundary(Point point) {
		
		Point[] points = ploygon.getPoints();
		double[] x_coord = new double[points.length];
		double[] y_coord = new double[points.length];
		for(int i=0; i< points.length; i++){
			x_coord[i] = points[i].x();
			y_coord[i] = points[i].y();
		}
		SimplePolygon2D polygon2D = new SimplePolygon2D(x_coord, y_coord);
		return polygon2D.distance(point.x(), point.y());
	}
}
