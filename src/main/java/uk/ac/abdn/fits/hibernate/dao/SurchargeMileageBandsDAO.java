package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeMileageBands;


@Transactional(readOnly = false)
public interface SurchargeMileageBandsDAO {
	
	void insertSurchargeMileageBands(SurchargeMileageBands surcharge_mileage_bands);
	List<SurchargeMileageBands> getSurchargeMileageBandsByOpId(int operator_id);

}
