package uk.ac.abdn.fits.hibernate.dao;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.ac.abdn.fits.hibernate.model.Operator;


@Transactional(readOnly = false)
public interface OperatorDAO {
	
	void insertOperator(Operator operator);
	void updateOperator(Operator operator);
	Operator getOperatorById(int operatorId);
	Operator getOperator(String name);
	List<Operator> getOperators();
	List<Operator> getOperatorByUserId(int userId);
}
