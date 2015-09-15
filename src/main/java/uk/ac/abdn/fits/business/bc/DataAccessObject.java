package uk.ac.abdn.fits.business.bc;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.abdn.fits.business.bc.DataAccessObject.Title;
import uk.ac.abdn.fits.business.client.OriginalRequest;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.hibernate.model.FareMileageBands;
import uk.ac.abdn.fits.hibernate.model.FareStructure;
import uk.ac.abdn.fits.hibernate.model.OperatingArea;
import uk.ac.abdn.fits.hibernate.model.OperationalHours;
import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.model.OtherEligTable;
import uk.ac.abdn.fits.hibernate.model.PassengerElig;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;
import uk.ac.abdn.fits.mvc.control.operator.HibUtils.Day;
import uk.ac.abdn.fits.mvc.control.operator.map.json.LatLon;
import uk.ac.abdn.fits.mvc.control.operator.map.json.MapJsonString;
import uk.ac.abdn.fits.query.operator.PassengerAge;
import uk.ac.abdn.fits.query.operator.PassengerJourneyPurpose;
import uk.ac.abdn.fits.query.operator.PassengerMobilityStatus;
import uk.ac.abdn.fits.query.operator.fare.DistanceBand;
import uk.ac.abdn.fits.query.operator.fare.FareCalculator;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

public class DataAccessObject implements BusinessComponent{
	
	
	private static OperatorDataManager operatorDataManager = null;
	
	private static final String TITLE_CATCHMENT_AREA = "Catchment area";
	private static final String TITLE_DESTINATION_OUTWITH_CATCHMENT_AREA = "Destination outwith the catchment area";
	private static final String OTHER_ELIG_ISSUE_NONE = "None";
	
	public enum Title{
		Catchment_area, Destination, Not_covered;
	}
	
	public DataAccessObject(){

		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
	}
	
	public List<TOption> query(QueryCommand query){
		
		List<Operator> operators  = operatorDataManager.getOperators();
		ArrayList<TOption> options = new ArrayList<TOption>();
		for(Operator operator: operators){
			int operatorId = operator.getOperator_id();
			boolean area = check_operating_area(operatorId, query.getDirectionsResponse());
			boolean time = check_operating_time(operatorId, query);
			OriginalRequest request = query.getOriginalRequest();
			boolean elig = check_eligibility(operatorId, request.getAgeGroup(), request.getMobilityStatus(), request.getPurpose(), request.getOtherEligIssue());
			TOption option = null;
			if(area && time && elig){
				option = new TOption();
				option.setDirectionsResponse(query.getDirectionsResponse());
				uk.ac.abdn.fits.query.operator.RegistryOperator registryOperator = new 	uk.ac.abdn.fits.query.operator.RegistryOperator(operator.getName(), operator.getDescription(), 
						"eligibility", "preferences", "telephone", "website", "address", "email", operator.getHow2book(), "layer");
				option.setOperator(registryOperator);
				option.setFare(estimate_fare(operatorId, query));
				option.setSourceType("registry_operator");
				option.setSourceID(operator.getName());
			}
			if(option != null){
				options.add(option);
			}
		}
		return options;
	}
	
	public void getComments(TOption option){
		
	}
	
	public double estimate_fare(int operatorId, QueryCommand query ){
		DirectionsResponse response = query.getDirectionsResponse();
		int meters = new Integer(response.getRoutes()[0].getLegs()[0].getDistance().getValue());
		double fare = 0;
		FareStructure fare_structure = operatorDataManager.getFareStructureByOpId(operatorId);
		// if yes, the value is 0. If no, the value is 1. The default is 0.
		if(fare_structure.getFare_structure_radioBtns().equals("1") || fare_structure.getFare_structure_radioBtns().equals("2")){
			return 0;
		}
		// 0 = flat rate, 1 = zonal fare, 2 = fare per mile
		if(fare_structure.getHow_charge_radioBtns().equals("0") || fare_structure.getHow_charge_radioBtns().equals("2")){
			FareCalculator fare_cal = new FareCalculator(fare_structure.getReturn_fare_multiplier(), fare_structure.getDiscount_for_over60(),
					fare_structure.getDiscount_for_under16(), fare_structure.getDiscount_for_other_concessionary(), fare_structure.isHas_escort(), fare_structure.isCharge_for_dead_mileage());
			int fare_structure_id = fare_structure.getId();
			List<FareMileageBands> fareMileageBands = operatorDataManager.getFareMileageBandsByFareStrctId(fare_structure_id);
			for(FareMileageBands band: fareMileageBands){
				fare_cal.addDistanceBand(new DistanceBand(band.getMile_1(), band.getMile_2(), band.getType(), band.getCharge()));
			}
			fare = fare_cal.calc(meters);
			fare = calculateDiscountedFare(fare, fare_cal, query);
		}else if(fare_structure.getHow_charge_radioBtns().equals("1")){
			return -100;
		}
		return fare;
	}
	
	public double calculateDiscountedFare(double fare, FareCalculator fare_cal, QueryCommand query){
		
		OriginalRequest requestForm =  query.getOriginalRequest();
		if(requestForm.getAgeGroup() == PassengerAge.under_16){
			fare = (1-fare_cal.getDiscount_per_for_under_16s())*fare;
		}else if(requestForm.getAgeGroup() == PassengerAge.over_60){
			fare = (1-fare_cal.getDiscount_per_for_over_60s())*fare;
		}
		if(requestForm.isHasReturn()){
			fare *= fare_cal.getReturn_fare_multiplier();
		}
		return fare;
	}

	public boolean check_eligibility(int operatorId, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose, String other_issue){
		
		PassengerElig psg_elig = operatorDataManager.getPassengerElig(operatorId);
		boolean is_elig_age_grp = true;
		boolean is_elig_mob_stt = true;
		boolean is_elig_jnr_pps = true;
		if(age_group == PassengerAge.under_16){
			if(!psg_elig.isAge_under16()){
				is_elig_age_grp = false;
			}
		}else if(age_group == PassengerAge.from_16_to_21){
			if(!psg_elig.isAge_17to21()){
				is_elig_age_grp = false;
			}
		}else if(age_group == PassengerAge.from_22_to_54){
			if(!psg_elig.isAge_22to54()){
				is_elig_age_grp = false;
			}
		}else if(age_group == PassengerAge.from_55_to_59){
			if(!psg_elig.isAge_55to59()){
				is_elig_age_grp = false;
			}
		}else{
			if(!psg_elig.isAge_over60()){
				is_elig_age_grp = false;
			}
		}
		if(mobility_status == PassengerMobilityStatus.Able_bodied){
			if(!psg_elig.isAble_bodied()){
				is_elig_mob_stt = false;
			}
		}else if(mobility_status == PassengerMobilityStatus.Disabled_wheelchair_user){
			if(!psg_elig.isDiable_wheelchair_user()){
				is_elig_mob_stt = false;
			}
		}else if(mobility_status == PassengerMobilityStatus.Mobility_impaired_unable_to_use_regular_PT){
			if(!psg_elig.isMobility_prevents_PT()){
				is_elig_mob_stt = false;
			}
		}else if(mobility_status == PassengerMobilityStatus.Disable_other){
			if(!psg_elig.isDiable_other()){
				is_elig_mob_stt = false;
			}
		}else{
			if(!psg_elig.isCant_live_on_a_bus_route()){
				is_elig_mob_stt = false;
			}
		}
		if(journey_purpose == PassengerJourneyPurpose.Health_appointment){
			if(!psg_elig.isHealth_appointment()){
				is_elig_jnr_pps = false;
			}
		}else if(journey_purpose == PassengerJourneyPurpose.Shopping){
			if(!psg_elig.isShopping()){
				is_elig_jnr_pps = false;
			}
		}else if(journey_purpose == PassengerJourneyPurpose.Social_care){
			if(!psg_elig.isSocial_care()){
				is_elig_jnr_pps = false;
			}
		}else if(journey_purpose == PassengerJourneyPurpose.School_or_education){
			if(!psg_elig.isSchool_or_education()){
				is_elig_jnr_pps = false;
			}
		}else if(journey_purpose == PassengerJourneyPurpose.Work_commuting){
			if(!psg_elig.isWork_or_commuting()){
				is_elig_jnr_pps = false;
			}
		}else{
			if(!psg_elig.isOther_purpose()){
				is_elig_jnr_pps = false;
			}
		}
		boolean other_elig = false;
		List<OtherEligTable> otherEligIssue = operatorDataManager.getOperatorOtherEligByOpID(operatorId);
		if(otherEligIssue != null){
			if(!other_issue.equals(OTHER_ELIG_ISSUE_NONE)){
				if(otherEligIssue != null){
					for(OtherEligTable e: otherEligIssue){
						if(e.getElig().equals(other_issue)){ // e.getElig() != null && 
							other_elig = true;
							break;
						}
					}
				}
			}
		}else{
			other_elig = true;
		}
		return is_elig_age_grp && is_elig_mob_stt && is_elig_jnr_pps && other_elig;
	}
	
	public boolean check_operating_time(int operatorId, QueryCommand query ){
		
		Calendar departure_time = null;
		Calendar arrival_time = null;
		/* the direction is a drive mode so there is no departure time and arrival time in the json or xml result */
		DirectionsResponse response = query.getDirectionsResponse(); 
		int duration = gerDuration(response); //  seconds
		if(query.getOriginalRequest().getDepartureTime() != null){ /* if the request is depart after */
			departure_time = query.getOriginalRequest().getDepartureTime();
		}else{ /* if the request is arrival by */
			arrival_time = query.getOriginalRequest().getArriveTime();
			departure_time = Calendar.getInstance();  
			departure_time.setTimeInMillis(arrival_time.getTimeInMillis());
			/* use the arrival time to get the departure time */
			departure_time.add(Calendar.SECOND, -gerDuration(response));
		}
		Operator operator = operatorDataManager.getOperatorById(operatorId);
		/* check the period when the service is not available */
		if(operator.isInactive()){
			if(operator.getInactiveFrom()!=null && operator.getInactiveTo()!=null){
				if(departure_time.getTimeInMillis()> operator.getInactiveFrom().getTime()
						&& departure_time.getTimeInMillis() < operator.getInactiveTo().getTime()){
					System.out.println("The operator "+ operator.getName()+" is not available between " 
						+ operator.getInactiveFrom().toGMTString()+" and " + operator.getInactiveTo().toGMTString());
					return false;
				}
			}
			/* to do */
		}
		List<OperationalHours> operationalHours = operatorDataManager.getOperationalHoursByOpId(operatorId);
		boolean isCovered = checkOperationalHours(departure_time, duration, operationalHours);
		return isCovered;
	}
	
	
	public boolean checkOperationalHours(Calendar departure_time, int diffSeconds, List<OperationalHours> operationalHours){
		
		String day = null;
		switch (departure_time.get(Calendar.DAY_OF_WEEK)) {
	        case Calendar.MONDAY:  day = Day.Monday.toString();
	            break;
	        case Calendar.TUESDAY:  day = Day.Tuesday.toString();
	        	break;
	        case Calendar.WEDNESDAY:  day = Day.Wednesday.toString();
	        	break;
	        case Calendar.THURSDAY:  day = Day.Thursday.toString();
	        	break;
	        case Calendar.FRIDAY:  day = Day.Friday.toString();
	        	break;
	        case Calendar.SATURDAY:  day = Day.Saturday.toString();
	        	break;
	        case Calendar.SUNDAY:  day = Day.Sunday.toString();
	        	break;
		}
		Time openingTime, closingTime; // java.sql.Time
		boolean isYes = false;
		for(OperationalHours hours: operationalHours){
			if(hours.getDay_of_week().equals(day)){
				openingTime = hours.getOpening_time();
				closingTime = hours.getClosing_time();
				if(openingTime!= null && closingTime!=null){
					isYes = checkSessionTime(departure_time.getTimeInMillis(), diffSeconds, openingTime, closingTime);
					if(isYes){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkSessionTime(long departure_time, int diffSeconds, Time openingTime, Time closingTime){
		Calendar departure_t = Calendar.getInstance();
		departure_t.setTimeInMillis(departure_time);
		Calendar opening_t = Calendar.getInstance();
		opening_t.setTimeInMillis(openingTime.getTime());
		Calendar closing_t = Calendar.getInstance();
		closing_t.setTimeInMillis(closingTime.getTime());
		boolean isYes = liesBetween(departure_t, opening_t, closing_t);
		if(isYes){
			departure_t.add(Calendar.SECOND, diffSeconds);
			isYes = liesBetween(departure_t, opening_t, closing_t);
			if(isYes){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean liesBetween(Calendar target, Calendar t1, Calendar t2){
		if((target.get(Calendar.HOUR_OF_DAY)> t1.get(Calendar.HOUR_OF_DAY) || target.get(Calendar.HOUR_OF_DAY)== t1.get(Calendar.HOUR_OF_DAY) && target.get(Calendar.MINUTE)>=t1.get(Calendar.MINUTE) ) 
				&& (target.get(Calendar.HOUR_OF_DAY) < t2.get(Calendar.HOUR_OF_DAY) || target.get(Calendar.HOUR_OF_DAY) ==  t2.get(Calendar.HOUR_OF_DAY) && target.get(Calendar.MINUTE) <= t2.get(Calendar.MINUTE))){
			return true;
		}
		return false;
	}
	
	
	public int gerDuration(DirectionsResponse response){
		return response.getRoutes()[0].getLegs()[0].getDuration().getValue(BusinessOperaotor.TIME_PENALTY);
	}
	
	
	
	
	public boolean check_operating_area(int operatorId, DirectionsResponse response){
		
		boolean isCovered = false;
		Point origin = getOriginLoc(response);
		Point destn = getDestnLoc(response);
		OperatingArea operatingArea = operatorDataManager.getOperatingAreaByOpId(operatorId);
		String jsonStr = operatingArea.getJson();
		MapJsonString mapJson = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapJson = mapper.readValue(jsonStr, MapJsonString.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(mapJson != null){
			List<Area> map = new ArrayList<Area>();
			String title = null;
			Polygon polygon = null;
			Area area = null;
			for(int i=0; i< mapJson.getOverlays().length; i++){
				title = mapJson.getOverlays()[i].getTitle();
				polygon = getPolygon(mapJson.getOverlays()[i].getPaths()[0]);
				if(title.equals(TITLE_CATCHMENT_AREA)){
					area = new Area(Title.Catchment_area, polygon);
				}else if(title.equals(TITLE_DESTINATION_OUTWITH_CATCHMENT_AREA)){
					area = new Area(Title.Destination, polygon);
				}else{
					continue;
				}
				map.add(area);
			}
			Title oriTitle = getCoveredAreaType(origin,map);
			Title desTitle = getCoveredAreaType(destn,map);
			if(oriTitle == Title.Not_covered || desTitle == Title.Not_covered){
				isCovered = false;
			}else{
				if(desTitle == Title.Destination && oriTitle == Title.Destination){
					isCovered = false;
				}else{
					isCovered = true;
				}
			}
		}
		return isCovered;
	}
	
	
	public Polygon getPolygon(LatLon[] latlons){
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<latlons.length; i++){
			sb.append(latlons[i].getLng());
			sb.append(",");
			sb.append(latlons[i].getLat());
			sb.append(",0.0 ");
		}
		sb.append(latlons[latlons.length-1].getLng());
		sb.append(",");
		sb.append(latlons[latlons.length-1].getLat());
		Point[] points = getPoints(sb.toString());
		return new Polygon(points);
	}
	
	
	public static void main(String[] args){
		DataAccessObject dataAccessObject = new DataAccessObject();
		dataAccessObject.check_operating_area(6, null);
	}
	
	private Title getCoveredAreaType(Point point, List<Area> areas){
		
		for(Area area: areas){
			if(area.getPolygon().contains(point)){
				return area.getTitle();
			}
		}
		return Title.Not_covered;
	}
	

	private Point getOriginLoc(DirectionsResponse response){
		Point origin = null;
		String lat = null, lng = null;
		lat = response.getRoutes()[0].getLegs()[0].getStart_location().getLat();
		lng = response.getRoutes()[0].getLegs()[0].getStart_location().getLng();
		OSRef osRef = null;
		osRef = new LatLng(new Double(lat), new Double(lng)).toOSRef();
		origin = new Point(osRef.getEasting(), osRef.getNorthing()); 
		return origin;
	}
	
	private Point getDestnLoc(DirectionsResponse response){
		Point destn = null;
		String lat = null, lng = null;
		lat = response.getRoutes()[0].getLegs()[0].getEnd_location().getLat();
		lng = response.getRoutes()[0].getLegs()[0].getEnd_location().getLng();
		OSRef osRef = new LatLng(new Double(lat), new Double(lng)).toOSRef();
		destn =new Point(osRef.getEasting(), osRef.getNorthing()); 
		return destn;
	}
	
	
	public Point[] getPoints(String points_str){
		
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
			point = new Point(osRef.getEasting(), osRef.getNorthing()); 
			list.add(point);
		}
		list.remove(list.size()-1);
		points = new Point[list.size()];
		points = list.toArray(points);
		return points;
	}
}

class Area{
	
	private Title title;
	private Polygon polygon;
	
	public Area(Title title, Polygon polygon){
		this.title = title;
		this.polygon = polygon;
	}
	
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Polygon getPolygon() {
		return polygon;
	}
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
}
