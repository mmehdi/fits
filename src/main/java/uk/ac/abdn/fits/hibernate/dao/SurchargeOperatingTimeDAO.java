package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeOperatingTime;


@Transactional(readOnly = false)
public interface SurchargeOperatingTimeDAO {

	void insertSurchargeOperatingTime(SurchargeOperatingTime operating_time);
	SurchargeOperatingTime getSurchargeOperatingTimeByOpId(int operatorId);
	List<SurchargeOperatingTime> getSurchargeOperatingTimes();
	
}
