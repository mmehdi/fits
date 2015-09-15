package uk.ac.abdn.fits.business.facade;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.bc.JessReasoner;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;


public class ReasoningSessionFacade {
	
	private JessReasoner jess_reasoner;
	
	public ReasoningSessionFacade(JessReasoner jess_reasoner){
		this.jess_reasoner = jess_reasoner;
	}
	
	public ReasoningSessionFacade(){
		jess_reasoner = new JessReasoner();
		
	}
	
	
	public List<TOption> ranking(List<TOption> options, QueryCommand query){
		return jess_reasoner.ranking(options, query);
	}

}
