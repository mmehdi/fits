package uk.ac.abdn.fits.mvc.control.operator;

import java.io.Serializable;

public class VehicleInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3282090776785483638L;

	private String vehicleType;
	
	private String regNum;
	
	private String otherVehicle;

	public String getOtherVehicle() {
		return otherVehicle;
	}

	public void setOtherVehicle(String otherVehicle) {
		this.otherVehicle = otherVehicle;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setFields(OperatorDataInputForm form ){
		vehicleType = form.getVehicle_type();
		regNum = form.getRegNum();
		otherVehicle = form.getOther_vehicle();
	}
	
	public String toString(){
		
		return "vehicle type: "+ vehicleType +" regNum: "+ regNum;
	}

}
