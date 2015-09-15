package uk.ac.abdn.fits.hibernate.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="passenger_elig")
public class PassengerElig {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	
	@Column(name="user_id", nullable = true)
	private int user_id;
	
	@Column(name="age_under16", nullable = false)
	private boolean age_under16;
	
	@Column(name="age_17to21", nullable = false)
	private boolean age_17to21;
	
	@Column(name="age_22to54", nullable = false)
	private boolean age_22to54;
	
	@Column(name="age_55to59", nullable = false)
	private boolean age_55to59;
	
	@Column(name="age_over60", nullable = false)
	private boolean age_over60;
	
	@Column(name="able_bodied", nullable = false)
	private boolean able_bodied;
	
	@Column(name="mobility_prevents_PT", nullable = false)
	private boolean mobility_prevents_PT;
	
	@Column(name="disable_wheelchair_user", nullable = false)
	private boolean diable_wheelchair_user;
	
	@Column(name="disable_other", nullable = false)
	private boolean diable_other;
	
	@Column(name="cant_live_on_a_bus_route", nullable = false)
	private boolean cant_live_on_a_bus_route;
	
	@Column(name="health_appointment", nullable = false)
	private boolean health_appointment;
	
	@Column(name="social_care", nullable = false)
	private boolean social_care;
	
	@Column(name="shopping", nullable = false)
	private boolean shopping;
	
	@Column(name="leisure_or_visiting_friends", nullable = false)
	private boolean leisure_or_visiting_friends;
	
	@Column(name="school_or_education", nullable = false)
	private boolean school_or_education;
	
	@Column(name="work_or_commuting", nullable = false)
	private boolean work_or_commuting;
	
	@Column(name="other_purpose", nullable = false)
	private boolean other_purpose;
	
	@Column(name="elig_radioBtns", nullable = true)
	private String elig_radioBtns;
	
	@Column(name="explain_opening_up_elig", nullable = true)
	private String explain_opening_up_elig;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
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
	public boolean isDiable_wheelchair_user() {
		return diable_wheelchair_user;
	}
	public void setDiable_wheelchair_user(boolean diable_wheelchair_user) {
		this.diable_wheelchair_user = diable_wheelchair_user;
	}
	public boolean isDiable_other() {
		return diable_other;
	}
	public void setDiable_other(boolean diable_other) {
		this.diable_other = diable_other;
	}
	public boolean isCant_live_on_a_bus_route() {
		return cant_live_on_a_bus_route;
	}
	public void setCant_live_on_a_bus_route(boolean cant_live_on_a_bus_route) {
		this.cant_live_on_a_bus_route = cant_live_on_a_bus_route;
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
	public String getElig_radioBtns() {
		return elig_radioBtns;
	}
	public void setElig_radioBtns(String elig_radioBtns) {
		this.elig_radioBtns = elig_radioBtns;
	}
	public String getExplain_opening_up_elig() {
		return explain_opening_up_elig;
	}
	public void setExplain_opening_up_elig(String explain_opening_up_elig) {
		this.explain_opening_up_elig = explain_opening_up_elig;
	}
	
}
