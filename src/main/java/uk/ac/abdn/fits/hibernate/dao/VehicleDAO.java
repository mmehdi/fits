package uk.ac.abdn.fits.hibernate.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.Vehicle;


@Transactional(readOnly = false)
public interface VehicleDAO {
	
	void insertVehicle(Vehicle vehicle);
	Vehicle getVehicleById(int id);
	Vehicle getVehicleByOpId(int OpId);
	Vehicle getVehicleByType(String vehType);
	List<Vehicle> getVehicles();
}
