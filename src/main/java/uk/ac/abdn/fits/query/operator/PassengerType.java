package uk.ac.abdn.fits.query.operator;

import java.util.HashMap;
import java.util.Map;


public enum PassengerType {
	
	General_public_adult(0), 
	General_public_under_16(1),
	Over_60_free_travel_only_using_concessionary_pass(2),
	Over_60_prepared_to_pay_a_fare(3),
	Disabled_free_travel_only_using_pass(4),
	Disabled_prepared_to_pay_a_fare(5),
	Hospital_GP_surgery_patient_clinical_need_for_ambulance_service_transport(6),
	Hospital_GP_surgery_patient_no_clinical_need_for_ambulance_service_transport(7),
	Hospital_patient_requiring_escort_assistance(8), 
	Fare_paying_relative_of_hospital_patient(9);

	private int value;

    private static Map<Integer, PassengerType> map = new HashMap<Integer, PassengerType>();

    static {
        for (PassengerType type : PassengerType.values()) {
            map.put(type.value, type);
        }
    }

    private PassengerType(final int value) { this.value = value; }

    public static PassengerType valueOf(int value) {
        return map.get(value);
    }
}
