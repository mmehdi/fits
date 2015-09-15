package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.Serializable;


public class GeneralInfo implements Serializable {
 

	private static final long serialVersionUID = 3477158118108639967L;
	
	private String name;
  	private String description;
  	private String type_of_permit;
	private String how2book;
  	private boolean cb_not_avail;
	private String not_valid_from;
  	private String not_valid_to;
  	private String added_service_not_avail;
	//  	private ArrayList<OperationalHours> operationalHours;
  	private String oprHouMon1;
  	private String oprHouMon2;
  	private String oprHouMon3;
  	private String oprHouMon4;
  	private String oprHouTue1;
	private String oprHouTue2;
	private String oprHouTue3;
	private String oprHouTue4;
	private String oprHouWed1;
	private String oprHouWed2;
	private String oprHouWed3;
	private String oprHouWed4;
	private String oprHouThu1;
	private String oprHouThu2;
	private String oprHouThu3;
	private String oprHouThu4;
	private String oprHouFri1;
	private String oprHouFri2;
	private String oprHouFri3;
	private String oprHouFri4;
	private String oprHouSat1;
	private String oprHouSat2;
	private String oprHouSat3;
	private String oprHouSat4;
	private String oprHouSun1;
	private String oprHouSun2;
	private String oprHouSun3;
	private String oprHouSun4;
//	private String jsonData;
//	private String kmlData;
	
  	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType_of_permit() {
		return type_of_permit;
	}
	public void setType_of_permit(String type_of_permit) {
		this.type_of_permit = type_of_permit;
	}
	public String getNot_valid_from() {
		return not_valid_from;
	}
	public void setNot_valid_from(String not_valid_from) {
		this.not_valid_from = not_valid_from;
	}
	public String getNot_valid_to() {
		return not_valid_to;
	}
	public void setNot_valid_to(String not_valid_to) {
		this.not_valid_to = not_valid_to;
	}
	public String getHow2book() {
		return how2book;
	}
	public void setHow2book(String how2book) {
		this.how2book = how2book;
	}
	public String getOprHouMon1() {
		return oprHouMon1;
	}
	public void setOprHouMon1(String oprHouMon1) {
		this.oprHouMon1 = oprHouMon1;
	}
	public String getOprHouMon2() {
		return oprHouMon2;
	}
	public void setOprHouMon2(String oprHouMon2) {
		this.oprHouMon2 = oprHouMon2;
	}
	public String getOprHouMon3() {
		return oprHouMon3;
	}
	public void setOprHouMon3(String oprHouMon3) {
		this.oprHouMon3 = oprHouMon3;
	}
	public String getOprHouMon4() {
		return oprHouMon4;
	}
	public void setOprHouMon4(String oprHouMon4) {
		this.oprHouMon4 = oprHouMon4;
	}
	public String getOprHouTue1() {
		return oprHouTue1;
	}
	public void setOprHouTue1(String oprHouTue1) {
		this.oprHouTue1 = oprHouTue1;
	}
	public String getOprHouTue2() {
		return oprHouTue2;
	}
	public void setOprHouTue2(String oprHouTue2) {
		this.oprHouTue2 = oprHouTue2;
	}
	public String getOprHouTue3() {
		return oprHouTue3;
	}
	public void setOprHouTue3(String oprHouTue3) {
		this.oprHouTue3 = oprHouTue3;
	}
	public String getOprHouTue4() {
		return oprHouTue4;
	}
	public void setOprHouTue4(String oprHouTue4) {
		this.oprHouTue4 = oprHouTue4;
	}
	public String getOprHouWed1() {
		return oprHouWed1;
	}
	public void setOprHouWed1(String oprHouWed1) {
		this.oprHouWed1 = oprHouWed1;
	}
	public String getOprHouWed2() {
		return oprHouWed2;
	}
	public void setOprHouWed2(String oprHouWed2) {
		this.oprHouWed2 = oprHouWed2;
	}
	public String getOprHouWed3() {
		return oprHouWed3;
	}
	public void setOprHouWed3(String oprHouWed3) {
		this.oprHouWed3 = oprHouWed3;
	}
	public String getOprHouWed4() {
		return oprHouWed4;
	}
	public void setOprHouWed4(String oprHouWed4) {
		this.oprHouWed4 = oprHouWed4;
	}
	public String getOprHouThu1() {
		return oprHouThu1;
	}
	public void setOprHouThu1(String oprHouThu1) {
		this.oprHouThu1 = oprHouThu1;
	}
	public String getOprHouThu2() {
		return oprHouThu2;
	}
	public void setOprHouThu2(String oprHouThu2) {
		this.oprHouThu2 = oprHouThu2;
	}
	public String getOprHouThu3() {
		return oprHouThu3;
	}
	public void setOprHouThu3(String oprHouThu3) {
		this.oprHouThu3 = oprHouThu3;
	}
	public String getOprHouThu4() {
		return oprHouThu4;
	}
	public void setOprHouThu4(String oprHouThu4) {
		this.oprHouThu4 = oprHouThu4;
	}
	public String getOprHouFri1() {
		return oprHouFri1;
	}
	public void setOprHouFri1(String oprHouFri1) {
		this.oprHouFri1 = oprHouFri1;
	}
	public String getOprHouFri2() {
		return oprHouFri2;
	}
	public void setOprHouFri2(String oprHouFri2) {
		this.oprHouFri2 = oprHouFri2;
	}
	public String getOprHouFri3() {
		return oprHouFri3;
	}
	public void setOprHouFri3(String oprHouFri3) {
		this.oprHouFri3 = oprHouFri3;
	}
	public String getOprHouFri4() {
		return oprHouFri4;
	}
	public void setOprHouFri4(String oprHouFri4) {
		this.oprHouFri4 = oprHouFri4;
	}
	public String getOprHouSat1() {
		return oprHouSat1;
	}
	public void setOprHouSat1(String oprHouSat1) {
		this.oprHouSat1 = oprHouSat1;
	}
	public String getOprHouSat2() {
		return oprHouSat2;
	}
	public void setOprHouSat2(String oprHouSat2) {
		this.oprHouSat2 = oprHouSat2;
	}
	public String getOprHouSat3() {
		return oprHouSat3;
	}
	public void setOprHouSat3(String oprHouSat3) {
		this.oprHouSat3 = oprHouSat3;
	}
	public String getOprHouSat4() {
		return oprHouSat4;
	}
	public void setOprHouSat4(String oprHouSat4) {
		this.oprHouSat4 = oprHouSat4;
	}
	public String getOprHouSun1() {
		return oprHouSun1;
	}
	public void setOprHouSun1(String oprHouSun1) {
		this.oprHouSun1 = oprHouSun1;
	}
	public String getOprHouSun2() {
		return oprHouSun2;
	}
	public void setOprHouSun2(String oprHouSun2) {
		this.oprHouSun2 = oprHouSun2;
	}
	public String getOprHouSun3() {
		return oprHouSun3;
	}
	public void setOprHouSun3(String oprHouSun3) {
		this.oprHouSun3 = oprHouSun3;
	}
	public String getOprHouSun4() {
		return oprHouSun4;
	}
	public void setOprHouSun4(String oprHouSun4) {
		this.oprHouSun4 = oprHouSun4;
	}
	public String getAdded_service_not_avail() {
		return added_service_not_avail;
	}
	public void setAdded_service_not_avail(String added_service_not_avail) {
		this.added_service_not_avail = added_service_not_avail;
	}
	
//	public String getJsonData() {
//		return jsonData;
//	}
//	public void setJsonData(String jsonData) {
//		this.jsonData = jsonData;
//	}
//	public String getKmlData() {
//		return kmlData;
//	}
//	public void setKmlData(String kmlData) {
//		this.kmlData = kmlData;
//	}

 
	public void setFields(OperatorDataInputForm form){
		this.setName(form.getService_name());
		this.setDescription(form.getService_description());
		this.setHow2book(form.getHow_to_book());
		this.setCb_not_avail(form.isCb_not_avail());
		this.setNot_valid_from(form.getNot_valid_from());
		this.setNot_valid_to(form.getNot_valid_to());
		this.setAdded_service_not_avail(form.getAdded_service_not_avail());
		this.setOprHouMon1(form.getTab_operating_hours_field_monday_1());
		this.setOprHouMon2(form.getTab_operating_hours_field_monday_2());
		this.setOprHouMon3(form.getTab_operating_hours_field_monday_3());
		this.setOprHouMon4(form.getTab_operating_hours_field_monday_4());
		this.setOprHouTue1(form.getTab_operating_hours_field_tuesday_1());
		this.setOprHouTue2(form.getTab_operating_hours_field_tuesday_2());
		this.setOprHouTue3(form.getTab_operating_hours_field_tuesday_3());
		this.setOprHouTue4(form.getTab_operating_hours_field_tuesday_4());
		this.setOprHouWed1(form.getTab_operating_hours_field_wednesday_1());
		this.setOprHouWed2(form.getTab_operating_hours_field_wednesday_2());
		this.setOprHouWed3(form.getTab_operating_hours_field_wednesday_3());
		this.setOprHouWed4(form.getTab_operating_hours_field_wednesday_4());
		this.setOprHouThu1(form.getTab_operating_hours_field_thursday_1());
		this.setOprHouThu2(form.getTab_operating_hours_field_thursday_2());
		this.setOprHouThu3(form.getTab_operating_hours_field_thursday_3());
		this.setOprHouThu4(form.getTab_operating_hours_field_thursday_4());
		this.setOprHouFri1(form.getTab_operating_hours_field_friday_1());
		this.setOprHouFri2(form.getTab_operating_hours_field_friday_2());
		this.setOprHouFri3(form.getTab_operating_hours_field_friday_3());
		this.setOprHouFri4(form.getTab_operating_hours_field_friday_4());
		this.setOprHouSat1(form.getTab_operating_hours_field_saturday_1());
		this.setOprHouSat2(form.getTab_operating_hours_field_saturday_2());
		this.setOprHouSat3(form.getTab_operating_hours_field_saturday_3());
		this.setOprHouSat4(form.getTab_operating_hours_field_saturday_4());
		this.setOprHouSun1(form.getTab_operating_hours_field_sunday_1());
		this.setOprHouSun2(form.getTab_operating_hours_field_sunday_2());
		this.setOprHouSun3(form.getTab_operating_hours_field_sunday_3());
		this.setOprHouSun4(form.getTab_operating_hours_field_sunday_4());
		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("name: ");
		sb.append(name);
		sb.append(" des: ");
		sb.append(description);
		sb.append(" How to Book: ");
		sb.append(how2book);
		sb.append(" inactive: ");
		sb.append(cb_not_avail);
		if(not_valid_from != null){
		    sb.append(" inactiveFrom ");
			sb.append(not_valid_from);
		}
		if(not_valid_to != null){
		    sb.append(" inactiveTo ");
			sb.append(not_valid_to);
		}
		sb.append(" Monday ");
		sb.append(oprHouMon1);
		sb.append("-");
		sb.append(oprHouMon2);
		sb.append(" ");
		sb.append(oprHouMon3);
		sb.append("-");
		sb.append(oprHouMon4);
		sb.append(" ");
		sb.append(" Tuesday ");
		sb.append(oprHouTue1);
		sb.append("-");
		sb.append(oprHouTue2);
		sb.append(" ");
		sb.append(oprHouTue3);
		sb.append("-");
		sb.append(oprHouTue4);
		sb.append(" ");
		sb.append(" Wednesday ");
		sb.append(oprHouWed1);
		sb.append("-");
		sb.append(oprHouWed2);
		sb.append(" ");
		sb.append(oprHouWed3);
		sb.append("-");
		sb.append(oprHouWed4);
		sb.append(" ");
		sb.append(" Thursday ");
		sb.append(oprHouThu1);
		sb.append("-");
		sb.append(oprHouThu2);
		sb.append(" ");
		sb.append(oprHouThu3);
		sb.append("-");
		sb.append(oprHouThu4);
		sb.append(" ");
		sb.append(" Friday ");
		sb.append(oprHouFri1);
		sb.append("-");
		sb.append(oprHouFri2);
		sb.append(" ");
		sb.append(oprHouFri3);
		sb.append("-");
		sb.append(oprHouFri4);
		sb.append(" ");
		sb.append(" Saturday ");
		sb.append(oprHouSat1);
		sb.append("-");
		sb.append(oprHouSat2);
		sb.append(" ");
		sb.append(oprHouSat3);
		sb.append("-");
		sb.append(oprHouSat4);
		sb.append(" ");
		sb.append(" Sunday ");
		sb.append(oprHouSun1);
		sb.append("-");
		sb.append(oprHouSun2);
		sb.append(" ");
		sb.append(oprHouSun3);
		sb.append("-");
		sb.append(oprHouSun4);
		sb.append(" \n");
//		sb.append("json string: \n"+ jsonData +"\n kml string:\n"+ kmlData);
	    return sb.toString();
	}
	public boolean getCb_not_avail() {
		return cb_not_avail;
	}
	public void setCb_not_avail(boolean cb_not_avail) {
		this.cb_not_avail = cb_not_avail;
	}
 
}