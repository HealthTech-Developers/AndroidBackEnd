package model;

import org.json.JSONObject;

import database.DatabaseConnection;

public class Consultation implements PatientRecord {

	private StringBuffer buff;
	private String doctorId;
	private String patientId;
	
	
	
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public StringBuffer getBuff() {
		return buff;
	}

	public void setBuff(StringBuffer buff) {
		this.buff = buff;
	}

	@Override
	public JSONObject insertrecord( String patientId,String doctorId) {
		
		// new connection
		DatabaseConnection db = new DatabaseConnection();
		// insert the buffer of consultation to the specified Patient id
		return(db.insertConsultation(buff,patientId,doctorId));		
	}

	@Override
	public void retrieveRecord(String patientId) {
		// TODO Auto-generated method stub
		
	}

}
