package uk.ac.abdn.fits.business.delegate;

import uk.ac.abdn.fits.business.bc.BusinessOperaotor;
import uk.ac.abdn.fits.business.bc.DataAccessObject;
import uk.ac.abdn.fits.business.facade.BSessionFacade;
import uk.ac.abdn.fits.query.operator.BABSBusService;
import uk.ac.abdn.fits.query.operator.BABSCarService;
import uk.ac.abdn.fits.query.operator.BanffshirePartnershipDialABus;
import uk.ac.abdn.fits.query.operator.MforMorayBuckieService;
import uk.ac.abdn.fits.query.operator.MforMorayForresService;
import uk.ac.abdn.fits.query.operator.MforMorayKeithService;
import uk.ac.abdn.fits.query.operator.MforMoraySpeysideService;
import uk.ac.abdn.fits.query.operator.Operator;
import uk.ac.abdn.fits.query.operator.TaxiOperator;
import uk.ac.abdn.fits.query.operator.TurriffA2BService;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


public class InitialContext {
	
	
	public InitialContext(){
		
	}
	
	public void initContext(){
		
		FTSBusinessDelegate fts_delegate = FTSBusinessDelegate.getInstance();
		BSessionFacade sessionFacade = null;
		BusinessOperaotor business_operator = null;
		Operator operator = null;
		/* Taxi */ /* should be removed in the evaluation of relaxing operating constraints */
		operator = new TaxiOperator(); 
		business_operator = new BusinessOperaotor(operator);
		sessionFacade = new BSessionFacade(business_operator);
		fts_delegate.addSessionFacade(sessionFacade);
	}

}
