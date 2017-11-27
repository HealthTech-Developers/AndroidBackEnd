package model;

import java.util.ArrayList;

import org.json.JSONObject;

import database.DatabaseConnection;

public class Presrciption implements PatientRecord {
	private String Date;
	private ArrayList<Drug> drugs;
	private String id;
	DatabaseConnection db;
	@Override
	public JSONObject insertrecord(String patientId, String doctorId) {
		 db = new DatabaseConnection();
		
		if(null!=db)
			db.insertPrescription( drugs,patientId, doctorId);
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void retrieveRecord(String PatientId) {
		// TODO Auto-generated method stub
		
	}

}
