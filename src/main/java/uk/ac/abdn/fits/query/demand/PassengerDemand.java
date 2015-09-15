package uk.ac.abdn.fits.query.demand;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Calendar;

import uk.ac.abdn.fits.query.operator.*;
import uk.me.jstott.jcoord.LatLng;


public class PassengerDemand {
	
	private String id;
	private int age;
	private short wheelchair;
	private short difficulty_walking;
	private Calendar arrive_by;
	private Calendar depart_after;
	private String origin_address;
	private String destn_address;
	private PassengerAge age_group;
	private PassengerMobilityStatus mobility_status;
	private PassengerJourneyPurpose purpose;
	private LatLng origin = null;
	private LatLng destn = null;
	
	public PassengerDemand(){
	}
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	public short getWheelchair(){
		return wheelchair;
	}
	public void setWheelchair(short wheelchair){
		this.wheelchair = wheelchair;
	}
	public short getDifficulty_walking(){
		return difficulty_walking;
	}
	public void setDifficulty_walking(short difficulty_walking){
		this.difficulty_walking = difficulty_walking;
	}
	public void setArriveBy(Calendar c){
		this.arrive_by = c;
	}
	public Calendar getArriveBy(){
		return arrive_by;
	}
	public void setDepartAfter(Calendar depart_after){
		this.depart_after = depart_after;
	}
	public Calendar getDepartAfter(){
		return depart_after;
	}
	public void setArriveByString(String time){
		this.arrive_by = Calendar.getInstance();
		String[] tokens = time.split(":");
		if(tokens.length == 3){
			arrive_by.set(Calendar.HOUR_OF_DAY, new Integer(tokens[0]));
			arrive_by.set(Calendar.MINUTE, new Integer(tokens[1]));
			arrive_by.set(Calendar.SECOND, new Integer(tokens[2]));
			arrive_by.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//			System.out.println(arrive_by.getTime().toGMTString());
		}else{
			System.err.println("invalid time tokens");
		}
	}
	public String getOrigin_address(){
		return origin_address;
	}
	public void setOrigin_address(String origin_address){
		this.origin_address = origin_address;
	}
	public String getDestn_address(){
		return destn_address;
	}
	public void setDestn_address(String destn_address){
		this.destn_address = destn_address;
	}
	public PassengerAge getAgeGroup(){
		if(age_group == null){
			if(age < 16){
				age_group = PassengerAge.under_16;
			}else if(age <= 21){
				age_group = PassengerAge.from_16_to_21;
			}else if(age <= 54){
				age_group = PassengerAge.from_22_to_54;
			}else if(age <=59){
				age_group = PassengerAge.from_55_to_59;
			}else{
				age_group = PassengerAge.over_60;
			}
		}
		return age_group;
	}
	public void setAgeGroup(PassengerAge age_group){
		this.age_group = age_group;
	}
	public String getAgeGroupString(){
		if(age < 16){
			return "<under 16>";
		}else if(age <= 21){
			return "<16 to 21>";
		}else if(age <= 54){
			return "<22 to 54>";
		}else if(age <=59){
			return "<55 to 59>";
		}else{
			return "<60 and above>";
		}
	}
	public PassengerMobilityStatus getMobilityStatus(){
		if(mobility_status == null){
			if(wheelchair == 1){
				mobility_status = PassengerMobilityStatus.Disabled_wheelchair_user;
			}else if(difficulty_walking == 1){
				mobility_status = PassengerMobilityStatus.Disable_other;
			}else{
				mobility_status = PassengerMobilityStatus.Able_bodied;
			}
		}
		return mobility_status;
	}
	public String getMobilityStatusString(){
		if(wheelchair == 1){
			return "<Disabled - wheelchair user>";
		}else if(difficulty_walking == 1){
			return "<Disabled - other>";
		}else{
			return "<Able bodied>";
		}
	}
	public void setMobilityStatus(PassengerMobilityStatus mobility_status){
		this.mobility_status = mobility_status;
	}
	public PassengerJourneyPurpose getPurpose(){
		return purpose;
	}
	public String getPurposeString(){
		if(purpose == PassengerJourneyPurpose.Health_appointment){
			return "Health Appointment";
		}
		return "";
	}
	public void setPurpose(PassengerJourneyPurpose purpose){
		this.purpose = purpose;
	}
	public LatLng getOrigin(){
		return origin;
	}
	public void setOrigin(LatLng origin){
		this.origin = origin;
	}
	public LatLng getDestn(){
		return destn;
	}
	public void setDestn(LatLng destn){
		this.destn = destn;
	}
	
	
}