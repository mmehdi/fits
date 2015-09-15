package uk.ac.abdn.fits.business.delegate;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.ac.abdn.fits.business.client.OriginalRequest;
import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.google.GoogleDirectionsWebService;
import uk.ac.abdn.fits.gmap.model.DirectionsResponse;
import uk.ac.abdn.fits.query.operator.PassengerMobilityStatus;


public class GoogleBusinessDelegate implements BusinessDelegate{
	
	public GoogleBusinessDelegate(){
		
	}
	
	public List<TOption> query(QueryCommand query){
		
		
		ArrayList<TOption> options = new ArrayList<TOption>();
		if(query.getOriginalRequest().getMobilityStatus() == PassengerMobilityStatus .Mobility_impaired_unable_to_use_regular_PT){
			return  options;
		}
		TOption option = null;
		DirectionsResponse response = null;
		DirectionsResponse temp_res = null;
		Calendar calendar = Calendar.getInstance();
		OriginalRequest request = query.getOriginalRequest();
		if(request.getDepartureTime() != null){
			calendar.setTimeInMillis(request.getDepartureTime().getTimeInMillis());
		}else{ // then it's arrival_time
			calendar.setTimeInMillis(request.getArriveTime().getTimeInMillis());
		}
		int index = 0;
		for(int i=0; i<5; i++){
			if(index >= 2){
				break;
			}
			if(request.getDepartureTime() != null){
				response = query(request.getOriginAddress(), request.getDestnAddress(), "departure_time", calendar, GoogleDirectionsWebService.TRANSIT_MODE);
				if(i != 0){
					calendar.add(Calendar.MINUTE, 20);
				}
			}else{ // then it's arrival_time
				response = query(request.getOriginAddress(), request.getDestnAddress(), "arrival_time", request.getArriveTime(), GoogleDirectionsWebService.TRANSIT_MODE);
				if(i != 0){
					calendar.add(Calendar.MINUTE, -20);
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(response.getStatus().equals("OK")){
				if(response.isWalkingDir()){
					continue;
				}
				if(temp_res !=null && temp_res.equals(response)){
					temp_res = response;
					continue;
				}else{
					temp_res = response;
					option = new TOption();
					option.setDirectionsResponse(response);
					option.setSourceType("google_transit"); 
					option.setSourceID("google_transit"+index);
					options.add(option);
					index++;
				}
			}
		}
		System.out.println("google transit: " + options.size());
		return options;
	}
	
	private DirectionsResponse query(String origin_address, String destn_address, String time_mode, Calendar c, String mode){
		
		GoogleDirectionsWebService google = GoogleDirectionsWebService.getInstance();
		DirectionsResponse response = google.query(origin_address, destn_address, time_mode, c, mode);
		return response;
	}
	

}
