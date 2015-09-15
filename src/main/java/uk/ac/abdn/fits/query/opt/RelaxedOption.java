package uk.ac.abdn.fits.query.opt;

import uk.ac.abdn.fits.hibernate.model.Operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */



public class RelaxedOption {
	
	private Operator operator;
	private String relaxed_constraint;
	private String relaxed_info;
	
	public RelaxedOption(){
		
	}
	
	public Operator getOperator(){
		return operator;
	}
	
	public void setOperaotr(Operator operator){
		this.operator = operator;
	}
	
	public String getRelaxedConstraint(){
		return relaxed_constraint;
	}
	
	public void setRelaxedInfo(String relaxed_info){
		this.relaxed_info = relaxed_info;
	}
	
	public String getRelaxedInfo(){
		return relaxed_info;
	}
	
	
}
