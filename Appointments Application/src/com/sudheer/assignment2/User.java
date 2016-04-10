package com.sudheer.assignment2;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class User {
	@PrimaryKey
	@Persistent
	private Key ID;
	
    @Persistent(mappedBy="parent")
	private ArrayList<Appointment> my_appointments;
	
	public User(Key id){
		this.ID=id;
	}
	
	public Key getID() {
		return ID;
	}
	
	public void setID(Key id) {
		this.ID = id;
	}
	
	public ArrayList<Appointment> getAppointments() {
		return my_appointments;
	}
    
	public void addAppointment(Appointment temp){
		if (my_appointments==null)	
			my_appointments=new ArrayList<Appointment>();
    	my_appointments.add(temp);
    }

}
