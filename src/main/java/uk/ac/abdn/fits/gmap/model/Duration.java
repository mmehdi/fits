package uk.ac.abdn.fits.gmap.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Duration extends TextValuePair{

	
	public int getValue(double penalty){
		
		double time = new Double(getValue())*penalty;
		
		return (int)time;
	}

}
