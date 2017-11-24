package model;

import database.DatabaseConnection;

public class Doctor extends User {
	
	
	private String proffession;

	@Override
	public boolean login() {		
		
		DatabaseConnection db = new DatabaseConnection();
		boolean feedback= db.login(this);
		
		
		return feedback;
		
		// TODO Auto-generated method stub

	}

	public String getProffession() {
		return proffession;
	}

	@Override
	public boolean register() {
		
		return false;
		// TODO Auto-generated method stub
		
	}

	public void setProffession(String proffesion) {
		// TODO Auto-generated method stub
		
	}

}
