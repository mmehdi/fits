package uk.ac.abdn.fits.hibernate.model;

import java.math.BigInteger;

import javax.persistence.Column;

public class QueryLogGroupedDTO
{
	
@Column(name="column_name")
private String column_name;


@Column(name="count")
  private Long count;
  

public String getColumn_name() {
	return column_name;
}

public void setColumn_name(String column_name) {
	this.column_name = column_name;
}

public Long getCount() {
	return count;
}

public void setCount(Long count) {
	this.count = count;
}

}
