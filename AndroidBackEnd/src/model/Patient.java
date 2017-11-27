package model;


import org.json.JSONObject;

import database.DatabaseConnection;

public class Patient extends User {
	private PatientHistory aHistory;
	private final String userType= "patient";

	@Override
	public JSONObject login() {
		DatabaseConnection db = new DatabaseConnection();

		 return db.login(this);
		

	}

	@Override
	public boolean register() {
		// accessing the database
		DatabaseConnection db = new DatabaseConnection();
		return db.register(this);

	}

	/**
	 * this new dab connection should be followed by the access grant. 
	 * a token is generated allowing the Doctor to access the patient Id
	 * @return 
	 * 
	 * */
	public JSONObject giveAccess( String doctorId){
		
		DatabaseConnection db=new DatabaseConnection();
		

		return db.grantAccess(this, doctorId);
	}





}
