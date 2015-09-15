package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

public class Elig {
	
	private boolean able_bodied;
	private boolean mobility_prevents_PT;
	private boolean disable_wheelchair_user;
	private boolean disable_other;
	private boolean cant_live_on_a_bus_route;
	private boolean age_under16;
	private boolean age_17to21;
	private boolean age_22to54;
	private boolean age_55to59;
	private boolean age_over60;
	private boolean health_appointment;
	private boolean social_care;
	private boolean shopping;
	private boolean leisure_or_visiting_friends;
	private boolean school_or_education;
	private boolean work_or_commuting;
	private boolean other_purpose;
	
	private String other_elig_type;
	private String other_elig;
	
	private String tab_elig_radioBtns = "0";
	private String explain_opening_up_elig;
	
	
	public boolean isAge_under16() {
		return age_under16;
	}
	public void setAge_under16(boolean age_under16) {
		this.age_under16 = age_under16;
	}
	public boolean isAge_17to21() {
		return age_17to21;
	}
	public void setAge_17to21(boolean age_17to21) {
		this.age_17to21 = age_17to21;
	}
	public boolean isAge_22to54() {
		return age_22to54;
	}
	public void setAge_22to54(boolean age_22to54) {
		this.age_22to54 = age_22to54;
	}
	public boolean isAge_55to59() {
		return age_55to59;
	}
	public void setAge_55to59(boolean age_55to59) {
		this.age_55to59 = age_55to59;
	}
	public boolean isAge_over60() {
		return age_over60;
	}
	public void setAge_over60(boolean age_over60) {
		this.age_over60 = age_over60;
	}
	public boolean isAble_bodied() {
		return able_bodied;
	}
	public void setAble_bodied(boolean able_bodied) {
		this.able_bodied = able_bodied;
	}
	public boolean isMobility_prevents_PT() {
		return mobility_prevents_PT;
	}
	public void setMobility_prevents_PT(boolean mobility_prevents_PT) {
		this.mobility_prevents_PT = mobility_prevents_PT;
	}
	public boolean isDisable_wheelchair_user() {
		return disable_wheelchair_user;
	}
	public void setDisable_wheelchair_user(boolean disable_wheelchair_user) {
		this.disable_wheelchair_user = disable_wheelchair_user;
	}
	public boolean isDisable_other() {
		return disable_other;
	}
	public void setDisable_other(boolean disable_other) {
		this.disable_other = disable_other;
	}
	public boolean isHealth_appointment() {
		return health_appointment;
	}
	public void setHealth_appointment(boolean health_appointment) {
		this.health_appointment = health_appointment;
	}
	public boolean isSocial_care() {
		return social_care;
	}
	public void setSocial_care(boolean social_care) {
		this.social_care = social_care;
	}
	public boolean isShopping() {
		return shopping;
	}
	public void setShopping(boolean shopping) {
		this.shopping = shopping;
	}
	public boolean isLeisure_or_visiting_friends() {
		return leisure_or_visiting_friends;
	}
	public void setLeisure_or_visiting_friends(boolean leisure_or_visiting_friends) {
		this.leisure_or_visiting_friends = leisure_or_visiting_friends;
	}
	public boolean isSchool_or_education() {
		return school_or_education;
	}
	public void setSchool_or_education(boolean school_or_education) {
		this.school_or_education = school_or_education;
	}
	public boolean isWork_or_commuting() {
		return work_or_commuting;
	}
	public void setWork_or_commuting(boolean work_or_commuting) {
		this.work_or_commuting = work_or_commuting;
	}
	public boolean isOther_purpose() {
		return other_purpose;
	}
	public void setOther_purpose(boolean other_purpose) {
		this.other_purpose = other_purpose;
	}
	
	public boolean isCant_live_on_a_bus_route() {
		return cant_live_on_a_bus_route;
	}
	public void setCant_live_on_a_bus_route(boolean cant_live_on_a_bus_route) {
		this.cant_live_on_a_bus_route = cant_live_on_a_bus_route;
	}
	public String getOther_elig() {
		return other_elig;
	}
	public void setOther_elig(String other_elig) {
		this.other_elig = other_elig;
	}
	
	
	public void setFields(OperatorDataInputForm form ){
		setAge_under16(form.getEligible_checkbox_under16()!=null?form.getEligible_checkbox_under16().equals("1"):false);
		setAge_17to21(form.getEligible_checkbox_17_to_21()!=null?form.getEligible_checkbox_17_to_21().equals("2"):false);
		setAge_22to54(form.getEligible_checkbox_22_to_54()!=null?form.getEligible_checkbox_22_to_54().equals("3"):false);
		setAge_55to59(form.getEligible_checkbox_55_to_59()!=null?form.getEligible_checkbox_55_to_59().equals("4"):false);
		setAge_over60(form.getEligible_checkbox_over60()!=null?form.getEligible_checkbox_over60().equals("5"):false);
		setAble_bodied(form.getEligible_checkbox_able_bodied()!=null?form.getEligible_checkbox_able_bodied().equals("6"):false);
		setMobility_prevents_PT(form.getEligible_checkbox_mobility_prevents_PT()!=null?form.getEligible_checkbox_mobility_prevents_PT().equals("7"):false);
		setDisable_wheelchair_user(form.getEligible_checkbox_disable_wheelchair_user()!=null?form.getEligible_checkbox_disable_wheelchair_user().equals("8"):false);
		setDisable_other(form.getEligible_checkbox_disable_other()!=null?form.getEligible_checkbox_disable_other().equals("9"):false);
		setCant_live_on_a_bus_route(form.getEligible_checkbox_cant_live_on_a_bus_route()!=null?form.getEligible_checkbox_cant_live_on_a_bus_route().equals("17"):false);
		
		System.out.println("setCant_live_on_a_bus_route(...) "+ form.getEligible_checkbox_cant_live_on_a_bus_route());
		
		setHealth_appointment(form.getEligible_checkbox_health_appointment()!=null?form.getEligible_checkbox_health_appointment().equals("10"):false);
		setSocial_care(form.getEligible_checkbox_social_care()!=null?form.getEligible_checkbox_social_care().equals("11"):false);
		setShopping(form.getEligible_checkbox_shopping()!=null?form.getEligible_checkbox_shopping().equals("12"):false);
		setLeisure_or_visiting_friends(form.getEligible_checkbox_leisure_or_visiting_friends()!=null?form.getEligible_checkbox_leisure_or_visiting_friends().equals("13"):false);
		setSchool_or_education(form.getEligible_checkbox_school_or_education()!=null?form.getEligible_checkbox_school_or_education().equals("14"):false);
		setWork_or_commuting(form.getEligible_checkbox_work_or_commuting()!=null?form.getEligible_checkbox_work_or_commuting().equals("15"):false);
		setOther_purpose(form.getEligible_checkbox_other_purpose()!=null?form.getEligible_checkbox_other_purpose().equals("16"):false);
		setOther_elig_type(form.getOther_elig_type());
		setOther_elig(form.getOther_elig());
		setTab_elig_radioBtns(form.getTab_elig_radioBtns());
		setExplain_opening_up_elig(form.getExplain_opening_up_elig());
	}
	public String toString(){
		StringBuilder sb = new  StringBuilder();
		sb.append("age_under16: "+ this.isAge_under16()+"\n");
		sb.append("age_17to21: "+ this.isAge_17to21()+"\n");
		sb.append("age_22to54: "+ this.isAge_22to54()+"\n");
		sb.append("age_55to59: "+ this.isAge_55to59()+"\n");
		sb.append("age_over60: "+ this.isAge_over60()+"\n");
		sb.append("able_bodied: "+ this.isAble_bodied()+"\n");
		sb.append("mobility_prevents_PT: "+ this.isMobility_prevents_PT()+"\n");
		sb.append("disable_wheelchair_user: "+ this.isDisable_wheelchair_user()+"\n");
		sb.append("disable_other: "+ this.isDisable_other()+"\n");
		sb.append("health_appointment: "+ this.isHealth_appointment()+"\n");
		sb.append("social_care: "+ this.isSocial_care()+"\n");
		sb.append("shopping: "+ this.isShopping()+"\n");
		sb.append("leisure_or_visiting_friends: "+ this.isLeisure_or_visiting_friends()+"\n");
		sb.append("school_or_education: "+ this.isSchool_or_education()+"\n");
		sb.append("work_or_commuting: "+ this.isAge_under16()+"\n");
		sb.append("other_purpose: "+ this.isAge_under16()+"\n");
		sb.append("cant_live_on_a_bus_route: "+ this.isCant_live_on_a_bus_route()+"\n");
		sb.append("other_elig: "+ this.getOther_elig()+"\n");
		return sb.toString();
	}
	public String getOther_elig_type() {
		return other_elig_type;
	}
	public void setOther_elig_type(String other_elig_type) {
		this.other_elig_type = other_elig_type;
	}
	public String getTab_elig_radioBtns() {
		return tab_elig_radioBtns;
	}
	public void setTab_elig_radioBtns(String tab_elig_radioBtns) {
		this.tab_elig_radioBtns = tab_elig_radioBtns;
	}
	public String getExplain_opening_up_elig() {
		return explain_opening_up_elig;
	}
	public void setExplain_opening_up_elig(String explain_opening_up_elig) {
		this.explain_opening_up_elig = explain_opening_up_elig;
	}
	
	
	

}
