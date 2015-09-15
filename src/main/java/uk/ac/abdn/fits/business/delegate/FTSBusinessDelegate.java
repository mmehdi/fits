package uk.ac.abdn.fits.business.delegate;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.ac.abdn.fits.business.bc.DataAccessObject;
import uk.ac.abdn.fits.business.client.OriginalRequest;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.facade.BSessionFacade;
import uk.ac.abdn.fits.business.google.GoogleDirectionsWebService;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;


public class FTSBusinessDelegate implements BusinessDelegate{

	
	private static FTSBusinessDelegate instance;
	private ArrayList<BSessionFacade> fts;
	private DataAccessObject dataAccessObject;
	private ServiceLocator service_locator;
	
	private FTSBusinessDelegate(){
		fts = new ArrayList<BSessionFacade>();
		dataAccessObject = new DataAccessObject();
		service_locator = ServiceLocator.getInstance();
	}
	
	public static FTSBusinessDelegate getInstance(){
		
		if(instance != null){
			return instance;
		}
		instance = new FTSBusinessDelegate();
		return instance;
	}
	
	public void addSessionFacade(BSessionFacade sessionFacade){
		fts.add(sessionFacade);
	}
	
	@Override
	public List<TOption> query(QueryCommand query) {
		ArrayList<TOption> options = new ArrayList<TOption>();
		DirectionsResponse dir_res = getDirectionsResponse(query);
		query.setDirectionsResponse(dir_res);
		/* DB search */ 
		options.addAll(dataAccessObject.query(query));
		for(BSessionFacade sf: fts){
			options.addAll(sf.query(query));
		}
		return options;
	}

	private DirectionsResponse getDirectionsResponse(QueryCommand query){
		
		GoogleDirectionsWebService google = GoogleDirectionsWebService.getInstance();
		OriginalRequest request = query.getOriginalRequest();
		DirectionsResponse response = null;
		if(request.getDepartureTime() != null){
			response = google.query(request.getOriginAddress(), request.getDestnAddress(), "departure_time", request.getDepartureTime(), GoogleDirectionsWebService.DRIVING_MODE);
		}else{ // then it's arrival_time
			response = google.query(request.getOriginAddress(), request.getDestnAddress(), "arrival_time", request.getArriveTime(), GoogleDirectionsWebService.DRIVING_MODE);
		}
		return response;
	}
	
}
