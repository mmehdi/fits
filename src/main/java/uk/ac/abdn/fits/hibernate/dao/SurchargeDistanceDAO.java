package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeDistance;


@Transactional(readOnly = false)
public interface SurchargeDistanceDAO {
	
	void insertSurchargeDistance(SurchargeDistance surcharge_dist);
	SurchargeDistance getSurchargeDistanceByOpId(int operatorId);
	List<SurchargeDistance> getSurchargeDistances();
}
