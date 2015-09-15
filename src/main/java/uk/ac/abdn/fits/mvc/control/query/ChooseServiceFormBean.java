package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.hibernate.validator.constraints.NotEmpty;

public class ChooseServiceFormBean {
	
	@NotEmpty(message="Please select a service!")
	private String service;
 
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
