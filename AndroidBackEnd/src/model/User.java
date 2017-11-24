package model;

public abstract class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalID;
	private String userType;

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		
		
		this.password = PasswordHash.getPasswordHash(password);
	}

	
	/*
	 * an abstract method of logging
	 * 
	 * */
	public abstract boolean login();
	/*
	 * an abstract method of registering
	 * 
	 * */
	public abstract boolean register();

	
	

}
