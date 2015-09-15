package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeMobilityStatus;


@Transactional(readOnly = false)
public interface SurchargeMobilityStatusDAO {
	
	void insertSurchargeMobilityStatus(SurchargeMobilityStatus mobility_status);
	SurchargeMobilityStatus getSurchargeMobilityStatusByOpId(int operatorId);
	List<SurchargeMobilityStatus> getSurchargeMobilityStatuss();
	
}
