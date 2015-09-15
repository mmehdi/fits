package uk.ac.abdn.fits.business.delegate;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.facade.ReasoningSessionFacade;



public class Reasoner implements ReasoningDelegate{

	private ReasoningSessionFacade reasoningFacade;
	
	public Reasoner(ReasoningSessionFacade reasoningFacade){
		this.reasoningFacade = reasoningFacade;
	}
	
	public Reasoner(){
		reasoningFacade = new ReasoningSessionFacade();
	}
	
	@Override
	public List<TOption> rank(List<TOption> options, QueryCommand query) {
		return reasoningFacade.ranking(options, query);
	}


}
