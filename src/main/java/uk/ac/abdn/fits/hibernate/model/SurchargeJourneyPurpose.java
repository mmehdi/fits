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
@Table(name="surcharge_journey_purpose")
public class SurchargeJourneyPurpose {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	  
	@Column(name="health_appointment", nullable = false)
	private boolean health_appointment;
	  
	@Column(name="health_appointment_surcharge", nullable = true)
	private double health_appointment_surcharge;
	  
	@Column(name="social_care", nullable = false)
	private boolean social_care;
	
	@Column(name="social_care_surcharge", nullable = true)
	private double social_care_surcharge;
	
	@Column(name="shopping", nullable = false)
	private boolean shopping;
	
	@Column(name="shopping_surcharge", nullable = true)
	private double shopping_surcharge;
	
	@Column(name="leisure_visiting_friends", nullable = false)
	private boolean leisure_or_visiting_friends;
	
	@Column(name="leisure_visiting_friends_surcharge", nullable = true)
	private double leisure_or_visiting_friends_surcharge;
	
	@Column(name="school_education", nullable = false)
	private boolean school_or_education;
	
	@Column(name="school_education_surcharge", nullable = true)
	private double school_or_education_surcharge;
	
	@Column(name="work_commuting", nullable = false)
	private boolean work_or_commuting;
	
	@Column(name="work_commuting_surcharge", nullable = true)
	private double work_or_commuting_surcharge;
	
	@Column(name="other_purpose", nullable = false)
	private boolean other_purpose;
	
	@Column(name="other_purpose_surcharge", nullable = true)
	private double other_purpose_surcharge;

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

	public boolean isHealth_appointment() {
		return health_appointment;
	}

	public void setHealth_appointment(boolean health_appointment) {
		this.health_appointment = health_appointment;
	}

	public double getHealth_appointment_surcharge() {
		return health_appointment_surcharge;
	}

	public void setHealth_appointment_surcharge(double health_appointment_surcharge) {
		this.health_appointment_surcharge = health_appointment_surcharge;
	}

	public boolean isSocial_care() {
		return social_care;
	}

	public void setSocial_care(boolean social_care) {
		this.social_care = social_care;
	}

	public double getSocial_care_surcharge() {
		return social_care_surcharge;
	}

	public void setSocial_care_surcharge(double social_care_surcharge) {
		this.social_care_surcharge = social_care_surcharge;
	}

	public boolean isShopping() {
		return shopping;
	}

	public void setShopping(boolean shopping) {
		this.shopping = shopping;
	}

	public double getShopping_surcharge() {
		return shopping_surcharge;
	}

	public void setShopping_surcharge(double shopping_surcharge) {
		this.shopping_surcharge = shopping_surcharge;
	}

	public boolean isLeisure_or_visiting_friends() {
		return leisure_or_visiting_friends;
	}

	public void setLeisure_or_visiting_friends(boolean leisure_or_visiting_friends) {
		this.leisure_or_visiting_friends = leisure_or_visiting_friends;
	}

	public double getLeisure_or_visiting_friends_surcharge() {
		return leisure_or_visiting_friends_surcharge;
	}

	public void setLeisure_or_visiting_friends_surcharge(
			double leisure_or_visiting_friends_surcharge) {
		this.leisure_or_visiting_friends_surcharge = leisure_or_visiting_friends_surcharge;
	}

	public boolean isSchool_or_education() {
		return school_or_education;
	}

	public void setSchool_or_education(boolean school_or_education) {
		this.school_or_education = school_or_education;
	}

	public double getSchool_or_education_surcharge() {
		return school_or_education_surcharge;
	}

	public void setSchool_or_education_surcharge(
			double school_or_education_surcharge) {
		this.school_or_education_surcharge = school_or_education_surcharge;
	}

	public boolean isWork_or_commuting() {
		return work_or_commuting;
	}

	public void setWork_or_commuting(boolean work_or_commuting) {
		this.work_or_commuting = work_or_commuting;
	}

	public double getWork_or_commuting_surcharge() {
		return work_or_commuting_surcharge;
	}

	public void setWork_or_commuting_surcharge(double work_or_commuting_surcharge) {
		this.work_or_commuting_surcharge = work_or_commuting_surcharge;
	}

	public boolean isOther_purpose() {
		return other_purpose;
	}

	public void setOther_purpose(boolean other_purpose) {
		this.other_purpose = other_purpose;
	}

	public double getOther_purpose_surcharge() {
		return other_purpose_surcharge;
	}

	public void setOther_purpose_surcharge(double other_purpose_surcharge) {
		this.other_purpose_surcharge = other_purpose_surcharge;
	}
}
