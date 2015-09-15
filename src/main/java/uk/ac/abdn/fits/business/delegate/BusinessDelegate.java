package uk.ac.abdn.fits.business.delegate;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;


public interface BusinessDelegate {
	
	public List<TOption> query(QueryCommand query);

}
