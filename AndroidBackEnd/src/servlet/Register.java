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
		response.getWriter().print("successful");
		String username = request.getParameter("username");
		String passKey =  request.getParameter("password");
		
		// 
		String userType=  request.getParameter("userType");
		String firstName = request.getParameter("firstName");
		String nationID = request.getParameter("id");

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
			user = new Doctor();

			// setting information
			user.setFirstName(firstName);
			user.setNationalID(nationID);
			//
			user.setUserType(userType);
			user.setUsername(username);
			user.setPassword(passKey);
			
			// setting a profession
			Doctor doctor = (Doctor) user;
			       doctor.setProffession(username);
			
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
