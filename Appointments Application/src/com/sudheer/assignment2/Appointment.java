package com.sudheer.assignment2;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Appointment {

	@Persistent
	String Name;
	@Persistent
	String Date;
	@Persistent
	String Time;
	@Persistent
	private User parent;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key ID;

	public Key getID() {
		return ID;
	}
	
	public void setID(Key id) {
		this.ID = id;
	}
	
	public User getParent() {
		return parent;
	}
	
	public void setParent(User parent) {
		this.parent = parent;
	}
	
	public Appointment(String name, String date, String time) {
		this.Name = name;
		this.Date = date;
		this.Time = time;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	public String getTime() {
		return Time;
	}
	
	public void setTime(String time) {
		this.Time = time;
	}
}
