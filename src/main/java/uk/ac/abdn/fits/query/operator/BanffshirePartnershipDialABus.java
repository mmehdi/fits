package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class BanffshirePartnershipDialABus extends Operator{
	
	
	public static final String POLYGON_STR= "-2.7685546875,57.69240553526457,0.0 -2.775421142578125,57.629963916724314,0.0 -2.7472686767578125,57.602014062545535,0.0 -2.6992034912109375,57.57514724603766,0.0 -2.65045166015625,57.54826058225397,0.0 -2.618865966796875,57.544207445951805,0.0 -2.5872802734375,57.56492896950416,0.0 -2.52685546875,57.57873677041636,0.0 -2.4616241455078125,57.58278606520832,0.0 -2.39501953125,57.59456326992429,0.0 -2.300262451171875,57.59612714310655,0.0 -2.2913360595703125,57.694974357167204,0.0 -2.7685546875,57.69240553526457,0.0"; 

	private Polygon ploygon;
//	private PassengerType[] eligiblePassengers; // Unable to use regular PT
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	
	public static final String operatingTime = "0900-1600; Mon to Fri"; 
	
	private String fare = "Equivalent to standard bus fares. Concession passes valid"; //"Equivalent to standard bus fares. Concession passes valid";
	
	public BanffshirePartnershipDialABus(String name, String description, String elig, 
			String preferences, String tel, String website, String address, String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	
	public BanffshirePartnershipDialABus() {
		super("Banffshire Partnership Dial a Bus"
				, "Wheelchair accessible door to door service"
				, "Unable to use regular PT"
				, "Social and shopping"
				, ""
				, "http://www.banffshirepartners.co.uk/comtrans.htm"
				, "The Old School, Boyndie, Banff. AB45 2JT"
				, "bpl.transport@banffdab.org.uk"
				, "The Old School, Boyndie, Banff. AB45 2JT\nTel: 01261 843598 FFFax: 01261 843598\nE-mail: bpl.transport@banffdab.org.uk"
				, "North Aberdeenshire on-demand bus services");
		
		ploygon = new Polygon(Operator.getPoints(POLYGON_STR));
		eligibility();
	}
	
	private void eligibility(){
		
		age_group.put(PassengerAge.under_16, 1.0);
		age_group.put(PassengerAge.from_16_to_21, 1.0);
		age_group.put(PassengerAge.from_22_to_54, 1.0);
		age_group.put(PassengerAge.from_55_to_59, 1.0);
		age_group.put(PassengerAge.over_60, 1.0);
		mobility_status.put(PassengerMobilityStatus.Able_bodied, 1.2);
//		mobility_status.put(PassengerMobilityStatus.Disabled_wheelchair_user, 1.2); 
		mobility_status.put(PassengerMobilityStatus.Disabled_wheelchair_user, 1.0);  // change from 1.2 to 1.0; discussed with David.
		mobility_status.put(PassengerMobilityStatus.Disable_other, 1.2);
		mobility_status.put(PassengerMobilityStatus.Mobility_impaired_unable_to_use_regular_PT, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Health_appointment, 1.4);
		journey_purpose.put(PassengerJourneyPurpose.Shopping, 1.0);
		journey_purpose.put(PassengerJourneyPurpose.Social_care, 1.0);
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
			
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 && c.get(Calendar.HOUR_OF_DAY)< 16){
				
				return true;
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
		// TODO Auto-generated method stub
		return fare;
	}
	
	@Override
	public String getOperatingTime() {
		
		return operatingTime;
	}

	@Override
	public boolean relaxOpeningTime(Calendar c, int hour) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			if(c.get(Calendar.HOUR_OF_DAY)>= (9 - hour) && c.get(Calendar.HOUR_OF_DAY)< 16){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 && c.get(Calendar.HOUR_OF_DAY)< (16 + hour)){
				return true;
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
		return "16:00";
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
