package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterTest
 */
@WebServlet("/RegisterTest")
public class RegisterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			
			out.print(
					 "<html>"+
					 "<head>"
					 +"<title> Registration</title>"
					 +"</head>"
					 +"<body>"
					 +"<h2>Rgegister here</h2>"
					 
							+ "<form action = \"Register\" method=\"post\">"
							+ "<br>Name <input type=\"text\" name=\"name\" value=\"\"> "
							+ "<br>User name <input type=\"text\" name=\"name_\" value=\"\"> "
							+ "<br>User nationalId <input type=\"text\" name=\"Nid\" value=\"\"> "

							+ "<br>Password <input type=\"password\" name=\"password\">"
							+ "<input type=\"submit\" value=\"sign up\">"
							+ "</body></form>"
							
							+"</html>"
	);
			
			
			
			
			
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
