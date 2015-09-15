package uk.ac.abdn.fits.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="vehicle")
public class Vehicle {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="vehicle_type", nullable = false)
	private String vehicle_type;
	
	@Column(name="operator_id", nullable = true)
	private int operator_id;
	
	@Column(name="reg_num", nullable = false)
	private String reg_num;
	
	@Column(name="description", nullable = true)
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getReg_num() {
		return reg_num;
	}
	public void setReg_num(String reg_num) {
		this.reg_num = reg_num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}
}
