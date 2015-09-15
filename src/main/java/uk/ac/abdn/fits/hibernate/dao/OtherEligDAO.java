package uk.ac.abdn.fits.hibernate.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.OtherEligTable;

@Transactional(readOnly = false)
public interface OtherEligDAO {
	
	void insertOtherElig(OtherEligTable otherElig);
	OtherEligTable getOtherEligById(int id);
	List<OtherEligTable> getOtherEligByOperatorId(int operatorId);
	List<OtherEligTable> getAllOtherEligTable();
}
