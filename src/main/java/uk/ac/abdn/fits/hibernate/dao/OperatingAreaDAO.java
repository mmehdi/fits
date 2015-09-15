package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.OperatingArea;


@Transactional(readOnly = false)
public interface OperatingAreaDAO {
	
	void insertOperatingArea(OperatingArea operating_area);
	OperatingArea getOperatingAreaByOpId(int operator_id);
		
}
