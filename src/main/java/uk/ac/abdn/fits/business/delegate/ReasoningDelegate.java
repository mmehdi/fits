package uk.ac.abdn.fits.business.delegate;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;


public interface ReasoningDelegate {
	
	public List<TOption> rank(List<TOption> options, QueryCommand query);

	
}
