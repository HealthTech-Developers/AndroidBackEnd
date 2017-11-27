package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class Consultation
 */
@WebServlet("/Consultation")
public class Consultation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Consultation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSONObject json = request.get

		model.Consultation cons = new model.Consultation();
		String consultation = request.getParameter("consultation");
		System.out.println("cons: "+consultation);
		String doctorId     = request.getParameter("doctorId");
		System.out.println("doctor: "+doctorId);

		String patientId    = request.getParameter("patientId");
		System.out.println("patient: "+patientId);
		cons.setBuff(new StringBuffer(consultation));


		/**
		 * Retrieving different parameters
		 * 
		 * */

		    
		response.getWriter().print(cons.insertrecord(patientId, doctorId));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
