package uk.ac.abdn.fits.hibernate.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="operator")
public class Operator {
	
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="operator_id", nullable = false)
	  private int operator_id;
	  
	  @Column(name="user_id", nullable = false)
	  private int user_id;
	  
	  @Column(name="name", nullable = false)
	  private String name;
	  
	  @Column(name="description", nullable = false)
	  private String description;
	  
	  @Column(name="type_of_permit", nullable = false)
	  private String type_of_permit;
	  
	  @Column(name="how_to_book", nullable = true)
	  private String how2book;
	  
	  @Column(name="inactive", nullable = true)
	  private boolean inactive;
	  
	  @Column(name="inactive_from", nullable = true)
	  private Date inactiveFrom;
	  
	  @Column(name="inactive_to", nullable = true)
	  private Date inactiveTo;
	  
	  @Column(name="last_update", nullable = true)
	  private Date last_update;
	  
	  public int getOperator_id(){
		  return operator_id;
	  }
	  public void setOperator_id(int operator_id){
		  this.operator_id = operator_id;
	  }
	  public int getUser_id(){
		  return user_id;
	  }
	  public void setUser_id(int user_id){
		  this.user_id = user_id;
	  }
	  public String getName(){
		  return name;
	  }
	  public void setName(String name){
		  this.name = name;
	  }
	  public String getDescription(){
		  return description;
	  }
	  public void setDescription(String description){
		  this.description = description;
	  }
	  public String getType_of_permit() {
		  return type_of_permit;
	  }
	  public void setType_of_permit(String type_of_permit) {
		  this.type_of_permit = type_of_permit;
	  }
	  public String getHow2book() {
		  return how2book;
	  }
	  public void setHow2book(String how2book) {
		  this.how2book = how2book;
	  }
	  public boolean isInactive() {
		  return inactive;
	  }
	  public void setInactive(boolean inactive) {
		  this.inactive = inactive;
	  }
	  public Date getInactiveFrom() {
		  return inactiveFrom;
	  }
	  public void setInactiveFrom(Date inactiveFrom) {
		  this.inactiveFrom = inactiveFrom;
	  }
	  public Date getInactiveTo() {
		  return inactiveTo;
	  }
	  public void setInactiveTo(Date inactiveTo) {
		  this.inactiveTo = inactiveTo;
	  }
	  public Date getLast_update() {
		  return last_update;
	  }
	  public void setLast_update(Date last_update) {
		  this.last_update = last_update;
	  }
}
