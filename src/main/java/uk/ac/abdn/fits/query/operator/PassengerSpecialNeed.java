package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;
import java.util.Map;

public enum PassengerSpecialNeed {
	
	Clinical_need_for_ambulance_service_transport(0),
	Need_for_an_escort(1);
	
	private int value;

    private static Map<Integer, PassengerSpecialNeed> map = new HashMap<Integer, PassengerSpecialNeed>();

    static {
        for (PassengerSpecialNeed type : PassengerSpecialNeed.values()) {
            map.put(type.value, type);
        }
    }

    private PassengerSpecialNeed(final int value) { this.value = value; }

    public static PassengerSpecialNeed valueOf(int value) {
        return map.get(value);
    }
	

}
