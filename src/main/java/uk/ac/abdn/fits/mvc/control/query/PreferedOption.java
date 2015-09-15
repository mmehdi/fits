package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.HashMap;
import java.util.Map;

public enum PreferedOption {
	
	save_time(1), cheap_fare(2), less_num_of_changes(3);
	
	
	private int value;

    private static Map<Integer, PreferedOption> map = new HashMap<Integer, PreferedOption>();

    static {
        for (PreferedOption option : PreferedOption.values()) {
            map.put(option.value, option);
        }
    }

    private PreferedOption(final int value) { this.value = value; }

    public static PreferedOption valueOf(int value) {
        return map.get(value);
    }
    
    public static String toString(PreferedOption value){
    	if(value == PreferedOption.save_time){
    		return "Minimise travel time";
    	}else if(value == PreferedOption.cheap_fare){
    		return "Minimise fare";
    	}else{
    		return "Minimise number of changes";
    	}
    }
    
    

}
