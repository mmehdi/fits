package uk.ac.abdn.fits.mvc.control.admin;

import org.hibernate.validator.constraints.NotEmpty;

import uk.ac.abdn.fits.mvc.convert.MaskFormat;

public class EditUserFormBean{
	
	@NotEmpty
	private String user_id;
	
	@NotEmpty
	private String fname;
	
	@NotEmpty
	private String lname;
	
	@MaskFormat("#### ### ####")
	private String phone;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
