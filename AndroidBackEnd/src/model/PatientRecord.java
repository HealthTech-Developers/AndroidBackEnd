package model;

public interface PatientRecord {
	
	void insertrecord(String patientId,String doctorId);
	
	//retrieving records from the history
	void retrieveRecord(String PatientId);

}
