package uk.ac.abdn.fits.mvc.control.query;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.HashMap;
import java.util.Map;

public enum PreferedTypeOfOperator {
	
	patient_transport_service(4), fixed_bus(5), door_to_door_service(6), car_service(7);
	
	
	private int value;

    private static Map<Integer, PreferedTypeOfOperator> map = new HashMap<Integer, PreferedTypeOfOperator>();

    static {
        for (PreferedTypeOfOperator option : PreferedTypeOfOperator.values()) {
            map.put(option.value, option);
        }
    }

    private PreferedTypeOfOperator(final int value) { this.value = value; }

    public static PreferedTypeOfOperator valueOf(int value) {
        return map.get(value);
    }

}
