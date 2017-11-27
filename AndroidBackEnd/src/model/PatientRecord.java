package model;

import org.json.JSONObject;

public interface PatientRecord {
	
	JSONObject insertrecord(String patientId,String doctorId);
	
	//retrieving records from the history
	void retrieveRecord(String PatientId);

}
