package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.OperationalHours;



@Transactional(readOnly = false)
public interface OperationalHoursDAO {
	
	void insertOperationalHours(OperationalHours operational_hours);
	List<OperationalHours> getOperationalHoursByOpId(int operator_id);
		
}
