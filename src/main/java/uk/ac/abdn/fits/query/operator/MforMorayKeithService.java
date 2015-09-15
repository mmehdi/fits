package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class MforMorayKeithService extends Operator{
	
	
	public static final String POLYGON_STR= "-3.121490478515625,57.56888774130089,0.0 -3.099517822265625,57.52393500065137,0.0 -3.055572509765625,57.48852334963972,0.0 -3.01025390625,57.46268068590949,0.0 -2.89215087890625,57.49442767792295,0.0 -2.8997039794921875,57.5375740685218,0.0 -2.8159332275390625,57.5401538587701,0.0 -2.783660888671875,57.51139730407086,0.0 -2.74658203125,57.52246020119193,0.0 -2.6861572265625,57.51102848304863,0.0 -2.63397216796875,57.53315114610741,0.0 -2.7115631103515625,57.57109710117865,0.0 -2.74932861328125,57.58066944459514,0.0 -2.8179931640625,57.645400667406605,0.0 -3.08441162109375,57.61562391374733,0.0 -3.121490478515625,57.56888774130089,0.0"; 

	private Polygon ploygon;
	
//	private PassengerType[] eligiblePassengers; //No restrictions
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	
	public static final String operatingTime = "0900-1415; Mon to Fri"; 
	
	private String fare = "Standard bus fares. Concession passes valid.";

	public MforMorayKeithService(String name, String description, String elig, 
			String preferences, String tel, String website, String address, String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
		// TODO Auto-generated constructor stub
	}
	
	public MforMorayKeithService() {
		super("M for Moray - Keith service"
				, "Keith area accessible on-demand bus.  Operating Time: 0900-1430; Mon to Fri. Eligible passengers: No restrictions. Fares: Standard bus fares. Concession passes valid."
				, "No restrictions"
				, ""
				, "0300 123 4565"
				, "http://www.moray.gov.uk/moray_standard/page_52932.html"
				, ""
				, "transport@moray.gov.uk"
				, "Dial M for Moray\n\nTel: 01343 562533\nEmail: transport@moray.gov.uk"
				, "Moray Area on-demand bus services");
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
		journey_purpose.put(PassengerJourneyPurpose.Social_care, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.School_or_education, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Work_commuting, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Other, 1.0);
		this.eligibility_cal = new EligibilityCalculator(age_group, mobility_status, journey_purpose);
	}
	
	@Override
	public boolean isCovered(Point point) {
		
		return ploygon.contains(point);
	}
	
	@Override
	public boolean checkOperatingTime(Calendar c) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 ){
				
				if(c.get(Calendar.HOUR_OF_DAY)< 14){
					return true;
				}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
					if(c.get(Calendar.MINUTE) <= 15){
						return true;
					}
				}
					
			}
			
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
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)>= (9 - hour) ){
				
				if(c.get(Calendar.HOUR_OF_DAY)< 14){
					return true;
				}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
					if(c.get(Calendar.MINUTE) <= 15){
						return true;
					}
				}
			}
			
		}
		
		return false;
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 ){
				if(c.get(Calendar.HOUR_OF_DAY)< (14 + hour)){
					return true;
				}else if(c.get(Calendar.HOUR_OF_DAY) == (14 + hour)){
					if(c.get(Calendar.MINUTE) <= 15){
						return true;
					}
				}
			}
		}
		return false;
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
		return "9:00";
	}

	@Override
	public String getClosingTime() {
		return "14:15";
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
