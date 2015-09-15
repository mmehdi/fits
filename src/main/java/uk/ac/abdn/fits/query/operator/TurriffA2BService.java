package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class TurriffA2BService extends Operator{
	
	public static final String POLYGON_STR= "-2.5052261352539062,57.58039335456832,0.0 -2.52685546875,57.57873677041636,0.0 -2.5632476806640625,57.57137326173521,0.0 -2.5872802734375,57.56492896950416,0.0 -2.599639892578125,57.533427594480905,0.0 -2.6092529296875,57.5039279516761,0.0 -2.6183509826660156,57.48017263074733,0.0 -2.6307106018066406,57.46681212465378,0.0 -2.629852294921875,57.4566788816221,0.0 -2.6181793212890625,57.448551791887134,0.0 -2.5522613525390625,57.44929069286476,0.0 -2.4506378173828125,57.45594012991794,0.0 -2.3943328857421875,57.47145078140801,0.0 -2.3404312133789062,57.48990726224335,0.0 -2.302837371826172,57.5061412525211,0.0 -2.2748565673828125,57.527529574477036,0.0 -2.2693634033203125,57.56971626696864,0.0 -2.2748565673828125,57.59548320346999,0.0 -2.39501953125,57.594563269924265,0.0 -2.4616241455078125,57.582786065208296,0.0 -2.5052261352539062,57.58039335456832,0.0"; 
	public static final String operatingTime = "0930-1450 Mon - Fri"; 
	
	private Polygon ploygon;
//	private PassengerType[] eligiblePassengers; // unable to use regular PT service
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	private String fare = "Equivalent to standard bus fares. Concession passes valid";
	
	public TurriffA2BService(String name, String description, String elig, 
			String preferences, String tel, String website, String address, String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	
	public TurriffA2BService() {
		super("Turriff A2B service"
				, "Door to door service for those unable to use conventional PT."
				, "unable to use regular PT service"
				, "Over 60 or disabled" // preference
				, "01224 665599"
				, "https://www.aberdeenshire.gov.uk/publictransport/a2bdialabus/turriff.asp"
				, ""
				, "You can email your enquiry to a2bdialabus@aberdeenshire.gov.uk"
				, "If you require any further information please contact A2B dial-a-bus on 01224 664747 between 9.00am and 5.00pm, Monday to Friday. Please note this phone line cannot be used to book journeys. Alternatively, you can email your enquiry to a2bdialabus@aberdeenshire.gov.uk"
				, "North Aberdeenshire on-demand bus services");
		
		ploygon = new Polygon(Operator.getPoints(POLYGON_STR));
		eligibility();
	}
	
	private void eligibility(){
		
		age_group.put(PassengerAge.under_16, 1.2);
		age_group.put(PassengerAge.from_16_to_21, 1.2);
		age_group.put(PassengerAge.from_22_to_54, 1.2);
		age_group.put(PassengerAge.from_55_to_59, 1.2);
		age_group.put(PassengerAge.over_60, 1.0);
		mobility_status.put(PassengerMobilityStatus.Able_bodied, 1.2);
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
			
			if(c.get(Calendar.HOUR_OF_DAY)> 9 && c.get(Calendar.HOUR_OF_DAY)< 14){
				return true;
			}else if (c.get(Calendar.HOUR_OF_DAY) == 9){
				if(c.get(Calendar.MINUTE) > 30){
					return true;
				}
			}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
				if(c.get(Calendar.MINUTE) < 50){
					return true;
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
			
			if(c.get(Calendar.HOUR_OF_DAY)> (9 - hour) && c.get(Calendar.HOUR_OF_DAY)< 14){
				return true;
			}else if (c.get(Calendar.HOUR_OF_DAY) == 9 - hour){
				if(c.get(Calendar.MINUTE) > 30){
					return true;
				}
			}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
				if(c.get(Calendar.MINUTE) < 50){
					return true;
				}
			}
		}
		
		return false;
		
		
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)> 9 && c.get(Calendar.HOUR_OF_DAY)< 14 + hour){
				return true;
			}else if (c.get(Calendar.HOUR_OF_DAY) == 9){
				if(c.get(Calendar.MINUTE) > 30){
					return true;
				}
			}else if(c.get(Calendar.HOUR_OF_DAY) == 14 + hour){
				if(c.get(Calendar.MINUTE) < 50){
					return true;
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
		return "9:30";
	}

	@Override
	public String getClosingTime() {
		// TODO Auto-generated method stub
		return "14:50";
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
