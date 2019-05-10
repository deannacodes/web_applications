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



@WebServlet(urlPatterns= {"/lab4/Login"}, loadOnStartup=1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(checkCookies(request)) {
			response.sendRedirect("MembersOnly");
		}
		
		// Set my content type
		response.setContentType("text/html");
				
		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();
		String error = "";
		
		
		
		if (request.getParameter("loginBtn") != null) {			

			String email = request.getParameter("username");
			
			if (checkPassword(request)) {
				Cookie cookie = new Cookie( "email", email );
	            response.addCookie( cookie );

				response.sendRedirect("MembersOnly");
			} else {
				error = "<div class=\"alert alert-danger\" role=\"alert\">\r\n" + 
						    "  Wrong Username or Password combination.\n" + 
						    "</div>";
			}
		}
		
		
		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Login</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body style=\"padding-top: 60px;\">");
		out.println("<div class=\"container\">");
		
		out.println("<h1>Login</h1>");
		
		out.println(error);
		
		out.println("<form action=\"Login\" method=\"post\">");
		out.println("  <div class=\"form-group\">");
		out.println("    <label for=\"username\">Username</label>");
		out.println("    <input class=\"form-control mr-sm-2\" type=\"text\" name=\"username\" >");
		out.println("  </div>");
		out.println("  <div class=\"form-group\">");
		out.println("    <label for=\"password\">Password</label>");
		out.println("    <input class=\"form-control mr-sm-2\" type=\"password\" name=\"password\" >");
		out.println("  </div>");
		
		out.println("  <input class=\"btn btn-primary\" type=\"submit\" name=\"loginBtn\" value=\"Login\">");

		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
			
	}
	
	
	public boolean checkPassword(HttpServletRequest request) {
		if (request.getParameter("username").equals("acervan5@calstatela.edu") && request.getParameter("password").equals("abcd")) {
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
        if( cookies != null ) {
            for( Cookie cookie : cookies ) {
                if( cookie.getName().equals( "email" ) ) {
                	return true;
        		}
			}
		}
        
        return false;
	}
	
	

}