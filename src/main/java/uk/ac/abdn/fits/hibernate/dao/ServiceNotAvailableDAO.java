package uk.ac.abdn.fits.hibernate.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.ServiceNotAvailable;

@Transactional(readOnly = false)
public interface ServiceNotAvailableDAO {
	
	void insertServiceNotAvailable(ServiceNotAvailable serviceNotAvailable);
	List<ServiceNotAvailable> getServiceNotAvailableByOpId(int op_id);
	
}
