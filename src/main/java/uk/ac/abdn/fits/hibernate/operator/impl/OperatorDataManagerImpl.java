package uk.ac.abdn.fits.hibernate.operator.impl;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.dao.*;
import uk.ac.abdn.fits.hibernate.model.*;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;


public class OperatorDataManagerImpl implements OperatorDataManager{

	@Autowired
	private UserDAO userDAO; 
	@Autowired
	private OperatorDAO operatorDAO;
	@Autowired
	private ServiceNotAvailableDAO serviceNotAvailableDAO;
	@Autowired
	private VehicleDAO vehicleDAO;
	@Autowired
	private OperationalHoursDAO OperationalHoursDAO;
	@Autowired
	private OperatingAreaDAO operatingAreaDAO;
	@Autowired
	private FareStructureDAO fareStructureDAO;
	@Autowired
	private PassengerEligDAO passengerEligDAO;
	@Autowired
	private OtherEligDAO otherEligDAO;
	@Autowired
	private SurchargeDistanceDAO surchargeDistanceDAO;
	@Autowired
	private SurchargeAgeGroupDAO surchargeAgeGroupDAO;
	@Autowired
	private SurchargeMobilityStatusDAO surchargeMobilityStatusDAO;
	@Autowired
	private SurchargeJourneyPurposeDAO surchargeJourneyPurposeDAO;
	@Autowired
	private SurchargeOperatingTimeDAO surchargeOperatingTimeDAO;
	@Autowired
	private FareMileageBandsDAO fareMileageBandsDAO;
	@Autowired
	private SurchargeMileageBandsDAO surchargeMileageBandsDAO;
	
	
	@Transactional
	public void insertOperator(Operator operator) {
		operatorDAO.insertOperator(operator);
	}

	@Transactional
	public void updateOperator(Operator operator) {
		operatorDAO.updateOperator(operator);
	}
	
	@Transactional
	public Operator getOperatorById(int operatorId) {
		return operatorDAO.getOperatorById(operatorId);
	}

	@Transactional
	public Operator getOperator(String name) {
		return operatorDAO.getOperator(name);
	}

	@Transactional
	public List<Operator> getOperators() {
		return operatorDAO.getOperators();
	}

	@Override
	public void insertFareStructure(FareStructure fare) {
		fareStructureDAO.insertFareStructure(fare);
	}

	

	@Override
	public void insertSurchargeDistance(SurchargeDistance surcharge_dist) {
		surchargeDistanceDAO.insertSurchargeDistance(surcharge_dist);
	}

	@Override
	public void insertSurchargeAgeGroup(SurchargeAgeGroup age_group) {
		surchargeAgeGroupDAO.insertSurchargeAgeGroup(age_group);
	}

	@Override
	public void insertSurchargeMobilityStatus(
			SurchargeMobilityStatus mobility_status) {
		surchargeMobilityStatusDAO.insertSurchargeMobilityStatus(mobility_status);
	}

	@Override
	public void insertSurchargeJourneyPurpose(
			SurchargeJourneyPurpose journey_purpose) {
		surchargeJourneyPurposeDAO.insertSurchargeJourneyPurpose(journey_purpose);
	}

	@Override
	public void insertSurchargeOperatingTime(
			SurchargeOperatingTime operating_time) {
		surchargeOperatingTimeDAO.insertSurchargeOperatingTime(operating_time);
	}
	
	@Override
	public void insertOperatingArea(OperatingArea operating_area) {
		operatingAreaDAO.insertOperatingArea(operating_area);
	}
	
	
	@Transactional
	public OperatingArea getOperatingAreaByOperatorId(int operatorId) {
		return operatingAreaDAO.getOperatingAreaByOpId(operatorId);
	}
	
	@Override
	public void insertOperationalHours(OperationalHours operational_hours) {
		OperationalHoursDAO.insertOperationalHours(operational_hours);
	}
	
	@Override
	public void insertFareMileageBands(FareMileageBands fare_mileage_bands) {
		fareMileageBandsDAO.insertFareMileageBands(fare_mileage_bands);
		
	}

	
	@Override
	public void insertSurchargeMileageBands(
			SurchargeMileageBands surcharge_mileage_bands) {
		surchargeMileageBandsDAO.insertSurchargeMileageBands(surcharge_mileage_bands);
	}
	

	@Override
	public void updateFareStructure(FareStructure fare) {
		fareStructureDAO.updateFareStructure(fare);
		
	}

	

	@Override
	public void updateSurchargeDistance(SurchargeDistance surcharge_dist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSurchargeAgeGroup(SurchargeAgeGroup age_group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSurchargeMobilityStatus(
			SurchargeMobilityStatus mobility_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSurchargeJourneyPurpose(
			SurchargeJourneyPurpose journey_purpose) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSurchargeOperatingTime(
			SurchargeOperatingTime operating_time) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void updateOperatingArea(OperatingArea operating_area) {
		operatingAreaDAO.updateOperatingArea(operating_area);
		
	}

	@Override
	public void deleteOperatingArea(OperatingArea operating_area) {
		operatingAreaDAO.deleteOperatingArea(operating_area);
	}
	

	@Override
	public void updateOperationalHours(OperationalHours operational_hours) {
		OperationalHoursDAO.updateOperationalHours(operational_hours);
		
	}

	

	@Override
	public void updateFareMileageBands(FareMileageBands fare_mileage_bands) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void updateSurchargeMileageBands(
			SurchargeMileageBands surcharge_mileage_bands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FareStructure getFareStructureByOpId(int operator_id) {
		return fareStructureDAO.getFareStructureByOpId(operator_id);
	}

	@Override
	public SurchargeDistance getSurchargeDistanceByOpId(int operator_id) {
		return surchargeDistanceDAO.getSurchargeDistanceByOpId(operator_id);
	}

	@Transactional
	public OperatingArea getOperatingAreaByOpId(int operatorId) {
		return operatingAreaDAO.getOperatingAreaByOpId(operatorId);
	}

	@Transactional
	public List<OperationalHours> getOperationalHoursByOpId(int operatorId) {
		
		return OperationalHoursDAO.getOperationalHoursByOpId(operatorId);
	}

	@Override
	public void updateOperatorLastUpdateTime(int operatorId, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOperatorOtherElig(OtherEligTable otherElig) {
		otherEligDAO.insertOtherElig(otherElig);
		
	}

	@Override
	public void updateOperatorOtherElig(OtherEligTable elg) {
		otherEligDAO.updateOtherElig(elg);
	}

	@Override
	public void deleteOperatorOtherEligsByOpID(int opID) {
		List<OtherEligTable> otherEligs = getOperatorOtherEligByOpID(opID);
		
		if(otherEligs!=null){
			for(OtherEligTable o: otherEligs)
				otherEligDAO.deleteOtherElig(o);
		}

	}

	@Override
	public List<OtherEligTable> getOperatorOtherEligByOpID(int opID) {
		return otherEligDAO.getOtherEligByOperatorId(opID);
	}

	@Override
	public List<OtherEligTable> getOperatorOtherEligs() {
		return otherEligDAO.getAllOtherEligTable();
	}

	@Override
	public void insertVehicle(Vehicle vehicle) {
		vehicleDAO.insertVehicle(vehicle);
		
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		vehicleDAO.updateVehicle(vehicle);
		
	}
	
	@Override
	public Vehicle getVehicleById(int id) {
		return vehicleDAO.getVehicleById(id);
	}

	@Override
	public Vehicle getVehicleByType(String type) {
		
		return vehicleDAO.getVehicleByType(type);
	}

	@Override
	public List<Vehicle> getAllVehicle() {
		return vehicleDAO.getVehicles();
	}

	@Override
	public PassengerElig getPassengerElig(int operator_id) {
		
		return passengerEligDAO.getPassengerEligByOpId(operator_id);
	}


	@Override
	public void insertPassengerElig(PassengerElig elig) {
		passengerEligDAO.insertPassengerElig(elig);
		
	}
	
	@Override
	public void updatePassengerElig(PassengerElig elig) {
		passengerEligDAO.updatePassengerElig(elig);
	}

	@Override
	public List<FareMileageBands> getFareMileageBandsByFareStrctId(
			int fare_structure_id) {
		return fareMileageBandsDAO.getFareMileageBandsByFareStrctId(fare_structure_id);
	}

	
	@Override
	public void deleteFareMileageBandsByFareStrctId(int fare_structure_id) {
		List<FareMileageBands> fareMlgBands = getFareMileageBandsByFareStrctId(fare_structure_id);
		if(fareMlgBands!=null){
			for(FareMileageBands obj: fareMlgBands)
				fareMileageBandsDAO.deleteFareMileageBands(obj);
		}
	}
	
	@Override
	public Vehicle getVehicleByOpId(int opId) {
		
		return vehicleDAO.getVehicleByOpId(opId);
	}

	
	
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDAO.getUser(username);
	}

	@Override
	public List<Operator> getOperatorByUserId(int userId) {
		// TODO Auto-generated method stub
		return operatorDAO.getOperatorByUserId(userId);
	}

	@Override
	public void insertServiceNotAvailable(
			ServiceNotAvailable serviceNotAvailable) {
		serviceNotAvailableDAO.insertServiceNotAvailable(serviceNotAvailable);
		
	}

	@Override
	public List<ServiceNotAvailable> getServiceNotAvailableByOpId(int op_id) {
		return serviceNotAvailableDAO.getServiceNotAvailableByOpId(op_id);
	}
	
}
