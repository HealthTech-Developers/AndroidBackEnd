package model;

import java.util.ArrayList;

import database.DatabaseConnection;

public class Presrciption implements PatientRecord {
	private String Date;
	private ArrayList<Drug> drugs;
	private String id;
	DatabaseConnection db;
	@Override
	public void insertrecord(String patientId, String doctorId) {
		
		
		if(null!=db)
			db.insertPrescription( drugs,patientId, doctorId);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void retrieveRecord(String PatientId) {
		// TODO Auto-generated method stub
		
	}

}
