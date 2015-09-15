package uk.ac.abdn.fits.mvc.control.form.register;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */


import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;
//import org.springframework.samples.mvc.convert.MaskFormat;

import uk.ac.abdn.fits.mvc.convert.MaskFormat;


public class RegisterFormBean {
	
//	@NotEmpty
	private String fname;
	
	

	//	@NotEmpty
	private String lname;
	
	
//	@Min(1)
	private String email;

//	@DateTimeFormat(iso=ISO.DATE)
//	@Past
//	private Date birthDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@MaskFormat("#### ### ####")
	private String phone;

	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String passwordRepeat;
	
	
//	@NumberFormat(pattern="$###,###.00")
//	private BigDecimal currency;

//	@NumberFormat(style=Style.PERCENT)
//	private BigDecimal percent;
	
//	private InquiryType inquiry;
//	
//	private String inquiryDetails;
	
//	private boolean subscribeNewsletter;
//	
//	private Map<String, String> additionalInfo;

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
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPasswordRepeat(){
		return passwordRepeat;
	}
	
	public void setPasswordRepeat(String passwordRepeat){
		this.passwordRepeat = passwordRepeat;
	}
	

//	public BigDecimal getCurrency() {
//		return currency;
//	}
//
//	public void setCurrency(BigDecimal currency) {
//		this.currency = currency;
//	}
//
//	public BigDecimal getPercent() {
//		return percent;
//	}
//
//	public void setPercent(BigDecimal percent) {
//		this.percent = percent;
//	}
//
//	public InquiryType getInquiry() {
//		return inquiry;
//	}
//
//	public void setInquiry(InquiryType inquiry) {
//		this.inquiry = inquiry;
//	}
//
//	public String getInquiryDetails() {
//		return inquiryDetails;
//	}
//
//	public void setInquiryDetails(String inquiryDetails) {
//		this.inquiryDetails = inquiryDetails;
//	}


	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("properties name=");
        
      
       
        sb.append("phone=");
        if (phone != null) {
        	sb.append("'").append(phone).append("', ");
        } else {
        	sb.append(phone).append(", ");
        }
        sb.append("userName=").append(userName).append(", ");
//        sb.append("currency=").append(currency).append(", ");
//        sb.append("percent=").append(percent).append(", ");
//        sb.append("inquiry=").append(inquiry).append(", ");
//        sb.append("inquiryDetails=");
//        if (inquiryDetails != null) {
//        	sb.append("'").append(inquiryDetails).append("', ");
//        } else {
//        	sb.append(inquiryDetails).append(", ");
//        }
      
        return sb.toString();
    }
}
