package model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class will be used to retrieve all history when needed by ht epatient
 * or Doctor.
 * 
 * */

public class PatientHistory {
	
	
	PatientHistory(){}
	PatientHistory(User user){}
	
	
	private ArrayList  <Presrciption> prescHistory;
	private ArrayList<Consultation> consHistory;
	
	
	
	public void retrievePrescritions(User user){
		
		
		
	}
	
	public void retrieveConsultations(User user){
				
		
	}
	
	
	// joson sending to the front end 
	public JSONObject sendHistory(){
		//send the following to the front end
		
		//this.consHistory;
		//this.prescHistory;
		
		JSONObject json = new JSONObject ();
		
		try {
			
			
			json.put("consult", this.prescHistory);
			json.put("consult", this.consHistory);
			// check for emptiness at the front end
			
		} catch (JSONException e) {

			
			e.printStackTrace();
		}
		return json;
		
	}
	

	
	
	

}
