package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.FareStructure;


@Transactional(readOnly = false)
public interface FareStructureDAO {
	
	void insertFareStructure(FareStructure fare);
	FareStructure getFareStructureByOpId(int operator_id);
	List<FareStructure> getFareStructures();
	
}
