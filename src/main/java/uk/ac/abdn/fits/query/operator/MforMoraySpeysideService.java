package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class MforMoraySpeysideService extends Operator{

	
	public static final String POLYGON_STR= "-3.2958984375,57.55120803456644,0.0 -3.3274840000000268,57.54829,0.0 -3.363189697265625,57.54383895665799,0.0 -3.41949462890625,57.51656040692372,0.0 -3.475799560546875,57.46415790200031,0.0 -3.530731201171875,57.39170408045634,0.0 -3.55133056640625,57.353941758590516,0.0 -3.563690185546875,57.31984826984671,0.0 -3.55682373046875,57.285723127223136,0.0 -3.548583984375,57.25750890469551,0.0 -3.5321044921875,57.227786363157286,0.0 -3.486785888671875,57.20027167166257,0.0 -3.423614501953125,57.19134358195087,0.0 -3.306884765625,57.18762290751651,0.0 -3.236846923828125,57.19506388163701,0.0 -3.234100341796875,57.2151470252333,0.0 -3.225296999999955,57.235948,0.0 -3.20526123046875,57.25973712933438,0.0 -3.151702880859375,57.279042764977774,0.0 -3.1036376953125,57.282011964547515,0.0 -3.05145263671875,57.28646531485516,0.0 -3.01025390625,57.30946570855315,0.0 -2.997894287109375,57.33022789911719,0.0 -3.00750732421875,57.39540417660596,0.0 -3.017120361328125,57.441993391526616,0.0 -3.02947998046875,57.4671121550888,0.0 -3.043212890625,57.49221366670073,0.0 -3.065185546875,57.514347738032804,0.0 -3.083038330078125,57.526884420651236,0.0 -3.110504150390625,57.534256927023264,0.0 -3.179168701171875,57.54310196689153,0.0 -3.247833251953125,57.54973433821772,0.0 -3.2958984375,57.55120803456644,0.0"; 

	private Polygon ploygon;
//	private PassengerType[] eligiblePassengers; // 	No restrictions
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	
	public static final String operatingTime = "0900-1430 and 1600-1800; Mon to Fri"; 
	
	private String fare = "Standard bus fares. Concession passes valid.";
	
	public MforMoraySpeysideService(String name, String description, String elig, 
			String preferences, String tel, String website, String address, String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
		// TODO Auto-generated constructor stub
	}
	
	public MforMoraySpeysideService() {
		super("M for Moray - Speyside service"
				, "Speyside area accessible on-demand bus. Operating Time: 0900-1430 and 1600-1800; Mon to Fri. Eligible passengers: No restrictions. Fares: Standard bus fares. Concession passes valid."
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
					if(c.get(Calendar.MINUTE) <= 30){
						return true;
					}
				}
				
				if(c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY)< 18){
					
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
			
			if(c.get(Calendar.HOUR_OF_DAY)>= (9 - hour) ){
				
				if(c.get(Calendar.HOUR_OF_DAY)< 14){
					return true;
				}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
					if(c.get(Calendar.MINUTE) <= 30){
						return true;
					}
				}
				
				if(c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY)< 18){
					
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 ){
				
				if(c.get(Calendar.HOUR_OF_DAY)< 14){
					return true;
				}else if(c.get(Calendar.HOUR_OF_DAY) == 14){
					if(c.get(Calendar.MINUTE) <= 30){
						return true;
					}
				}
				
				if(c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY)< (18 + hour)){
					
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
		return "9:00 && 16:00";
	}

	@Override
	public String getClosingTime() {
		return "14:30 && 18:00";
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
