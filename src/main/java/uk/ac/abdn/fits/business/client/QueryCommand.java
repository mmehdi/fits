package uk.ac.abdn.fits.business.client;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Date;
import java.util.List;

import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.ws.FITSWebServiceBroker;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;


public class QueryCommand implements Command{

	private int state;
	private FITSWebServiceBroker application;
	private OriginalRequest request;
	private DirectionsResponse directionsResponse;
	
	
	public QueryCommand(FITSWebServiceBroker application, OriginalRequest request){
		state = 0;
		this.application = application;
		this.request = request;
		directionsResponse = null;
	}
	
	@Override
	public List<TOption> execute(){
		
		return null;
	}

	public DirectionsResponse getDirectionsResponse(){
		return directionsResponse;
	}
	
	public void setDirectionsResponse(DirectionsResponse dr){
		this.directionsResponse = dr;
	}
	
	public OriginalRequest getOriginalRequest(){
		return request;
	}
	
	public void setOriginalRequest(OriginalRequest request){
		this.request = request;
	}
	
}
