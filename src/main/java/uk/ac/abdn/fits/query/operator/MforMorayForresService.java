package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class MforMorayForresService extends Operator{
	
	
	public static final String POLYGON_STR= "-3.77105712890625,57.62922866964605,0.0 -3.768310546875,57.60937137166543,0.0 -3.7628173828125,57.58655886615978,0.0 -3.7580108642578125,57.561890550364836,0.0 -3.7470245361328125,57.54457593151928,0.0 -3.7298583984375,57.52172277909669,0.0 -3.7126922607421875,57.50217565997505,0.0 -3.69964599609375,57.48261806656337,0.0 -3.6907196044921875,57.47043540439562,0.0 -3.6838531494140625,57.467481419935325,0.0 -3.6591339111328125,57.467481419935325,0.0 -3.6385345458984375,57.467481419935325,0.0 -3.5918426513671875,57.48999952121883,0.0 -3.4991455078125,57.526884420651236,0.0 -3.45245361328125,57.55305007117087,0.0 -3.437347412109375,57.571465314788526,0.0 -3.429107666015625,57.59612714310652,0.0 -3.43048095703125,57.62150767664831,0.0 -3.443134999999984,57.644549,0.0 -3.462066650390625,57.66266734601164,0.0 -3.47991943359375,57.67515301354949,0.0 -3.49639892578125,57.681761332630515,0.0 -3.51287841796875,57.66854348975104,0.0 -3.5437774658203125,57.662300055417106,0.0 -3.5781097412109375,57.66119816131916,0.0 -3.6069488525390625,57.66266734601164,0.0 -3.6344146728515625,57.66303463288711,0.0 -3.66943359375,57.66083085584828,0.0 -3.6989593505859375,57.65789227819028,0.0 -3.7209320068359375,57.65385134523876,0.0 -3.7470245361328125,57.647237938677804,0.0 -3.7628173828125,57.637683112449935,0.0 -3.77105712890625,57.62922866964605,0.0"; 

	private Polygon ploygon;
	
//	private PassengerType[] eligiblePassengers; //No restrictions
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal;
	
	public static final String operatingTime = "0800-1700(no service between 1000 - 1030); Mon to Fri"; // There is currently no service available between 1000 and 1030
	
	private String fare = "Standard bus fares";
	
	public MforMorayForresService(String name, String description, String elig, String preferences, String tel, String website, String address, 
			String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	

	public MforMorayForresService() {
		super("M for Moray - Forres service"
				, "Forres area accessible on-demand bus. Operating Time: 0800-1700; Mon to Fri. Eligible passengers: No restrictions. Fares: Standard bus fares. Concession passes valid."
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
			
			if(c.get(Calendar.HOUR_OF_DAY)>= 8 && c.get(Calendar.HOUR_OF_DAY)< 17){
				
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
		return fare;
	}
	
	@Override
	public String getOperatingTime() {
		
		return operatingTime;
	}

	@Override
	public boolean relaxOpeningTime(Calendar c, int hour) {
		
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)>= (8 - hour) && c.get(Calendar.HOUR_OF_DAY)< 17){
				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			
			if(c.get(Calendar.HOUR_OF_DAY)>= 8 && c.get(Calendar.HOUR_OF_DAY)< (17 + hour)){
				
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
		return "8:00";
	}


	@Override
	public String getClosingTime() {
		return "17:00";
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
