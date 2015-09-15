package uk.ac.abdn.fits.hibernate.model;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.sql.Time;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="operator_timetable")
public class OperationalHours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	  
	@Column(name="operator_id", nullable = false)
	private int operator_id;
	  
	@Column(name="day_of_week", nullable = false)
	private String day_of_week;
	  
	@Column(name="session", nullable = true)
	private String session;
	
	@Column(name="opening_time", nullable = true)
	private Time opening_time;
	  
	@Column(name="closing_time", nullable = true)
	private Time closing_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}
	public String getDay_of_week() {
		return day_of_week;
	}
	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Time getOpening_time() {
		return opening_time;
	}
	public void setOpening_time(Time opening_time) {
		this.opening_time = opening_time;
	}
	public Time getClosing_time() {
		return closing_time;
	}
	public void setClosing_time(Time closing_time) {
		this.closing_time = closing_time;
	}

}
