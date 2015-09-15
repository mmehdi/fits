package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.Serializable;

public class TextValuePair implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String text;
	protected String value;
	
	public TextValuePair(String text, String value){
		this.text = text;
		this.value = value;
	}
	  
	public String getText()
	{
		return text;
	}
	  
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}

}