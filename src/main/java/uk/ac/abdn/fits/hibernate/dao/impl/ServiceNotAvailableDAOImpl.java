package uk.ac.abdn.fits.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.abdn.fits.hibernate.dao.ServiceNotAvailableDAO;
import uk.ac.abdn.fits.hibernate.model.ServiceNotAvailable;

@Service
public class ServiceNotAvailableDAOImpl implements ServiceNotAvailableDAO{

	

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertServiceNotAvailable(
			ServiceNotAvailable serviceNotAvailable) {
		
		sessionFactory.getCurrentSession().save(serviceNotAvailable);
	}

	@Override
	public List<ServiceNotAvailable> getServiceNotAvailableByOpId(int operator_id) {
		
		Query query = sessionFactory.
			      getCurrentSession().
	      createQuery("from ServiceNotAvailable where operator_id = :operator_id");
		  query.setParameter("operator_id", operator_id);
		  if(query.list().size() > 0){
			  return query.list();
		  }
		  return null;
	}

}
