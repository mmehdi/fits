package uk.ac.abdn.fits.hibernate.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_not_available_time")
public class ServiceNotAvailable {
	
	  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	  
	@Column(name="inactiveFrom", nullable = false)
	private Date from;
	  
	@Column(name="inactiveTo", nullable = false)
	private Date to;
	  
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
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getFromStr(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);  // MM/dd/yyyy
		return dateFormat.format(from).toString();
	}
	public String getToStr(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK); 
		return dateFormat.format(to).toString();
	}

}
