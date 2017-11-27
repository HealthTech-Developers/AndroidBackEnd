package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import model.Doctor;
import model.Drug;
import model.Patient;
import model.User;

public class DatabaseConnection {

	private Connection myConnection;
	private PreparedStatement statement;


	public DatabaseConnection(){


		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://172.29.53.77:3306/easyhealth";
			//myConnection = DriverManager.getConnection(db, "dniwemugisha", "Student@123");
			myConnection = DriverManager.getConnection(db, "root", "");
			System.out.println(myConnection==null);

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}




	}


	public JSONObject  login(User user){

		String username = user.getUsername();
		String password = user.getPassword();
		String hash_ = null;
		String doctorId=null;
		String dbSpassword=null;
		ResultSet mySet;
		JSONObject json = new JSONObject();
		String id;
		boolean loginOk = false;

		String query ="SELECT * FROM `easyhealth`.`users` WHERE `username`= ?;";		


		try {
			statement = myConnection.prepareStatement(query);
			statement.setString(1, username);
			System.out.println(username +" loggin in with password "+password);
			mySet =statement.executeQuery();
			int i=0;
			while(mySet.next()){
				dbSpassword = mySet.getString("password");
				loginOk=(dbSpassword.equals(password));

				try {
					// setting the feedback
					json.put("feedback", loginOk );

					//if login is a success
					if(loginOk){
						json.put("userType",mySet.getString("type"));
						json.put("userId",mySet.getString("user_id"));
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// closing the connection to the server
		finally{

			try {
				myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("done login "+ loginOk);
		return json;


	}


	public boolean register(User user){



		String table = null;
		Doctor user2 = null ;
		String proffesion;
		String query =null;
		boolean isPatient=true;
		String id = null;
		if(user instanceof Patient){
			table ="patient";
			query ="INSERT INTO easyhealth.patient (`patient_id`,`name`,NID) VALUES (?,?,?);";

		}
		else {
			System.out.println("here Doctor");
			isPatient = false;
			query ="INSERT INTO easyhealth.doctor (`doctor_id`,`name`,NID,`speciality`) VALUES (?,?,?,?);";
			user2 = new Doctor();
			user2 = (Doctor) user;

		}

		try {
			statement =  myConnection.prepareStatement(query);
			int t = new Random().nextInt(200)+1;
			id=t+"/doc/2017";
			statement.setString(1,id);
			statement.setString(2,user.getFirstName());
			statement.setString(3,user.getNationalID());

			if(!isPatient)			  {
				System.out.println(user2.getProffession());

				statement.setString(4,user2.getProffession());		
			}

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		query ="INSERT INTO easyhealth.users (`username`,`password`,`type`,`user_id`) VALUES (?,?,?,?);";		
		try {
			System.out.println("pasword....");
			statement =  myConnection.prepareStatement(query);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getUserType());
			statement.setString(4,id);

			statement.executeUpdate();


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}







		// closing connection
		try{

			myConnection.close();
		}catch(SQLException e){

			e.getMessage();
		}


		return true;



	}




	/**
	 * 
	 * inserting a new record into the data base
	 * 
	 * 
	 * */



	public JSONObject insertConsultation( StringBuffer buff, String patientId, String doctorId) {
		System.out.println("adding Data");
		JSONObject json = new JSONObject();
		System.out.println("recording a consultaion");
		String patient_id = patientId;
		String doctor_id = doctorId;
		String description = String.valueOf(buff);
		boolean doneAdding = false;

		ResultSet mySet;

		String query ="INSERT INTO `patient_consultation`(`patient_id`, `doctor_id`, `description`) VALUES (?,?,?);";                      


		try 
		{
			statement = myConnection.prepareStatement(query);
			statement.setString(1, patientId);
			System.out.println("idddddddddd"+patientId);
			statement.setString(2, doctor_id);
			statement.setString(3, description);
			statement.executeUpdate();
			System.out.println("adding user"+ patient_id);
			doneAdding=true;
		}
		catch (SQLException e) 
		{
			System.out.println(e);
		}

		// closing the connection to the server
		finally{

			try {
				myConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// setting the feedback
		try {
			json.put("feedback", doneAdding);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}


	public void insertPrescription(ArrayList<Drug> drugs, String patientId, String doctorId) {



	}

	public JSONObject retriveConsultations(String patientId){
		JSONObject json = new JSONObject();
		String id ="25/doc/2017";

		ArrayList<String> retriveConsultations_ = new ArrayList<String> ();
		String query= "SELECT description, `doctor_id` FROM patient_consultation where patient_id = '"+ id + "'";
System.out.println("hhhh"+patientId);

		try {

			statement = myConnection.prepareStatement(query);


			ResultSet myRs = statement.executeQuery();


			while (myRs.next()) {
				System.out.println("here ok");


				retriveConsultations_.add(myRs.getString("description") +"\n by"+ myRs.getString("doctor_id"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				myConnection.close();  
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				json.put("consultation",retriveConsultations_);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;


	}
	/**
	 * 
	 * 
	 * 
	 * 
	 * */
	public JSONObject grantAccess(Patient patient,String doctorId){

		System.out.println("grandting acess...");
		JSONObject json = new JSONObject();
		String patient_id = patient.getId();
		//Date date
		String valid = "valid:";
		try {

			if(patient_id != null && doctorId != null){
				String query = "UPDATE `doctor` SET `token`='"+valid +patient_id+ "' WHERE doctor_id = '"+doctorId+"'";
				statement = myConnection.prepareStatement(query);
				statement.executeUpdate(query);
				// Sleep for 
				try {
					System.out.println("going to sleep...");




					Thread.sleep(1000*30);
					System.out.println("coming from sleep...");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("declining accessing...");
				query = "UPDATE `doctor` SET `token`= null WHERE doctor_id = '"+doctorId+"'";
				statement.executeUpdate(query);
			}
			else{
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 


		try {
			return json.put("feedback", true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("acess done...");

		return json;
	}



	// checking access granted
	public JSONObject checkAccess(Doctor doctor) {
		System.out.println("access checking....");

		JSONObject json = new JSONObject();
		ResultSet set= null;
		String query = null;
		String token =null;
		String doctorId = doctor.getId();
		if(doctorId !=null){
			query= "Select token from easyhealth.doctor where doctor_id ='"+doctorId+"' ";
		}
		else
		{

		}

		try {
			statement= myConnection.prepareStatement(query);
			set=statement.executeQuery();

			if (set.next()){

				token= set.getString("token");
				System.out.println("doctor found....");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			json.put("feedback", token!=null);
			if(token!=null){
				// adding the patient id

				json.put("patientId",token.split(":",2)[1]);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("access checked");
		return json;
	}








	public ArrayList<String> retrivePatientData(){
		JSONObject json = new JSONObject();

		ArrayList<String> patientData = new ArrayList<String> ();
		String query= "SELECT pt.patient_id, pt.name, ins.insurance_id, ins.insurance_name, ins.insurance_type, ins.insurance_status from patient pt join insurance ins on pt.patient_id = ins.patient_id"; 

				try {
					statement = myConnection.prepareStatement(query);


					ResultSet myRs = statement.executeQuery();

					while (myRs.next()) {

						patientData.add(myRs.getString("patient_id"));
						patientData.add(myRs.getString("name"));
						patientData.add(myRs.getString("insurance_id"));
						patientData.add(myRs.getString("insurance_name"));
						patientData.add(myRs.getString("insurance_type"));
						patientData.add(myRs.getString("insurance_status"));
					}
				} catch (SQLException e) {
					System.out.println(e);
				} finally {
					try {
						myConnection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

					
				}
		return patientData;



	}
























}