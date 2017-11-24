package model;

import database.DatabaseConnection;

public class Consultation implements PatientRecord {

	private StringBuffer buff;
	
	
	
	public StringBuffer getBuff() {
		return buff;
	}

	public void setBuff(StringBuffer buff) {
		this.buff = buff;
	}

	@Override
	public void insertrecord( String patientId) {
		// new connection
		DatabaseConnection db = new DatabaseConnection();
		// insert the buffer of consultation to the specified Patient id
		db.insertrecord(buff,patientId);		
	}

	@Override
	public void retrieveRecord(String patientId) {
		// TODO Auto-generated method stub
		
	}

}
