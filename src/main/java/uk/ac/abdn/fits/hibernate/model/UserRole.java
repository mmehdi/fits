package uk.ac.abdn.fits.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="user_roles")
public class UserRole {

	
	@Id
	@Column(name="user_id", nullable = false)
	private int user_id;
	
	@Column(name="role_id", nullable = false)
	private String role_id;
	
		
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	  
}
