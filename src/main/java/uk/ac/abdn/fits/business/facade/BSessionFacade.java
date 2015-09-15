package uk.ac.abdn.fits.business.facade;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.bc.BusinessOperaotor;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;



public class BSessionFacade {
	
	private BusinessOperaotor business_operator;
	
	public BSessionFacade(BusinessOperaotor business_operator){
		this.business_operator = business_operator;
	}
	
	public List<TOption> query(QueryCommand query){
		return business_operator.query(query);
	}
	

}
