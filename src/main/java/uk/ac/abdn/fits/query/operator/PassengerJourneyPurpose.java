package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;
import java.util.Map;

public enum PassengerJourneyPurpose {
	
	Health_appointment(0),
	Shopping(1),
	Social_care(2),
	School_or_education(3),
	Work_commuting(4),
	Other(5);
	
	private int value;

    private static Map<Integer, PassengerJourneyPurpose> map = new HashMap<Integer, PassengerJourneyPurpose>();

    static {
        for (PassengerJourneyPurpose type : PassengerJourneyPurpose.values()) {
            map.put(type.value, type);
        }
    }

    private PassengerJourneyPurpose(final int value) { this.value = value; }

    public static PassengerJourneyPurpose valueOf(int value) {
        return map.get(value);
    }
    
    public String toString(PassengerJourneyPurpose purpose){
    	
    	if(purpose == Health_appointment){
    		return "Health Appointment";
    	}else if(purpose == Shopping){
    		return "Shopping";
    	}else if(purpose == Social_care){
    		return "Social/Leisure";
    	}else if(purpose == School_or_education){
    		return "School/Education";
    	}else if(purpose == Work_commuting){
    		return "Work/Commuting";
    	}else{
    		return "Other";
    	}
    }
	
	

}
