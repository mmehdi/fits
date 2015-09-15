package uk.ac.abdn.fits.business.bc;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;
import uk.ac.abdn.fits.business.client.OriginalRequest;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.query.operator.BABSCarService;
import uk.ac.abdn.fits.query.operator.Operator;
import uk.ac.abdn.fits.query.operator.PassengerAge;
import uk.ac.abdn.fits.query.operator.PassengerJourneyPurpose;
import uk.ac.abdn.fits.query.operator.PassengerMobilityStatus;
import uk.ac.abdn.fits.query.operator.area.OperatingAreaRelaxation.Direction;


public class BusinessOperaotor implements BusinessComponent{
	
	private Operator operator;
	
	public static final double TIME_PENALTY = 1.5;
	
	public BusinessOperaotor(Operator operator){
		this.operator = operator;
	}
	
	public List<TOption> query(QueryCommand query){
		
		ArrayList<TOption> options = new ArrayList<TOption>();
		OriginalRequest request = query.getOriginalRequest();
		TOption option = process(operator, query.getDirectionsResponse(), request, 
				request.getAgeGroup(), request.getMobilityStatus(), request.getPurpose());
		if(option != null){
			options.add(option);
		}
		return options;
	}
	
	private TOption process(Operator operator, DirectionsResponse response, OriginalRequest request,
			PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
		
		TOption option = null;
		String relaxing_area = null;
		String relaxing_elig = null;
		if(check_operating_area(operator, response)){
			if( check_operating_time(operator, request, response)){
				if(checkEligibility(operator, age_group, mobility_status, journey_purpose)){
					option = new TOption();
					option.setDirectionsResponse(response);
					option.setOperator(operator);
					if(operator.getDescription().equals("taxi service")){
						option.setSourceType("taxi");
						option.setSourceID(operator.getName());
					}else{
						option.setSourceType("operators");
						option.setSourceID(operator.getName());
					}
					option.setFare(calFare(operator, response));
					return option;
				}else{ // at this point, relax the eligibility 
					relaxing_elig = relax_eligibility(operator, age_group, mobility_status, journey_purpose);
					if(relaxing_elig.startsWith("n/a")){
						return null;
					}
					option = new TOption();
					option.setDirectionsResponse(response);
					option.setOperator(operator);
					option.setSourceType("operators");
					option.setSourceID(operator.getName());
					option.setRelaxingInfo(relaxing_elig);
					option.setIsRelaxed(true);
					option.setFare(calFare(operator, response));
					return option;
				}
			}
		}else{ // relaxing the operating area
			relaxing_area = relaxed_operating_area(operator, response);
			if(!relaxing_area.startsWith("n/a")){
				if(check_operating_time(operator, request, response)){
					if(checkEligibility(operator, age_group, mobility_status, journey_purpose)){ // still need to check eligibility
						option = new TOption();
						option.setDirectionsResponse(response);
						option.setOperator(operator);
						option.setSourceType("operators");
						option.setSourceID(operator.getName());
						option.setRelaxingInfo(relaxing_area);
						option.setIsRelaxed(true);
						option.setFare(calFare(operator, response));
						return option;
					}else{  // relaxing eligibility
						relaxing_elig = relax_eligibility(operator, age_group, mobility_status, journey_purpose);
						if(relaxing_elig.startsWith("n/a")){
							return null;
						}
						option = new TOption();
						option.setDirectionsResponse(response);
						option.setOperator(operator);
						option.setSourceType("operators");
						option.setSourceID(operator.getName());
						option.setRelaxingInfo(relaxing_area);
						option.addRelaxingInfo(relaxing_elig);
						option.setIsRelaxed(true);
						option.setFare(calFare(operator, response));
						return option;
					}
				}
			}
		}
		return null;
	}

	private boolean check_operating_time(Operator operator, OriginalRequest request, DirectionsResponse response){
		Calendar departure_time = null;
		Calendar arrival_time = null;
		if(request.getDepartureTime() != null){
			departure_time = request.getDepartureTime();
			arrival_time = Calendar.getInstance();
			arrival_time.setTimeInMillis(departure_time.getTimeInMillis());
			arrival_time.add(Calendar.SECOND, new Integer(response.getRoutes()[0].getLegs()[0].getDuration().getValue(TIME_PENALTY)));
		}else{
			arrival_time = request.getArriveTime();
			departure_time = Calendar.getInstance();  
			departure_time.setTimeInMillis(arrival_time.getTimeInMillis());
			departure_time.add(Calendar.SECOND, -new Integer(response.getRoutes()[0].getLegs()[0].getDuration().getValue(TIME_PENALTY)));
		}
		return operator.checkOperatingTime(departure_time) && operator.checkOperatingTime(arrival_time);
	}
	
	private boolean check_operating_area(Operator operator, DirectionsResponse response){
		boolean isCovered = false;
		Point origin = getOriginLoc(response);
		Point destn = getDestnLoc(response);
		isCovered = operator.isCovered(origin) && operator.isCovered(destn);
		return isCovered;
	}
	
	private String relaxed_operating_area(Operator operator, DirectionsResponse response){
		final int miles = 5;
		Point origin = getOriginLoc(response);
		Point destn = getDestnLoc(response);
		double dist1 =0;
		double dist2 = 0;
		if(operator.isCovered(origin)){ // the origin is covered but the destn is not covered
			if(operator.relaxOperatingArea(destn, Direction.North, miles)){
				return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + "miles";
			}else if(operator.relaxOperatingArea(destn, Direction.South, miles)){
				return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + "miles";
			}else if(operator.relaxOperatingArea(destn, Direction.West, miles)){
				return "operating area is relaxed towards West by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + "miles";
			}else if(operator.relaxOperatingArea(destn, Direction.East, miles)){
				return "operating area is relaxed towards East by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + "miles";
			}else{
				return "n/a;od 0 - " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + "miles";
			}
		}else if(operator.isCovered(destn)){ // origin is not covered but the destn is covered
			if(operator.relaxOperatingArea(origin, Direction.North, miles)){
				return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) + "miles";
			}else if(operator.relaxOperatingArea(origin, Direction.South, miles)){
				return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) + "miles";
			}else if(operator.relaxOperatingArea(origin, Direction.West, miles)){
				return "operating area is relaxed towards West by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) + "miles";
			}else if(operator.relaxOperatingArea(origin, Direction.East, miles)){
				return "operating area is relaxed towards East by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) + "miles";
			}else{
				return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) + " - 0 miles";
			}
		}else{ // if both origin and destn are not covered
			boolean north_o = operator.relaxOperatingArea(origin, Direction.North, miles);
			boolean south_o = operator.relaxOperatingArea(origin, Direction.South, miles);
			boolean west_o = operator.relaxOperatingArea(origin, Direction.West, miles);
			boolean east_o = operator.relaxOperatingArea(origin, Direction.East, miles);
			boolean north_d = operator.relaxOperatingArea(destn, Direction.North, miles);
			boolean south_d = operator.relaxOperatingArea(destn, Direction.South, miles);
			boolean west_d = operator.relaxOperatingArea(destn, Direction.West, miles);
			boolean east_d = operator.relaxOperatingArea(destn, Direction.East, miles);
			if(north_o){
				if(north_d){
					dist1 = operator.getShortestDistanceToBoundary(origin);
					dist2 = operator.getShortestDistanceToBoundary(destn);
					if(dist1 >= dist2){
						return "operating area is relaxed towards North by " + convertMiles(dist1) + " miles";
					}else{
						return "operating area is relaxed towards North by " + convertMiles(dist2) + " miles";
					}
				}else if(east_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles"; 
				}else if(west_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(origin))
							+ "miles & West by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles";
				}else if(south_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(origin))
							+ "miles & South by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles";
				}else{
//					return "n/a;d " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
					return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
				}
			}else if(east_o){
				if(east_d){
					dist1 = operator.getShortestDistanceToBoundary(origin);
					dist2 = operator.getShortestDistanceToBoundary(destn);
					if(dist1 >= dist2){
						return "operating area is relaxed towards East by " + convertMiles(dist1) + " miles";
					}else{
						return "operating area is relaxed towards East by " + convertMiles(dist2) + " miles";
					}
				}else if(north_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) 
							+ " miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) +" miles"; 
				}else if(west_d){
					return "operating area is relaxed towards West by " + convertMiles(operator.getShortestDistanceToBoundary(origin))
							+ "miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles";
				}else if(south_d){
					return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(destn))
							+ "miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) +" miles";
				}else{
//					return "n/a;d " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
					return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " - " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
				}
			}else if(south_o){
				if(south_d ){
					dist1 = operator.getShortestDistanceToBoundary(origin);
					dist2 = operator.getShortestDistanceToBoundary(destn);
					if(dist1 >= dist2){
						return "operating area is relaxed towards South by " + convertMiles(dist1) + " miles";
					}else{
						return "operating area is relaxed towards South by " + convertMiles(dist2) + " miles";
					}
				}else if(east_d){
					return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles"; 
				}else if(west_d){
					return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(origin))
							+ "miles & West by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles";
				}else if(north_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(destn))
							+ "miles & South by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) +" miles";
				}else{
//					return "n/a;d " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
					return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " - " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
				}
			}else if(west_o){
				if(west_d){
					dist1 = operator.getShortestDistanceToBoundary(origin);
					dist2 = operator.getShortestDistanceToBoundary(destn);
					if(dist1 >= dist2){
						return "operating area is relaxed towards West by " + convertMiles(dist1) + " miles";
					}else{
						return "operating area is relaxed towards West by " + convertMiles(dist2) + " miles";
					}
				}else if(east_d){
					return "operating area is relaxed towards West by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
							+ " miles & East by " + convertMiles(operator.getShortestDistanceToBoundary(destn)) +" miles"; 
				}else if(north_d){
					return "operating area is relaxed towards North by " + convertMiles(operator.getShortestDistanceToBoundary(destn))
							+ "miles & West by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) +" miles";
				}else if(south_d){
					return "operating area is relaxed towards South by " + convertMiles(operator.getShortestDistanceToBoundary(destn))
							+ "miles & West by " + convertMiles(operator.getShortestDistanceToBoundary(origin)) +" miles";
				}
//				return "n/a;d " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
				return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
						+ " - " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
			}else{
//				return "n/a;o " + convertMiles(operator.getShortestDistanceToBoundary(origin))+ " miles";
				return "n/a;od " + convertMiles(operator.getShortestDistanceToBoundary(origin)) 
						+ " - " + convertMiles(operator.getShortestDistanceToBoundary(destn)) + " miles";
			}
		}
	}
	
	
	public String relax_eligibility(Operator operator, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
		
		if(operator.relaxPassengerType(age_group, mobility_status, journey_purpose) < 100){
			return operator.relaxiPassengerTypeInfo(age_group, mobility_status, journey_purpose);
		}
		return "n/a";
	}
	
	
	
	private String convertMiles(double dist){
		
		final double MILE = 1609.344;
		double miles = dist/MILE;
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(miles);
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
	
	private boolean checkEligibility(Operator operator, PassengerAge age_group, PassengerMobilityStatus mobility_status, PassengerJourneyPurpose journey_purpose){
		return operator.checkEligibility(age_group, mobility_status, journey_purpose);
	}
	
	private double calFare(Operator operator, DirectionsResponse response){
		
		if(operator.getFare().equals("free")){ // BABS bus service;
			return -1;
		}else if(operator.getDescription().equals("taxi service")){
			return taxi_fare(new Integer(response.getRoutes()[0].getLegs()[0].getDistance().getValue()));
		}else if(operator instanceof BABSCarService){ // BABS car service
			return hire_car(new Integer(response.getRoutes()[0].getLegs()[0].getDistance().getValue()));
		}else if(operator.getFare().startsWith("Equivalent to standard bus fares")){ // Banffshire Partnership Dial a Bus; Turriff A2B service
			return -2; 
		}else if(operator.getFare().startsWith("Standard bus fares")){ // MforMorayBuckieService; MforMorayForresService; MforMorayKeithService; MforMoraySpeysideService
			return -3;
		}
		return 0;
	}
	
	private double hire_car(int meters){
		
		double m = meters*0.00062137;
		int miles = ((int)m) +1;
        return 7 + 0.45*(miles - 1);
	}
	
	private double taxi_fare(int meters){
		
		double m = meters*0.00062137;
		int miles = ((int)m) +1;
		return 2.4 + 1.80*(miles);
	}
	
}
