package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;
import java.util.Map;

public enum PassengerAge {
	
	under_16(0), 
	from_16_to_21(1),
	from_22_to_54(2),
	from_55_to_59(3),
	over_60(4);
	
	private int value;

    private static Map<Integer, PassengerAge> map = new HashMap<Integer, PassengerAge>();

    static {
        for (PassengerAge type : PassengerAge.values()) {
            map.put(type.value, type);
        }
    }

    private PassengerAge(final int value) { this.value = value; }

    public static PassengerAge valueOf(int value) {
        return map.get(value);
    }
    
    public String toString(PassengerAge age){
    	if(age == under_16){
    		return "under 16";
    	}else if(age == from_16_to_21){
    		return "16 to 21";
    	}else if(age == from_22_to_54){
    		return "22 to 54";
    	}else if(age == from_55_to_59){
    		return "55 to 59";
    	}else{
    		return "60 and above";
    	}
    }
   
}
