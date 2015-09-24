package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.abdn.fits.hibernate.model.*;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;


public class HibUtils {
	
	
	private static OperatorDataManager operatorDataManager = null;
	
	public enum Day {
	    Sunday, Monday, Tuesday, Wednesday,
	    Thursday, Friday, Saturday 
	}
	
	public enum DaySession{
		Morning, Afternoon
	}
	
	public HibUtils(){
	
		if (operatorDataManager == null){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
			operatorDataManager = 
			        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		}
	}
	
	public int insertOperator(OperatorDataInputForm form){
		
		Operator operator = new Operator();
		operator.setName(form.getService_name());
		operator.setDescription(form.getService_description());
		Date last_update = new Date(Calendar.getInstance().getTime().getTime());
		operator.setLast_update(last_update);
		operatorDataManager.insertOperator(operator);
		operator = operatorDataManager.getOperator(operator.getName());
		return operator.getOperator_id();
	}
	
	
	public int getOperIdByName(String name){
		
		Operator operator = operatorDataManager.getOperator(name);
		return operator.getOperator_id();
	}
	
//	public void insertFareS tructure(int operator_id, OperatorDataInputForm form){
//		
//		FareStructure fare_structure = new FareStructure();
//		fare_structure.setOperator_id(operator_id);
//		fare_structure.setReturn_fare_multiplier(form.getReturn_fare_multiplier());
//		fare_structure.setDiscount_for_over60(form.getDiscount_for_over60());
//		fare_structure.setDiscount_for_under16(form.getDiscount_for_under16());
//		fare_structure.setHas_escort(form.isFare_structure_checkbox_escort());
//		fare_structure.setCharge_for_dead_mileage(form.isFare_structure_checkbox_charge_for_dead_mileage());
//		operatorDataManager.insertFareStructure(fare_structure);
////		int fare_structure_id = operatorDataManager.getFareStructureByOpId(operator_id).getId();
//		int fare_structure_id = fare_structure.getId();
//		List<FareMileageBands> mileage_bands = getFareMileageBands(fare_structure_id, form);
//		for(FareMileageBands milage_band: mileage_bands){
//			operatorDataManager.insertFareMileageBands(milage_band);
//		}
//	}
	
	private List<FareMileageBands> getFareMileageBands(int fare_structure_id, Fare fare){
		ArrayList<FareMileageBands> mileage_bands = new ArrayList<FareMileageBands>();
		FareMileageBands mileage_band = null;
		if(fare.getFare_dist1_mile_2()>0){
			mileage_band = new FareMileageBands();
			mileage_band.setFare_structure_id(fare_structure_id);
			mileage_band.setMile_1(0);
			mileage_band.setMile_2(fare.getFare_dist1_mile_2());
			mileage_band.setType(fare.getFare_dist1_type());
			mileage_band.setCharge(fare.getFare_dist1_charge());
			mileage_bands.add(mileage_band);
		}
		if(fare.getFare_dist2_mile_1() > 0 && fare.getFare_dist2_mile_2() > 0){
			mileage_band = new FareMileageBands();
			mileage_band.setFare_structure_id(fare_structure_id);
			mileage_band.setMile_1(fare.getFare_dist2_mile_1());
			mileage_band.setMile_2(fare.getFare_dist2_mile_2());
			mileage_band.setType(fare.getFare_dist2_type());
			mileage_band.setCharge(fare.getFare_dist2_charge());
			mileage_bands.add(mileage_band);
		}
		
		String fare_dist = fare.getAdded_fare_dist();
//		String entry = null;
		StringTokenizer st = null;
		StringTokenizer entryST = null;
		String ind = null;
		String mile1 = null;
		String mile2 = null;
		String type = null;
		String fare_str = null;
		if(fare_dist != null && fare_dist.length()>0){
			st = new StringTokenizer(fare_dist, "#");
			while(st.hasMoreTokens()){
				entryST = new StringTokenizer(st.nextToken(), ",");
				ind = entryST.nextToken();
				mile1 = entryST.nextToken().trim();
				mile2 = entryST.nextToken().trim();
				type = entryST.nextToken();
				fare_str = entryST.nextToken().trim();
				if(mile1.length()>0){
					mileage_band = new FareMileageBands();
					mileage_band.setFare_structure_id(fare_structure_id);
					mileage_band.setMile_1(new Double(mile1));
					mileage_band.setMile_2(new Double(mile2));
					mileage_band.setType(type);
					mileage_band.setCharge(new Double(fare_str));
					mileage_bands.add(mileage_band);
				}
			}
		}
		
//		if(fare.getFare_dist3_mile_1() > 0 && fare.getFare_dist3_mile_2() > 0){
//			mileage_band = new FareMileageBands();
//			mileage_band.setFare_structure_id(fare_structure_id);
//			mileage_band.setMile_1(fare.getFare_dist3_mile_1());
//			mileage_band.setMile_2(fare.getFare_dist3_mile_2());
//			mileage_band.setType(fare.getFare_dist3_type());
//			mileage_band.setCharge(fare.getFare_dist3_charge());
//			mileage_bands.add(mileage_band);
//		}
//		if(fare.getFare_dist4_mile_1() > 0 && fare.getFare_dist4_mile_2() > 0){
//			mileage_band = new FareMileageBands();
//			mileage_band.setFare_structure_id(fare_structure_id);
//			mileage_band.setMile_1(fare.getFare_dist4_mile_1());
//			mileage_band.setMile_2(fare.getFare_dist4_mile_2());
//			mileage_band.setType(fare.getFare_dist4_type());
//			mileage_band.setCharge(fare.getFare_dist4_charge());
//			mileage_bands.add(mileage_band);
//		}
//		fare_structure.setDist5_other(""); // to be processed
		return mileage_bands;
	}
	
	
//	public void insertOperatingArea(int operator_id, OperatorDataInputForm form){
//		OperatingArea operating_area = new OperatingArea();
//		operating_area.setOperator_id(operator_id);
//		operating_area.setJson(form.getJsonData());
//		operating_area.setKml(form.getKmlData());
//		operatorDataManager.insertOperatingArea(operating_area);
//	}
	
//	public void insertPassengerElig(int operator_id, OperatorDataInputForm form){
//		
//		PassengerElig passenger_elig = new PassengerElig();
//		passenger_elig.setOperator_id(operator_id);
//		passenger_elig.setAge_under16(form.getEligible_checkbox_under16()!=null?form.getEligible_checkbox_under16().equals("1"):false);
//		passenger_elig.setAge_17to21(form.getEligible_checkbox_17_to_21()!=null?form.getEligible_checkbox_17_to_21().equals("2"):false);
//		passenger_elig.setAge_22to54(form.getEligible_checkbox_22_to_54()!=null?form.getEligible_checkbox_22_to_54().equals("3"):false);
//		passenger_elig.setAge_55to59(form.getEligible_checkbox_55_to_59()!=null?form.getEligible_checkbox_55_to_59().equals("4"):false);
//		passenger_elig.setAge_over60(form.getEligible_checkbox_over60()!=null?form.getEligible_checkbox_over60().equals("5"):false);
//		passenger_elig.setAble_bodied(form.getEligible_checkbox_able_bodied()!=null?form.getEligible_checkbox_able_bodied().equals("6"):false);
//		passenger_elig.setMobility_prevents_PT(form.getEligible_checkbox_mobility_prevents_PT()!=null?form.getEligible_checkbox_mobility_prevents_PT().equals("7"):false);
//		passenger_elig.setDiable_wheelchair_user(form.getEligible_checkbox_disable_wheelchair_user()!=null?form.getEligible_checkbox_disable_wheelchair_user().equals("8"):false);
//		passenger_elig.setDiable_other(form.getEligible_checkbox_disable_other()!=null?form.getEligible_checkbox_disable_other().equals("9"):false);
//		passenger_elig.setHealth_appointment(form.getEligible_checkbox_health_appointment()!=null?form.getEligible_checkbox_health_appointment().equals("10"):false);
//		passenger_elig.setSocial_care(form.getEligible_checkbox_social_care()!=null?form.getEligible_checkbox_social_care().equals("11"):false);
//		passenger_elig.setShopping(form.getEligible_checkbox_shopping()!=null?form.getEligible_checkbox_shopping().equals("12"):false);
//		passenger_elig.setLeisure_or_visiting_friends(form.getEligible_checkbox_leisure_or_visiting_friends()!=null?form.getEligible_checkbox_leisure_or_visiting_friends().equals("13"):false);
//		passenger_elig.setSchool_or_education(form.getEligible_checkbox_school_or_education()!=null?form.getEligible_checkbox_school_or_education().equals("14"):false);
//		passenger_elig.setWork_or_commuting(form.getEligible_checkbox_work_or_commuting()!=null?form.getEligible_checkbox_work_or_commuting().equals("15"):false);
//		passenger_elig.setOther_purpose(form.getEligible_checkbox_other_purpose()!=null?form.getEligible_checkbox_other_purpose().equals("16"):false);
//		operatorDataManager.insertPassengerElig(passenger_elig);
//	}

	/** deprecated 
	 * 
	public void insertSurchargeAgeGroup(int operator_id, OperatorDataInputForm form){
		
		SurchargeAgeGroup sur_age_group = new SurchargeAgeGroup();
		sur_age_group.setOperator_id(operator_id);
		sur_age_group.setUnder16(form.isSurcharge_checkbox_under16());
		sur_age_group.setUnder16_surcharge(form.getSurcharge_under_16_num());
		sur_age_group.set_17to21(form.isSurcharge_checkbox_17_to_21());
		sur_age_group.set_17to21_surcharge(form.getSurcharge_17_21_years_old_num());
		sur_age_group.set_22to54(form.isSurcharge_checkbox_22_to_54());
		sur_age_group.set_22to54_surcharge(form.getSurcharge_22_54_years_old_num());
		sur_age_group.set_55to59(form.isSurcharge_checkbox_55_to_59());
		sur_age_group.set_55to59_surcharge(form.getSurcharge_55_59_years_old_num());
		sur_age_group.setOver60(form.isSurcharge_checkbox_over60());
		sur_age_group.setOver60_surcharge(form.getSurcharge_over_60_years_old_num());
		operatorDataManager.insertSurchargeAgeGroup(sur_age_group);
	}
	
	public void insertSurchargeDistance(int operator_id, OperatorDataInputForm form){
		SurchargeDistance sur_dist = new SurchargeDistance();
		sur_dist.setOperator_id(operator_id);
		operatorDataManager.insertSurchargeDistance(sur_dist);
//		int surcharge_distance_id = operatorDataManager.getSurchargeDistanceByOpId(operator_id).getId();
		int surcharge_distance_id = sur_dist.getId();
		List<SurchargeMileageBands> mileage_bands = getSurchargeMileageBands(surcharge_distance_id, form);
		for(SurchargeMileageBands milage_band: mileage_bands){
			operatorDataManager.insertSurchargeMileageBands(milage_band);
		}
	}
	
	
	private List<SurchargeMileageBands> getSurchargeMileageBands(int surcharge_distance_id, OperatorDataInputForm form){
		ArrayList<SurchargeMileageBands> mileage_bands = new ArrayList<SurchargeMileageBands>();
		SurchargeMileageBands mileage_band = null;
		if(form.getSurcharge_dist1_mile()>0){
			mileage_band = new SurchargeMileageBands();
			mileage_band.setSurcharge_distance_id(surcharge_distance_id);
			mileage_band.setMile_1(0);
			mileage_band.setMile_2(form.getSurcharge_dist1_mile());
			mileage_band.setType(form.getSurcharge_dist1_type());
			mileage_band.setSurcharge(form.getSurcharge_dist1_charge());
			mileage_bands.add(mileage_band);
		}
		if(form.getSurcharge_dist2_1() > 0 && form.getSurcharge_dist2_2() > 0){
			mileage_band = new SurchargeMileageBands();
			mileage_band.setSurcharge_distance_id(surcharge_distance_id);
			mileage_band.setMile_1(form.getSurcharge_dist2_1());
			mileage_band.setMile_2(form.getSurcharge_dist2_2());
			mileage_band.setType(form.getSurcharge_dist2_type());
			mileage_band.setSurcharge(form.getSurcharge_dist2_charge());
			mileage_bands.add(mileage_band);
		}
		if(form.getSurcharge_dist3_mile_1() > 0 && form.getSurcharge_dist3_mile_2() > 0){
			mileage_band = new SurchargeMileageBands();
			mileage_band.setSurcharge_distance_id(surcharge_distance_id);
			mileage_band.setMile_1(form.getSurcharge_dist3_mile_1());
			mileage_band.setMile_2(form.getSurcharge_dist3_mile_2());
			mileage_band.setType(form.getSurcharge_dist3_type());
			mileage_band.setSurcharge(form.getSurcharge_dist3_charge());
			mileage_bands.add(mileage_band);
		}
		if(form.getSurcharge_dist4_mile_1() > 0 && form.getSurcharge_dist4_mile_2() > 0){
			mileage_band = new SurchargeMileageBands();
			mileage_band.setSurcharge_distance_id(surcharge_distance_id);
			mileage_band.setMile_1(form.getSurcharge_dist4_mile_1());
			mileage_band.setMile_2(form.getSurcharge_dist4_mile_2());
			mileage_band.setType(form.getSurcharge_dist4_type());
			mileage_band.setSurcharge(form.getSurcharge_dist4_charge());
			mileage_bands.add(mileage_band);
		}
//		surcharge_disitance.setDist5_other(""); // to be processed
		return mileage_bands;
	}
	 
	
	public void insertSurchargeJourneyPurpose(int operator_id, OperatorDataInputForm form){
		SurchargeJourneyPurpose sur_jour_purp = new SurchargeJourneyPurpose();
		sur_jour_purp.setOperator_id(operator_id);
		sur_jour_purp.setHealth_appointment(form.isSurcharge_checkbox_health_appointment());
		sur_jour_purp.setHealth_appointment_surcharge(form.getSurcharge_health_appointment_num());
		sur_jour_purp.setSocial_care(form.isSurcharge_checkbox_social_care());
		sur_jour_purp.setSocial_care_surcharge(form.getSurcharge_social_care_num());
		sur_jour_purp.setShopping(form.isSurcharge_checkbox_shopping());
		sur_jour_purp.setShopping_surcharge(form.getSurcharge_shopping_num());
		sur_jour_purp.setLeisure_or_visiting_friends(form.isSurcharge_checkbox_leisure_visiting_friends());
		sur_jour_purp.setLeisure_or_visiting_friends_surcharge(form.getSurcharge_leisure_visiting_friends_num());
		sur_jour_purp.setSchool_or_education(form.isSurcharge_checkbox_school_education());
		sur_jour_purp.setSchool_or_education_surcharge(form.getSurcharge_school_education_num());
		sur_jour_purp.setWork_or_commuting(form.isSurcharge_checkbox_work_commuting());
		sur_jour_purp.setWork_or_commuting_surcharge(form.getSurcharge_work_commuting_num());
		sur_jour_purp.setOther_purpose(form.isSurcharge_checkbox_other_purpose());
		sur_jour_purp.setOther_purpose_surcharge(form.getSurcharge_other_purpose_num());
		operatorDataManager.insertSurchargeJourneyPurpose(sur_jour_purp);
	}
	
	public void insertSurchargeMobilityStatus(int operator_id, OperatorDataInputForm form){
		SurchargeMobilityStatus sur_mob_stat = new SurchargeMobilityStatus();
		sur_mob_stat.setOperator_id(operator_id);
		sur_mob_stat.setAble_bodied(form.isSurcharge_checkbox_able_bodied());
		sur_mob_stat.setAble_bodied_surcharge(form.getSurcharge_able_bodied_num());
		sur_mob_stat.setMobility_prevents_PT(form.isSurcharge_checkbox_mobility_prevents_PT());
		sur_mob_stat.setMobility_prevents_PT_surcharge(form.getSurcharge_mobility_prevents_PT_num());
		sur_mob_stat.setDiable_wheelchair_user(form.isSurcharge_checkbox_diable_wheelchair_user());
		sur_mob_stat.setDiable_wheelchair_user_surcharge(form.getSurcharge_disable_wheelchair_user_num());
		sur_mob_stat.setDiable_other(form.isSurcharge_checkbox_disable_other());
		sur_mob_stat.setDiable_other_surcharge(form.getSurcharge_disable_other_num());
		operatorDataManager.insertSurchargeMobilityStatus(sur_mob_stat);
	}
	
	public void insertSurchargeOperatingTime(int operator_id, OperatorDataInputForm form){
		SurchargeOperatingTime sur_operating_time = new SurchargeOperatingTime();
		sur_operating_time.setOperator_id(operator_id);
		sur_operating_time.setStart_05_hours_earlier(form.isSurcharge_checkbox_start_05_hours_earlier());
		sur_operating_time.setStart_05_hours_earlier_surcharge(form.getSurcharge_start_05_hours_earlier());
		sur_operating_time.setStart_1_hour_earlier(form.isSurcharge_checkbox_start_1_hour_earlier());
		sur_operating_time.setStart_1_hour_earlier_surcharge(form.getSurcharge_start_1_hour_earlier());
		sur_operating_time.setStart_15_hours_earlier(form.isSurcharge_checkbox_start_15_hours_earlier());
		sur_operating_time.setStart_15_hours_earlier_surcharge(form.getSurcharge_start_15_hours_earlier());
		sur_operating_time.setFinish_05_hours_later(form.isSurcharge_checkbox_finish_05_hours_later());
		sur_operating_time.setFinish_05_hours_later_surcharge(form.getSurcharge_finish_05_hours_later());
		sur_operating_time.setFinish_1_hour_later(form.isSurcharge_checkbox_finish_1_hour_later());
		sur_operating_time.setFinish_1_hour_later_surcharge(form.getSurcharge_finish_1_hour_later());
		sur_operating_time.setFinish_15_hours_later(form.isSurcharge_checkbox_finish_15_hours_later());
		sur_operating_time.setFinish_15_hours_later_surcharge(form.getSurcharge_finish_15_hours_later());
		operatorDataManager.insertSurchargeOperatingTime(sur_operating_time);
	}
	*/
	
	/** deprecated
	public void insertOperationalHours(int operator_id, OperatorDataInputForm form){
		

//		<tr><td>Monday</td><td>09:00</td><td>18:00</td></tr><tr><td>Tuesday</td><td>09:00</td><td>18:00</td></tr><tr><td>Wednesday</td><td>09:00</td><td>18:00</td></tr><tr><td>Thursday</td><td>09:00</td><td>18:00</td></tr><tr><td>Friday</td><td>09:00</td><td>18:00</td></tr><tr><td>Saturday</td><td>Closed</td><td></td></tr><tr><td>Sunday</td><td>Closed</td><td></td></tr>

		String opening_hours = form.getOpening_hours();
		ArrayList<OperationalHours> operationalHours = new ArrayList<OperationalHours>();
		OperationalHours hours = null;
		String pattern = "(?i)(<tr.*?>)(.+?)(</tr>)";
		Pattern p0 = Pattern.compile(pattern); // find the <tr></tr>
	    Matcher m0 = p0.matcher(opening_hours);
	    StringTokenizer st = null;
	    String hoursStr = null;
	    while(m0.find()){
	    	hours = new OperationalHours();
	    	hoursStr = m0.group();
	    	hoursStr = hoursStr.replaceAll("(<td>)|(</td>)|(<tr>)|(</tr>)", " ");
	    	st = new StringTokenizer(hoursStr);
	    	if(st.countTokens()==3){  // if the operational hours are closed, ignore that line
	    		hours.setOperator_id(operator_id);
		    	hours.setDay_of_week(st.nextToken().trim());
//		    	hours.setOpening_time(st.nextToken().trim());
//		    	hours.setClosing_time(st.nextToken().trim());
		    	operationalHours.add(hours);
	    	}
	    }
		
		for(int i=0; i<operationalHours.size(); i++){
			hours = operationalHours.get(i);
			operatorDataManager.insertOperationalHours(hours);
		}
		
	}
	*/
	public int updateGeneralInfo(int operator_id, GeneralInfo genralInfo){
		Operator operator = operatorDataManager.getOperatorById(operator_id);

		operator.setName(genralInfo.getName());
		operator.setDescription(genralInfo.getDescription());
		operator.setHow2book(genralInfo.getHow2book());
		operator.setType_of_permit(genralInfo.getType_of_permit());
		operator.setInactive(genralInfo.getCb_not_avail());
		Date last_update = new Date(Calendar.getInstance().getTime().getTime());
		operator.setLast_update(last_update);

		operatorDataManager.updateOperator(operator);
		
		operatorDataManager.deleteOperationalHoursByOpId(operator_id);
		insertOperatingHours(operator.getOperator_id(),genralInfo);

		operatorDataManager.deleteServiceNotAvailTimeByOpId(operator_id);
		insertServiceNotAvailTime(operator.getOperator_id(),genralInfo);
					

    	return operator.getOperator_id();
	}

	public void insertOperatingHours(int opId, GeneralInfo genralInfo){
		Operator operator = operatorDataManager.getOperatorById(opId);
		/* insert operational hours*/
		ArrayList<OperationalHours> operationalHours = new ArrayList<OperationalHours>();
		OperationalHours hours = null;
		/* Monday */
		hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Monday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouMon1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouMon2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Monday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouMon3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouMon4()));
    	operationalHours.add(hours);
    	/* Tuesday */
		hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Tuesday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouTue1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouTue2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Tuesday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouTue3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouTue4()));
    	operationalHours.add(hours);
    	/* Wednesday */
    	hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Wednesday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouWed1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouWed2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Wednesday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouWed3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouWed4()));
    	operationalHours.add(hours);
    	/* Thursday */
    	hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Thursday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouThu1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouThu2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Thursday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouThu3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouThu4()));
    	operationalHours.add(hours);
    	/* Friday */
    	hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Friday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouFri1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouFri2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Friday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouFri3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouFri4()));
    	operationalHours.add(hours);
    	/* Saturday */
    	hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Saturday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouSat1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouSat2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Saturday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouSat3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouSat4()));
    	operationalHours.add(hours);
    	/* Sunday */
    	hours = new OperationalHours(); // morning session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Sunday.toString());
    	hours.setSession(DaySession.Morning.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouSun1()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouSun2()));
    	operationalHours.add(hours);
    	hours = new OperationalHours(); // afternoon session
		hours.setOperator_id(operator.getOperator_id());
    	hours.setDay_of_week(Day.Sunday.toString());
    	hours.setSession(DaySession.Afternoon.toString());
    	hours.setOpening_time(getTime(genralInfo.getOprHouSun3()));
    	hours.setClosing_time(getTime(genralInfo.getOprHouSun4()));
    	operationalHours.add(hours);
    	for(int i=0; i<operationalHours.size(); i++){
			hours = operationalHours.get(i);
			operatorDataManager.insertOperationalHours(hours);
		}
	}
	public void insertServiceNotAvailTime(int opId,GeneralInfo genralInfo){
		Operator operator = operatorDataManager.getOperatorById(opId);
    	Date date = null;
		String service_not_avail = genralInfo.getAdded_service_not_avail();
		StringTokenizer st = null;
		StringTokenizer entryST = null;
		String ind = null;
		String from = null;
		String to = null;
		ServiceNotAvailable sna = null;
		List<ServiceNotAvailable> sna_list = new ArrayList<ServiceNotAvailable>();
		if(genralInfo.getCb_not_avail() == true){
			if(genralInfo.getNot_valid_from() != null && genralInfo.getNot_valid_from().length()>0){
				date = getStartOfDay(genralInfo.getNot_valid_from());
				sna = new ServiceNotAvailable();
				sna.setOperator_id(operator.getOperator_id());
				sna.setFrom(date);
				if(genralInfo.getNot_valid_to() != null && genralInfo.getNot_valid_to().length()>0){
					date = getEndOfDay(genralInfo.getNot_valid_to());
					sna.setTo(date);
				}
				sna_list.add(sna);
			}
			if(service_not_avail != null && service_not_avail.length()>0){
				st = new StringTokenizer(service_not_avail, "#");
				while(st.hasMoreTokens()){
					entryST = new StringTokenizer(st.nextToken(), ",");
					ind = entryST.nextToken();
					from = entryST.nextToken().trim();
					to = entryST.nextToken().trim();
					if(from.length()>0){
						sna = new ServiceNotAvailable();
						sna.setOperator_id(operator.getOperator_id());
						date = getStartOfDayAddedPeriod(from);
						sna.setFrom(date);
						if(to!=null && to.length()>0){
							date = getEndOfDayAddedPeriod(to);
							sna.setTo(date);
						}
						sna_list.add(sna);
					}
				}
			}
		}
		for(int i=0; i< sna_list.size(); i++){
			operatorDataManager.insertServiceNotAvailable(sna_list.get(i));
		}
	}

	public int insertGeneralInfo(String username, GeneralInfo genralInfo){
		
		Operator operator = new Operator();
		operator.setName(genralInfo.getName());
		operator.setDescription(genralInfo.getDescription());
		operator.setHow2book(genralInfo.getHow2book());
		operator.setType_of_permit(genralInfo.getType_of_permit());
		operator.setInactive(genralInfo.getCb_not_avail());
		Date last_update = new Date(Calendar.getInstance().getTime().getTime());
		operator.setLast_update(last_update);
		// get user_id
		User user = operatorDataManager.getUser(username);
		if(user != null){
			operator.setUser_id(user.getId());
		}
		operatorDataManager.insertOperator(operator);
		operator = operatorDataManager.getOperatorById(operator.getOperator_id());
		System.out.println("inserted operator: id " + operator.getOperator_id());
	
		insertOperatingHours(operator.getOperator_id(),genralInfo);
		insertServiceNotAvailTime(operator.getOperator_id(),genralInfo);
    	return operator.getOperator_id();
	}
	
	public void insertOperatingArea(int operator_id, OperatingAreaInfo operatingArea){
		OperatingArea operating_area = new OperatingArea();
		operating_area.setOperator_id(operator_id);
		operating_area.setJson(operatingArea.getJsonData());
		operating_area.setKml(operatingArea.getKmlData());
		
		operatorDataManager.insertOperatingArea(operating_area);
	}
	
	public void updateOperatingArea(int operator_id, OperatingAreaInfo operatingAreaInfo){
		OperatingArea operating_area = operatorDataManager.getOperatingAreaByOpId(operator_id);
		if(operating_area!=null)
			operatorDataManager.deleteOperatingArea(operating_area); //update was creating duplicates so I used delete!
		insertOperatingArea(operator_id,operatingAreaInfo);
	}
	
	public void insertElig(int operator_id, Elig elig){
		
		PassengerElig passenger_elig = new PassengerElig();
		passenger_elig.setOperator_id(operator_id);
		passenger_elig.setAge_under16(elig.isAge_under16());
		passenger_elig.setAge_17to21(elig.isAge_17to21());
		passenger_elig.setAge_22to54(elig.isAge_22to54());
		passenger_elig.setAge_55to59(elig.isAge_55to59());
		passenger_elig.setAge_over60(elig.isAge_over60());
		passenger_elig.setAble_bodied(elig.isAble_bodied());
		passenger_elig.setMobility_prevents_PT(elig.isMobility_prevents_PT());
		passenger_elig.setDiable_wheelchair_user(elig.isDisable_wheelchair_user());
		passenger_elig.setDiable_other(elig.isDisable_other());
		passenger_elig.setHealth_appointment(elig.isHealth_appointment());
		passenger_elig.setSocial_care(elig.isSocial_care());
		passenger_elig.setShopping(elig.isShopping());
		passenger_elig.setLeisure_or_visiting_friends(elig.isLeisure_or_visiting_friends());
		passenger_elig.setSchool_or_education(elig.isSchool_or_education());
		passenger_elig.setWork_or_commuting(elig.isWork_or_commuting());
		passenger_elig.setOther_purpose(elig.isOther_purpose());
		passenger_elig.setElig_radioBtns(elig.getTab_elig_radioBtns());
		passenger_elig.setExplain_opening_up_elig(elig.getExplain_opening_up_elig());
		operatorDataManager.insertPassengerElig(passenger_elig);
		if(!elig.getOther_elig_type().equals("none")){
			OtherEligTable otherElig = new OtherEligTable();
			if(elig.getOther_elig_type().equals("other")){
				if(!elig.getOther_elig().equals("")){
					otherElig.setOperator_id(operator_id);
					otherElig.setElig(elig.getOther_elig());
					operatorDataManager.insertOperatorOtherElig(otherElig);
				}
			}else{
				otherElig.setElig(elig.getOther_elig());
			}
		}
	}
	
	public void updateElig(PassengerElig passenger_elig, Elig eligInfo, int opId){
		
		//PassengerElig passenger_elig = operatorDataManager.getPassengerElig(operator_id);
		passenger_elig.setAge_under16(eligInfo.isAge_under16());
		passenger_elig.setAge_17to21(eligInfo.isAge_17to21());
		passenger_elig.setAge_22to54(eligInfo.isAge_22to54());
		passenger_elig.setAge_55to59(eligInfo.isAge_55to59());
		passenger_elig.setAge_over60(eligInfo.isAge_over60());
		passenger_elig.setAble_bodied(eligInfo.isAble_bodied());
		passenger_elig.setMobility_prevents_PT(eligInfo.isMobility_prevents_PT());
		passenger_elig.setDiable_wheelchair_user(eligInfo.isDisable_wheelchair_user());
		passenger_elig.setDiable_other(eligInfo.isDisable_other());
		passenger_elig.setHealth_appointment(eligInfo.isHealth_appointment());
		passenger_elig.setSocial_care(eligInfo.isSocial_care());
		passenger_elig.setShopping(eligInfo.isShopping());
		passenger_elig.setLeisure_or_visiting_friends(eligInfo.isLeisure_or_visiting_friends());
		passenger_elig.setSchool_or_education(eligInfo.isSchool_or_education());
		passenger_elig.setWork_or_commuting(eligInfo.isWork_or_commuting());
		passenger_elig.setOther_purpose(eligInfo.isOther_purpose());
		passenger_elig.setElig_radioBtns(eligInfo.getTab_elig_radioBtns());
		passenger_elig.setExplain_opening_up_elig(eligInfo.getExplain_opening_up_elig());

		operatorDataManager.deleteOperatorOtherEligsByOpID(opId); //update was creating duplicates, so i had to delete
		if(!eligInfo.getOther_elig_type().equals("none")){
			OtherEligTable otherElig = new OtherEligTable();
			if(eligInfo.getOther_elig_type().equals("other")){
				if(!eligInfo.getOther_elig().equals("")){
					otherElig.setOperator_id(passenger_elig.getOperator_id());
					otherElig.setElig(eligInfo.getOther_elig());
					operatorDataManager.insertOperatorOtherElig(otherElig);
				}
			}else{
				otherElig.setElig(eligInfo.getOther_elig());
			}
		}
		
		operatorDataManager.updatePassengerElig(passenger_elig);

	}
	
	public void insertFare(int operator_id, Fare fare){
		
		FareStructure fare_structure = new FareStructure();
		fare_structure.setOperator_id(operator_id);
		fare_structure.setFare_structure_radioBtns(fare.getTab_fare_structure_radioBtns());
		fare_structure.setHow_charge_radioBtns(fare.getTab_fare_structure_how_charge_radioBtns());
		fare_structure.setReturn_fare_multiplier(fare.getReturn_fare_multiplier());
		fare_structure.setDiscount_for_over60(new Double(fare.getDiscount_for_over60()));
		fare_structure.setDiscount_for_under16(new Double(fare.getDiscount_for_under16()));
		fare_structure.setDiscount_for_other_concessionary(new Double(fare.getDiscount_for_other_concessionary()));
		fare_structure.setHas_escort(fare.isFare_structure_checkbox_escort());
		fare_structure.setCharge_for_dead_mileage(fare.isFare_structure_checkbox_charge_for_dead_mileage());
		operatorDataManager.insertFareStructure(fare_structure);
//		int fare_structure_id = operatorDataManager.getFareStructureByOpId(operator_id).getId();
		int fare_structure_id = fare_structure.getId();
		List<FareMileageBands> mileage_bands = getFareMileageBands(fare_structure_id, fare);
		for(FareMileageBands milage_band: mileage_bands){
			operatorDataManager.insertFareMileageBands(milage_band);
		}
		
	}
	
	public void updateFare(int operator_id, Fare fareInfo){
		
	//	FareStructure fare_structure = new FareStructure();
		FareStructure fare_structure = operatorDataManager.getFareStructureByOpId(operator_id);
		fare_structure.setFare_structure_radioBtns(fareInfo.getTab_fare_structure_radioBtns());
		fare_structure.setHow_charge_radioBtns(fareInfo.getTab_fare_structure_how_charge_radioBtns());
		fare_structure.setReturn_fare_multiplier(fareInfo.getReturn_fare_multiplier());
		fare_structure.setDiscount_for_over60(new Double(fareInfo.getDiscount_for_over60()));
		fare_structure.setDiscount_for_under16(new Double(fareInfo.getDiscount_for_under16()));
		fare_structure.setDiscount_for_other_concessionary(new Double(fareInfo.getDiscount_for_other_concessionary()));
		fare_structure.setHas_escort(fareInfo.isFare_structure_checkbox_escort());
		fare_structure.setCharge_for_dead_mileage(fareInfo.isFare_structure_checkbox_charge_for_dead_mileage());
		operatorDataManager.updateFareStructure(fare_structure);

		int fare_structure_id = fare_structure.getId();
		operatorDataManager.deleteFareMileageBandsByFareStrctId(fare_structure_id);//update was creating duplicates

		List<FareMileageBands> mileage_bands = getFareMileageBands(fare_structure_id, fareInfo);
		for(FareMileageBands milage_band: mileage_bands){
			operatorDataManager.insertFareMileageBands(milage_band);
		}
		
	}
	
	public void insertVehicle(int operator_id, VehicleInfo vehicleInfo){
		
		Vehicle vehicle = new Vehicle();
		vehicle.setOperator_id(operator_id);
		vehicle.setVehicle_type(vehicleInfo.getVehicleType());
		vehicle.setReg_num(vehicleInfo.getRegNum());
		if(vehicleInfo.getVehicleType().equals("other")){
			vehicle.setDescription(vehicleInfo.getOtherVehicle());
		}else{
			vehicle.setDescription(vehicleInfo.getVehicleType());
		}
		operatorDataManager.insertVehicle(vehicle);
	}
	
	public void updateVehicle(int operator_id,VehicleInfo vehicleInfo){
		
		Vehicle vehicle = operatorDataManager.getVehicleByOpId(operator_id);
		if(vehicle!=null)
			operatorDataManager.deleteVehicle(vehicle);

		insertVehicle(operator_id,vehicleInfo);

	}
	
	
	public static void main(String[] args){
		System.out.println(Day.Monday.toString());
	}
	
	public Date getStartOfDay(String dateStr){
		Calendar date = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			date.setTime(dateFormat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		System.out.println("retrieve start of a day "+date.getTime().toGMTString());
		Date sqlDate = new Date(date.getTimeInMillis());
		return sqlDate;
	}
	
	public Date getEndOfDay(String dateStr){
		
		Calendar date = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			date.setTime(dateFormat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 0);
		System.out.println("retrieve end of a day "+date.getTime().toGMTString());
		Date sqlDate = new Date(date.getTimeInMillis());
		return sqlDate;
	}
	
	public Date getStartOfDayAddedPeriod(String dateStr){
		Calendar date = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.UK); 
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			date.setTime(dateFormat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		System.out.println("retrieve start of a day "+date.getTime().toGMTString());
//		Date sqlDate = new Date(date.getTimeInMillis());
		return date.getTime();
	}
	
	public Date getEndOfDayAddedPeriod(String dateStr){
		
		Calendar date = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			date.setTime(dateFormat.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 0);
		System.out.println("retrieve end of a day "+date.getTime().toGMTString());
//		Date sqlDate = new Date(date.getTimeInMillis());
		return date.getTime();
	}
	
	public Time getTime(String timeStr){
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.UK);
		Calendar time = new GregorianCalendar();
		try {
			time.setTime(dateFormat.parse(timeStr));
			time.add(Calendar.HOUR, 1);
		} catch (ParseException e) {
//			e.printStackTrace();
			return null;
		}
		Time t = new Time(time.getTimeInMillis());
		return t;
	}
	
}
