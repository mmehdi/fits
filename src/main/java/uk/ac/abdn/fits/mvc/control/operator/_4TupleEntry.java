package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.text.DecimalFormat;

public class _4TupleEntry {
	
	private double value1;
	
	private double value2;
	
	private String value3;
	
	private double value4;
	
	public _4TupleEntry(double value1, double value2, String value3, double value4){
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
	}
	

	public double getValue1() {
		return value1;
	}

	public void setValue1(double value1) {
		this.value1 = value1;
	}

	public double getValue2() {
		return value2;
	}

	public void setValue2(double value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		String result = df.format(value4);
		
		return "£"+result;
	}

	public void setValue4(double value4) {
		this.value4 = value4;
	}
	
	

}
