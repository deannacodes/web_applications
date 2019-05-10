package lab4;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns= {"/lab4/MembersOnly"}, loadOnStartup=1)
public class MembersOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "";
		
		boolean loggedIn = false;
		Cookie[] cookies = request.getCookies();
        if( cookies != null ) {
            for( Cookie cookie : cookies ) {
                if( cookie.getName().equals( "email" ) ) {
                	email = cookie.getValue();
        			loggedIn = true;
                }
            }
        }
        			
        if (!loggedIn)
        	response.sendRedirect("Login");
        
		
		// Set my content type
		response.setContentType("text/html");
				
		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();
		
		
		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Members Only</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body style=\"padding-top: 60px;\">");
		out.println("<div class=\"container\">");
		
		
		out.println("<h1>Welcome to the club</h1>");
		
		out.println("<p>");
		out.println("  Welcome, " + email + "!");
		out.println("</p>");
		
		out.println("<a href=\"Logout\" class=\"btn btn-danger\">Logout</a>");

		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	
	
	
	
	
	

}