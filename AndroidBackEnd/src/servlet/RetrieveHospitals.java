package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import database.DatabaseConnection;
import model.Patient;
import model.PatientHistory;
import model.User;

/**
 * Servlet implementation class RetrieveHospitals
 */
@WebServlet("/RetrieveHospitals")
public class RetrieveHospitals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db = new DatabaseConnection();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveHospitals() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json= new JSONObject();
		String district = request.getParameter("district");
		System.out.println(district);
		
		db.retriveHospitalData(district);
		
		
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
