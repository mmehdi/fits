package uk.ac.abdn.fits.hibernate.dao;


/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.FareMileageBands;


@Transactional(readOnly = false)
public interface FareMileageBandsDAO {
	
	void insertFareMileageBands(FareMileageBands fare_mileage_bands);
	List<FareMileageBands> getFareMileageBandsByFareStrctId(int fare_structure_id);
		
}
