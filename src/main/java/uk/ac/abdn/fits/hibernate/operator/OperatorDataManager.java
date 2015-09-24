package uk.ac.abdn.fits.hibernate.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.sql.Date;
import java.util.List;

import uk.ac.abdn.fits.hibernate.model.*;


public interface OperatorDataManager {
	
	
	
	User getUser(String username);
	void insertOperator(Operator operator);
	void updateOperator(Operator operator);
	void insertServiceNotAvailable(ServiceNotAvailable serviceNotAvailable);
	public List<ServiceNotAvailable> getServiceNotAvailableByOpId(int op_id);
	void updateOperatorLastUpdateTime(int operatorId, Date date);
	Operator getOperatorById(int operatorId);
	Operator getOperator(String name);
	List<Operator> getOperatorByUserId(int userId);
	void insertVehicle(Vehicle vehicle);
	void updateVehicle(Vehicle vehicle);
	Vehicle getVehicleById(int id);
	Vehicle getVehicleByType(String type);
	Vehicle getVehicleByOpId(int opId);
	List<Vehicle> getAllVehicle();
	
	PassengerElig getPassengerElig(int operator_id);
	void insertPassengerElig(PassengerElig elig);
	void updatePassengerElig(PassengerElig elig);
	
	FareStructure getFareStructureByOpId(int operator_id);
	
	SurchargeDistance getSurchargeDistanceByOpId(int operator_id);
	List<Operator> getOperators();
	void insertFareStructure(FareStructure fare);
	void updateFareStructure(FareStructure fare);
	void insertSurchargeDistance(SurchargeDistance surcharge_dist);
	void updateSurchargeDistance(SurchargeDistance surcharge_dist);
	void insertSurchargeAgeGroup(SurchargeAgeGroup age_group);
	void updateSurchargeAgeGroup(SurchargeAgeGroup age_group);
	void insertSurchargeMobilityStatus(SurchargeMobilityStatus mobility_status);
	void updateSurchargeMobilityStatus(SurchargeMobilityStatus mobility_status);
	void insertSurchargeJourneyPurpose(SurchargeJourneyPurpose journey_purpose);
	void updateSurchargeJourneyPurpose(SurchargeJourneyPurpose journey_purpose);
	void insertOperatorOtherElig(OtherEligTable elg);
	void updateOperatorOtherElig(OtherEligTable elg);
	void deleteOperatorOtherEligsByOpID(int opID);
	List<OtherEligTable> getOperatorOtherEligByOpID(int opID);
	List<OtherEligTable> getOperatorOtherEligs();
	void insertSurchargeOperatingTime(SurchargeOperatingTime operating_time);
	void updateSurchargeOperatingTime(SurchargeOperatingTime operating_time);
	void insertOperatingArea(OperatingArea operating_area);
	void updateOperatingArea(OperatingArea operating_area);
	OperatingArea getOperatingAreaByOpId(int operatorId);
	void insertOperationalHours(OperationalHours operational_hours);
	void updateOperationalHours(OperationalHours operational_hours);
	List<OperationalHours> getOperationalHoursByOpId(int operatorId);
	
	void insertFareMileageBands(FareMileageBands fare_mileage_bands);
	void updateFareMileageBands(FareMileageBands fare_mileage_bands);
	List<FareMileageBands> getFareMileageBandsByFareStrctId(int fare_structure_id);
	void insertSurchargeMileageBands(SurchargeMileageBands surcharge_mileage_bands);
	void updateSurchargeMileageBands(SurchargeMileageBands surcharge_mileage_bands);
	void deleteOperatingArea(OperatingArea operating_area);
	void deleteFareMileageBandsByFareStrctId(int fare_structure_id);
	void deleteOperationalHoursByOpId(int operator_id);
	void deleteServiceNotAvailTimeByOpId(int opId);

}
