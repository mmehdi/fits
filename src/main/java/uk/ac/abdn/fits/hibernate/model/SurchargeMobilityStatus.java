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
@Table(name="surcharge_mobility_status")
public class SurchargeMobilityStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	  
	@Column(name="able_bodied", nullable = false)
	private boolean able_bodied;
	  
	@Column(name="able_bodied_surcharge", nullable = true)
	private double able_bodied_surcharge;
	
	@Column(name="mobility_prevents_use_of_PT", nullable = false)
	private boolean mobility_prevents_use_of_PT;
	
	@Column(name="mobility_prevents_use_of_PT_surcharge", nullable = true)
	private double mobility_prevents_use_of_PT_surcharge;
	
	@Column(name="disable_wheelchair_user", nullable = false)
	private boolean disable_wheelchair_user;
	
	@Column(name="disable_wheelchair_user_surcharge", nullable = true)
	private double disable_wheelchair_user_surcharge;
	
	@Column(name="disable_other", nullable = false)
	private boolean disable_other;
	
	@Column(name="disable_other_surcharge", nullable = true)
	private double disable_other_surcharge;
	
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

	public boolean isAble_bodied() {
		return able_bodied;
	}

	public void setAble_bodied(boolean able_bodied) {
		this.able_bodied = able_bodied;
	}

	public double getAble_bodied_surcharge() {
		return able_bodied_surcharge;
	}

	public void setAble_bodied_surcharge(double able_bodied_surcharge) {
		this.able_bodied_surcharge = able_bodied_surcharge;
	}

	public boolean isMobility_prevents_PT() {
		return mobility_prevents_use_of_PT;
	}

	public void setMobility_prevents_PT(boolean mobility_prevents_PT) {
		this.mobility_prevents_use_of_PT = mobility_prevents_PT;
	}

	public double getMobility_prevents_PT_surcharge() {
		return mobility_prevents_use_of_PT_surcharge;
	}

	public void setMobility_prevents_PT_surcharge(
			double mobility_prevents_PT_surcharge) {
		this.mobility_prevents_use_of_PT_surcharge = mobility_prevents_PT_surcharge;
	}

	public boolean isDiable_wheelchair_user() {
		return disable_wheelchair_user;
	}

	public void setDiable_wheelchair_user(boolean diable_wheelchair_user) {
		this.disable_wheelchair_user = diable_wheelchair_user;
	}

	public double getDiable_wheelchair_user_surcharge() {
		return disable_wheelchair_user_surcharge;
	}

	public void setDiable_wheelchair_user_surcharge(
			double diable_wheelchair_user_surcharge) {
		this.disable_wheelchair_user_surcharge = diable_wheelchair_user_surcharge;
	}

	public boolean isDiable_other() {
		return disable_other;
	}

	public void setDiable_other(boolean diable_other) {
		this.disable_other = diable_other;
	}

	public double getDiable_other_surcharge() {
		return disable_other_surcharge;
	}

	public void setDiable_other_surcharge(double diable_other_surcharge) {
		this.disable_other_surcharge = diable_other_surcharge;
	}

	

}
