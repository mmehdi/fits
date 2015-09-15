package uk.ac.abdn.fits.hibernate.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;


@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id", nullable = false)
  private int id;
  
  @Column(name="username", nullable = false)
  private String username;
  
  @Column(name="password", nullable = false)
  private String password;
  
  @Column(name="enabled", nullable = false)
  private boolean enabled;
  
  @OneToOne(cascade=CascadeType.ALL)  
  @JoinTable(name="user_roles",  
      joinColumns = {@JoinColumn(name="user_id")},  
      inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}  
  )  
  private Role role;  
  
  @Column(name="fname", nullable = true)
  private String fname;
 
  @Column(name="lname", nullable = true)
  private String lname;
  
  @Column(name="email", nullable = true)
  private String email;
  
  @Column(name="phone_number", nullable = true)
  private String phone_number;
  
  
  public int getId() {
	  return id;
  }
  
  public void setId(int id) {
	  this.id = id;
  }

  public String getUsername() {
	  return username;
  }
  
  public void setUsername(String username) {
	  this.username = username;
  }
  
  public String getPassword() {
	  return password;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public void setEnabled(boolean enabled){
	  this.enabled = enabled;
  }
  
  public boolean getEnabled(){
	  return enabled;
  }
  
  public Role getRole() {
	  return role;
  }

  public void setRole(Role role) {
	  this.role = role;
  }	
  
  public String getFname() {
	return fname;
  }

  public void setFname(String fname) {
	  this.fname = fname;
  }

  public String getLname() {
	  return lname;
  }

  public void setLname(String lname) {
	  this.lname = lname;
  }

  public String getEmail() {
	  return email;
  }

  public void setEmail(String email) {
	  this.email = email;
  }

  public String getPhone_number() {
	  return phone_number;
  }

  public void setPhone_number(String phone_number) {
	  this.phone_number = phone_number;
  }
  
}