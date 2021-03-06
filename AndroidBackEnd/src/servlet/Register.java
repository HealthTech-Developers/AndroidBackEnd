package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Register() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





		response.setContentType("application/json");
		System.out.println("connection ok");
		String speciality="General";
		String username = request.getParameter("username");
		System.out.println(username);
		String passKey =  request.getParameter("password");
		System.out.println(passKey);

		// 
		String userType=  request.getParameter("userType");
		System.out.println(userType);

		String firstName = request.getParameter("name");
		
		System.out.println(firstName);

		String nationID = request.getParameter("nationalId"); 
		System.out.println(nationID);

		// instance of the user
		User user;
		boolean feedBack;
		JSONObject json = new JSONObject();

		// if the user is a Patient
		if (userType.equals("patient")){

			// concrete user
			user = new Patient();
			// setting information
			user.setFirstName(firstName);
			user.setNationalID(nationID);
			System.out.println("Patient registering...");

			// to the User
			user.setUsername(username);
			user.setPassword(passKey);
			user.setUserType(userType);


			feedBack = user.register();
			try {
				json.put("feedback", feedBack);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// if the user is a Doctor
		else{
			speciality=request.getParameter("speciality");
			user = new Doctor();
			System.out.println("Doctor  registering...");


			// setting information
			user.setFirstName(firstName);
			user.setNationalID(nationID);
			//
			user.setUserType(userType);
			user.setUsername(username);
			user.setPassword(passKey);

			// setting a profession
			Doctor doctor = (Doctor) user;
			doctor.setProffession(speciality);
			System.out.println(speciality);
			System.out.println("Doctor  registering again...");



			// registering
			feedBack= doctor.register();
			try {

				// json to send
				json.put("feedback",feedBack);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}	

		//returning the json to the front end
		response.getWriter().print(json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
