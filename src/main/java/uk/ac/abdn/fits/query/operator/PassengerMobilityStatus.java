package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;
import java.util.Map;

public enum PassengerMobilityStatus {
	
	Able_bodied(0),
	Disabled_wheelchair_user(1),
	Disable_other(2),
	Mobility_impaired_unable_to_use_regular_PT(3),
	Cant_live_on_a_bus_route(4);
	
	private int value;

    private static Map<Integer, PassengerMobilityStatus> map = new HashMap<Integer, PassengerMobilityStatus>();

    static {
        for (PassengerMobilityStatus type : PassengerMobilityStatus.values()) {
            map.put(type.value, type);
        }
    }

    private PassengerMobilityStatus(final int value) { this.value = value; }

    public static PassengerMobilityStatus valueOf(int value) {
        return map.get(value);
    }
    
    public String toString(PassengerMobilityStatus status){
    	
    	if(status == Able_bodied){
    		return "Able bodied";
    	}else if(status == Disabled_wheelchair_user){
    		return "Disabled - wheelchair user";
    	}else if(status == Disable_other){
    		return "Disabled - other";
    	}else{
    		return "Mobility impaired - unable to use regular PT";
    	}
    }
	

}
