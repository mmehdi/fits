package uk.ac.abdn.fits.mvc.control.operator;

import java.io.Serializable;

public class OperatingAreaInfo implements Serializable{
	
	private static final long serialVersionUID = 1577632228240420464L;
	private int operatorId;
	private String jsonData;
	private String kmlData;
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getKmlData() {
		return kmlData;
	}
	public void setKmlData(String kmlData) {
		this.kmlData = kmlData;
	}

	public String toString(){
		
		return "json string: \n"+ jsonData +"\n kml string:\n"+ kmlData;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
	public void setFields(OperatorDataInputForm form){
		this.setJsonData(form.getJsonData());
		this.setKmlData(form.getKmlData());
	}
	
}
