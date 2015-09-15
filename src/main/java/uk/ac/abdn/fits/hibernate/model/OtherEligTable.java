package uk.ac.abdn.fits.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="other_elig")
public class OtherEligTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	
	@Column(name="elig", nullable = false)
	private String elig;
	
	@Column(name="description", nullable = false)
	private String desription;
	
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
	public String getElig() {
		return elig;
	}
	public void setElig(String elig) {
		this.elig = elig;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
}
