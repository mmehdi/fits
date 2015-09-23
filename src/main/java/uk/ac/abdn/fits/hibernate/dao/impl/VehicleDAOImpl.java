package uk.ac.abdn.fits.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.VehicleDAO;
import uk.ac.abdn.fits.hibernate.model.Vehicle;

@Service
public class VehicleDAOImpl implements VehicleDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertVehicle(Vehicle vehicle) {
		sessionFactory.getCurrentSession().save(vehicle);
		
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		sessionFactory.getCurrentSession().update(vehicle);
		
	}
	
	@Override
	public Vehicle getVehicleById(int id) {
		 return (Vehicle) sessionFactory.
			      getCurrentSession().
			      get(Vehicle.class, id);
	}

	@Override
	public Vehicle getVehicleByType(String vehType) {
		 Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from VehicleInfo where vehicle_type = :vehicle_type");
		  query.setParameter("vehicle_type", vehType);
		  if(query.list().size() > 0){
			  return (Vehicle)query.list().get(0);
		  }
		  return null;
	}
	
	@Override
	public Vehicle getVehicleByOpId(int OpId) {
		 Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from Vehicle where operator_id = :operator_id");
		  query.setParameter("operator_id", OpId);
		  if(query.list().size() > 0){
			  return (Vehicle)query.list().get(0);
		  }
		  return null;
	}

	@Override
	public List<Vehicle> getVehicles() {
		Criteria criteria = sessionFactory.
			      getCurrentSession().
			      createCriteria(Vehicle.class);
		return criteria.list();
	}

}
