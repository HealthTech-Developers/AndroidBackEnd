package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Doctor;
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
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}




	}


	public boolean login(User user){

		String username = user.getUsername();
		String password = user.getPassword();
		String hash_ = null;
		ResultSet mySet;

		String query ="SELECT * FROM `EasyHelthy`.`users` WHERE `username`= ?;";		


		try {
			statement = myConnection.prepareStatement(query);
			statement.setString(1, username);
			mySet =statement.executeQuery();

			while(mySet.next()){
				hash_ = mySet.getString("Password");

			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		// retuening whether the credentials matches
		return hash_!=null;


	}


	public boolean register(User user){



		String table = null;
		Doctor user2 = null ;
		String proffesion;
		String query =null;
		boolean isPatient=true;
		if(user instanceof Patient){
			table ="patient";
			query ="INSERT INTO easyhealth.doctor (`name`,`id`,NID) VALUES (?,?,?);";

		}
		else {
			isPatient = false;
			query ="INSERT INTO easyhealth.patient (`name`,`id`,`speciality`) VALUES (?,?,?);";
			user2 = new Doctor();
			user2 = (Doctor) user;

		}

		try {
			statement =  myConnection.prepareStatement(query);
			statement.setString(1,user.getFirstName());
			//		statement.setString(2,user.getLastName());// a function to set id		
			statement.setString(2, "1234567");
			
			
			  if(isPatient)
				  	
				  statement.setString(3,user.getNationalID());
			  else				  
				statement.setString(3,user2.getProffession());			  
			  


			//					statement.setString(2,user.getLastName());

			//		statement.setString(3,user.getUsername());
			//		statement.setString(4,user.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		query ="INSERT INTO easyhealth.users (`username`,`password`,`type`) VALUES (?,?,?);";		
		try {
			statement =  myConnection.prepareStatement(query);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getPassword());
			statement.setString(2,user.getUserType());

			statement.executeQuery();


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
	
	
	public void insertrecord( StringBuffer buff, String patientId) {
		// String buffer is a record
		//insert it to the patient of Id: patientId

		
		//  insert it in the right place	
		
		
	}
	
	

}