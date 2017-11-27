package model;

import org.json.JSONObject;

import database.DatabaseConnection;

public class Doctor extends User {

    private String doctorId;
	private String proffession;

	@Override
	public JSONObject login() {		

		DatabaseConnection db = new DatabaseConnection();
		return db.login(this);


		

		// TODO Auto-generated method stub

	}

	public String getProffession() {
		return proffession;
	}

	@Override
	public boolean register() {

		DatabaseConnection db=new DatabaseConnection();



		return db.register(this);
		// TODO Auto-generated method stub

	}

	public void setProffession(String proffession) {
		this.proffession=proffession;		
	}
	
	
	public void retrieveRecords(String patientId){
		
		
		
	}
	public void addRecoords(StringBuffer recordBuff, String PatientId ){
		
		
		
		
	}

	public JSONObject checkAccess() {
     DatabaseConnection db =  new DatabaseConnection();	
     return db.checkAccess(this);
	}
	
	

}
