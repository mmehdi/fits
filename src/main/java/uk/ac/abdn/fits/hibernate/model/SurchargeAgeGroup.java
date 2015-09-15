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
@Table(name="surcharge_age_group")
public class SurchargeAgeGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;

	@Column(name="operator_id", nullable = false)
	private int operator_id;
	 
	@Column(name="under16", nullable = false)
	private boolean under16;
	 
	@Column(name="under16_surcharge", nullable = true)
	private double under16_surcharge;
	
	@Column(name="_17to21", nullable = false)
	private boolean _17to21;
	
	@Column(name="_17to21_surcharge", nullable = true)
	private double _17to21_surcharge;
	
	@Column(name="_22to54", nullable = false)
	private boolean _22to54;
	
	@Column(name="_22to54_surcharge", nullable = true)
	private double _22to54_surcharge;
	
	@Column(name="_55to59", nullable = false)
	private boolean _55to59;
	
	@Column(name="_55to59_surcharge", nullable = true)
	private double _55to59_surcharge;
	
	@Column(name="over60", nullable = false)
	private boolean over60;
	
	@Column(name="over60_surcharge", nullable = true)
	private double over60_surcharge;

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
	public boolean isUnder16() {
		return under16;
	}
	public void setUnder16(boolean under16) {
		this.under16 = under16;
	}
	public double getUnder16_surcharge() {
		return under16_surcharge;
	}
	public void setUnder16_surcharge(double under16_surcharge) {
		this.under16_surcharge = under16_surcharge;
	}
	public boolean is_17to21() {
		return _17to21;
	}
	public void set_17to21(boolean _17to21) {
		this._17to21 = _17to21;
	}
	public double get_17to21_surcharge() {
		return _17to21_surcharge;
	}
	public void set_17to21_surcharge(double _17to21_surcharge) {
		this._17to21_surcharge = _17to21_surcharge;
	}
	public boolean is_22to54() {
		return _22to54;
	}
	public void set_22to54(boolean _22to54) {
		this._22to54 = _22to54;
	}
	public double get_22to54_surcharge() {
		return _22to54_surcharge;
	}
	public void set_22to54_surcharge(double _22to54_surcharge) {
		this._22to54_surcharge = _22to54_surcharge;
	}
	public boolean is_55to59() {
		return _55to59;
	}
	public void set_55to59(boolean _55to59) {
		this._55to59 = _55to59;
	}
	public double get_55to59_surcharge() {
		return _55to59_surcharge;
	}
	public void set_55to59_surcharge(double _55to59_surcharge) {
		this._55to59_surcharge = _55to59_surcharge;
	}
	public boolean isOver60() {
		return over60;
	}
	public void setOver60(boolean over60) {
		this.over60 = over60;
	}
	public double getOver60_surcharge() {
		return over60_surcharge;
	}
	public void setOver60_surcharge(double over60_surcharge) {
		this.over60_surcharge = over60_surcharge;
	}
}
