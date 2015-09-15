package uk.ac.abdn.fits.business.client;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.List;

import uk.ac.abdn.fits.business.client.option.TOption;

public interface Command {
	
	public List<TOption> execute();

}
