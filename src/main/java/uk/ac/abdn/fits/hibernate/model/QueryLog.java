package uk.ac.abdn.fits.hibernate.model;

import java.sql.Timestamp;

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
@Table(name="query_log")
public class QueryLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id", nullable = false)
  private int id;
  
  @Column(name="from_postcode", nullable = false)
  private String from_postcode;
  
  @Column(name="from_address", nullable = false)
  private String from_address;
  
  @Column(name="to_postcode", nullable = false)
  private String to_postcode;
  
  @Column(name="to_address", nullable = false)
  private String to_address;
  
  @Column(name="age_group", nullable = false)
  private String age_group;
 
  @Column(name="mobility_status", nullable = false)
  private String mobility_status;
  
  @Column(name="purpose", nullable = false)
  private String purpose;
  
  @Column(name="is_return", nullable = true)
  private boolean is_return;
  

@Column(name="timestamp", nullable = true)
  private Timestamp timestamp;
  
  
  public String getFrom_postcode() {
	return from_postcode;
}

public void setFrom_postcode(String from_postcode) {
	this.from_postcode = from_postcode;
}

public String getFrom_address() {
	return from_address;
}

public void setFrom_address(String from_address) {
	this.from_address = from_address;
}

public String getTo_postcode() {
	return to_postcode;
}

public void setTo_postcode(String to_postcode) {
	this.to_postcode = to_postcode;
}

public String getTo_address() {
	return to_address;
}

public void setTo_address(String to_address) {
	this.to_address = to_address;
}

public String getAge_group() {
	return age_group;
}

public void setAge_group(String age_group) {
	this.age_group = age_group;
}

public String getMobility_status() {
	return mobility_status;
}

public void setMobility_status(String mobility_status) {
	this.mobility_status = mobility_status;
}

public String getPurpose() {
	return purpose;
}

public void setPurpose(String purpose) {
	this.purpose = purpose;
}

public Timestamp getTimestamp() {
	return timestamp;
}

public void setTimestamp(Timestamp timestamp) {
	this.timestamp = timestamp;
}

  public int getId() {
	  return id;
  }
  
  public void setId(int id) {
	  this.id = id;
  }
  
  public boolean isIs_return() {
	return is_return;
}

public void setIs_return(boolean is_return) {
	this.is_return = is_return;
}

}