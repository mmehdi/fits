package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.SurchargeJourneyPurpose;


@Transactional(readOnly = false)
public interface SurchargeJourneyPurposeDAO {
	
	void insertSurchargeJourneyPurpose(SurchargeJourneyPurpose journey_purpose);
	SurchargeJourneyPurpose getSurchargeJourneyPurposeByOpId(int operatorId);
	List<SurchargeJourneyPurpose> getSurchargeJourneyPurposes();
}
