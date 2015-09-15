package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeAgeGroup;


@Transactional(readOnly = false)
public interface SurchargeAgeGroupDAO {
	
	void insertSurchargeAgeGroup(SurchargeAgeGroup age_group);
	SurchargeAgeGroup getSurchargeAgeGroupByOpId(int operatorId);
	List<SurchargeAgeGroup> getSurchargeAgeGroups();
	
}
