package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.PassengerElig;


@Transactional(readOnly = false)
public interface PassengerEligDAO {
	
	void insertPassengerElig(PassengerElig elig);
	PassengerElig getPassengerEligByOpId(int operatorId);
	List<PassengerElig> getPassengerEligs();
	
}
