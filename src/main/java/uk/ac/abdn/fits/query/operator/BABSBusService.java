package uk.ac.abdn.fits.query.operator;

import java.util.Calendar;
import java.util.HashMap;

import math.geom2d.polygon.SimplePolygon2D;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class BABSBusService extends Operator{
	
	public static final String POLYGON_STR= "-2.8131866455078125,57.70396379956302,0.0 -2.8392791748046875,57.71826892238464,0.0 -3.096771240234375,57.67496943193424,0.0 -3.1149673461914062,57.666707297100274,0.0 -3.15032958984375,57.65724943259753,0.0 -3.2083511352539062,57.65293288861959,0.0 -3.251953125,57.65394318962215,0.0 -3.3003616333007812,57.65770860918319,0.0 -3.318042755126953,57.65743310392916,0.0 -3.3333206176757812,57.656055546276704,0.0 -3.3419036865234375,57.64466573285768,0.0 -3.3295440673828125,57.637683112449935,0.0 -3.3013916015625,57.6308829546413,0.0 -3.2306671142578125,57.624265362540456,0.0 -3.1661224365234375,57.61691106829562,0.0 -3.1166839599609375,57.60661255523787,0.0 -3.0706787109375,57.61029093061209,0.0 -3.0164337158203125,57.624265362540456,0.0 -2.920989990234375,57.64337956159059,0.0 -2.844085693359375,57.65293288861946,0.0 -2.800140380859375,57.668727103902924,0.0 -2.7912139892578125,57.68010936579536,0.0 -2.8131866455078125,57.70396379956302,0.0"; 
	public static final String operatingTime = "0900-1700; Mon to Fri";
	
	private Polygon ploygon;
//	private String elig; // Over 60 and disabled
	private HashMap<PassengerAge, Double> age_group = new HashMap<PassengerAge, Double>();
	private HashMap<PassengerMobilityStatus, Double> mobility_status = new HashMap<PassengerMobilityStatus, Double>();
	private HashMap<PassengerJourneyPurpose, Double> journey_purpose = new HashMap<PassengerJourneyPurpose, Double>();
	private EligibilityCalculator eligibility_cal; 
	private String fare = "free";
	
	public BABSBusService(String name, String description, String elig, String preferences, String tel, String website, String address, 
			String email, String contactUs, String layer) {
		super(name, description, elig, preferences, tel, website, address, email, contactUs, layer);
	}
	
	public BABSBusService() {
		super("BABS bus service"
				, "Buckie accessible community bus scheme. Covers Buckie, Cullen, Fochabers and Keith areas. Provides fully trained passenger assistants."
				, "Over 60 and disabled"
				, "Social and shopping trips"
				, "(01542) 835800"
				, "http://babsbus.co.uk/"
				, "BABS Dial-a-Bus Ltd\n11 March Road East\nBuckie\nAB56 4BY"
				, ""
				, "Office Telephone: (01542) 835800\n\nBABS Dial-a-Bus Ltd\n11 March Road East\nBuckie\nAB56 4BY"
				, "Moray Area on-demand bus services");
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
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 && c.get(Calendar.HOUR_OF_DAY)< 17){
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
			if(c.get(Calendar.HOUR_OF_DAY)>= (9 - hour) && c.get(Calendar.HOUR_OF_DAY)< 17){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean relaxClosingTime(Calendar c, int hour) {
		if(c.get(Calendar.DAY_OF_WEEK)>= Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
			if(c.get(Calendar.HOUR_OF_DAY)>= 9 && c.get(Calendar.HOUR_OF_DAY)< (17 + hour)){
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
		return "17:00";
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
	
	public static void main(String[] args) {
		
		BABSBusService service = new BABSBusService();
		service.getShortestDistanceToBoundary(new Point());
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
	
}
