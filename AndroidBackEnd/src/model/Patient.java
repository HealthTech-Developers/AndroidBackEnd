package model;


import database.DatabaseConnection;

public class Patient extends User {
	private PatientHistory aHistory;

	@Override
	public boolean login() {
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
	 * 
	 * 
	 * */
	public void giveAccess(){

		return;
	}





}
