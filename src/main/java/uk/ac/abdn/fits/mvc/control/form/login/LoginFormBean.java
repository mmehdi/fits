package uk.ac.abdn.fits.mvc.control.form.login;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import org.hibernate.validator.constraints.NotEmpty;


public class LoginFormBean {
	
	@NotEmpty
	private String j_username;
	
	@NotEmpty
	private String j_password;


    public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password(){
		return j_password;
	}
	public void setJ_password(String j_password){
		this.j_password = j_password;
	}
	


	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
        if (j_username != null) {
        	sb.append("'").append(j_username).append("', ");
        } else {
        	sb.append(j_username).append(", ");
        }
        sb.append("password=").append(j_password);
        return sb.toString();
    }
}
