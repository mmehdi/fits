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
@Table(name="surcharge_operating_time")
public class SurchargeOperatingTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	  
	@Column(name="start_05_hours_earlier", nullable = false)
	private boolean start_05_hours_earlier;
	  
	@Column(name="start_05_hours_earlier_surcharge", nullable = true)
	private double start_05_hours_earlier_surcharge;
	  
	@Column(name="start_1_hour_earlier", nullable = false)
	private boolean start_1_hour_earlier;
	  
	@Column(name="start_1_hour_earlier_surcharge", nullable = true)
	private double start_1_hour_earlier_surcharge;
	  
	@Column(name="start_15_hours_earlier", nullable = false)
	private boolean start_15_hours_earlier;
	  
	@Column(name="start_15_hours_earlier_surcharge", nullable = true)
	private double start_15_hours_earlier_surcharge;
	  
	@Column(name="finish_05_hours_later", nullable = false)
	private boolean finish_05_hours_later;
	  
	@Column(name="finish_05_hours_later_surcharge", nullable = true)
	private double finish_05_hours_later_surcharge;
	  
	@Column(name="finish_1_hour_later", nullable = false)
	private boolean finish_1_hour_later;
	  
	@Column(name="finish_1_hour_later_surcharge", nullable = true)
	private double finish_1_hour_later_surcharge;
	  
	@Column(name="finish_15_hours_later", nullable = false)
	private boolean finish_15_hours_later;
	  
	@Column(name="finish_15_hours_later_surcharge", nullable = true)
	private double finish_15_hours_later_surcharge;

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

	public boolean isStart_05_hours_earlier() {
		return start_05_hours_earlier;
	}

	public void setStart_05_hours_earlier(boolean start_05_hours_earlier) {
		this.start_05_hours_earlier = start_05_hours_earlier;
	}

	public double getStart_05_hours_earlier_surcharge() {
		return start_05_hours_earlier_surcharge;
	}

	public void setStart_05_hours_earlier_surcharge(
			double start_05_hours_earlier_surcharge) {
		this.start_05_hours_earlier_surcharge = start_05_hours_earlier_surcharge;
	}

	public boolean isStart_1_hour_earlier() {
		return start_1_hour_earlier;
	}

	public void setStart_1_hour_earlier(boolean start_1_hour_earlier) {
		this.start_1_hour_earlier = start_1_hour_earlier;
	}

	public double getStart_1_hour_earlier_surcharge() {
		return start_1_hour_earlier_surcharge;
	}

	public void setStart_1_hour_earlier_surcharge(
			double start_1_hour_earlier_surcharge) {
		this.start_1_hour_earlier_surcharge = start_1_hour_earlier_surcharge;
	}

	public boolean isStart_15_hours_earlier() {
		return start_15_hours_earlier;
	}

	public void setStart_15_hours_earlier(boolean start_15_hours_earlier) {
		this.start_15_hours_earlier = start_15_hours_earlier;
	}

	public double getStart_15_hours_earlier_surcharge() {
		return start_15_hours_earlier_surcharge;
	}

	public void setStart_15_hours_earlier_surcharge(
			double start_15_hours_earlier_surcharge) {
		this.start_15_hours_earlier_surcharge = start_15_hours_earlier_surcharge;
	}

	public boolean isFinish_05_hours_later() {
		return finish_05_hours_later;
	}

	public void setFinish_05_hours_later(boolean finish_05_hours_later) {
		this.finish_05_hours_later = finish_05_hours_later;
	}

	public double getFinish_05_hours_later_surcharge() {
		return finish_05_hours_later_surcharge;
	}

	public void setFinish_05_hours_later_surcharge(
			double finish_05_hours_later_surcharge) {
		this.finish_05_hours_later_surcharge = finish_05_hours_later_surcharge;
	}

	public boolean isFinish_1_hour_later() {
		return finish_1_hour_later;
	}

	public void setFinish_1_hour_later(boolean finish_1_hour_later) {
		this.finish_1_hour_later = finish_1_hour_later;
	}

	public double getFinish_1_hour_later_surcharge() {
		return finish_1_hour_later_surcharge;
	}

	public void setFinish_1_hour_later_surcharge(
			double finish_1_hour_later_surcharge) {
		this.finish_1_hour_later_surcharge = finish_1_hour_later_surcharge;
	}

	public boolean isFinish_15_hours_later() {
		return finish_15_hours_later;
	}

	public void setFinish_15_hours_later(boolean finish_15_hours_later) {
		this.finish_15_hours_later = finish_15_hours_later;
	}

	public double getFinish_15_hours_later_surcharge() {
		return finish_15_hours_later_surcharge;
	}

	public void setFinish_15_hours_later_surcharge(
			double finish_15_hours_later_surcharge) {
		this.finish_15_hours_later_surcharge = finish_15_hours_later_surcharge;
	}

}
