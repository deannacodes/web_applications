package hw1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/photos/CreateAlbum")
public class CreateAlbum extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumName = "";
		String albumDescription = "";
		
		if (request.getParameter("albumName") != null) {
			albumName = request.getParameter("albumName");
		}
		
		if (request.getParameter("albumDesc") != null) {
			albumDescription = request.getParameter("albumDesc");
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Albums</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div style='padding-top: 50px;' class=\"container\">");
		
		if (request.getParameter("error") != null && request.getParameter("error").equals("missingFields")) {
    		out.println("<div class=\"alert alert-danger\" role=\"alert\">" + 
    					"    Album name and description are required." + 
    					"</div>");
    	}
		
		out.println("<div class=\"card\">");
		out.println("	<h1 class='card-header'>Create Album</h1>");
		out.println("	<div class=\"card-body\">");
		out.println("		<form action='CreateAlbum' method='post'>");	
		out.println("   		<div class='form-group'>");
		out.println("			    <label for=\"albumName\">Album Name</label>");
		out.println("			    <input type=\"text\" name=\"albumName\" value=\"" + albumName + "\">");
		out.println("   		</div>");		
		out.println("   		<div class='form-group'>");
		out.println("	 		   <label for=\"albumDesc\">Album Description</label>");
		out.println("	 		   <input type=\"text\" name=\"albumDesc\" value=\"" + albumDescription + "\">");
		out.println("  	 		</div>");
		out.println("			<input class=\"btn btn-primary\" type=\"submit\" value=\"Submit\">");
		out.println("			<a href='Albums' class='btn btn-secondary'>Cancel<a/>");
		out.println("		</form>");

		out.println("		</div>");
		out.println("	</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("albumName").equals("") || request.getParameter("albumDesc").equals("")) {
			response.sendRedirect("CreateAlbum?error=missingFields&albumName=" + request.getParameter("albumName")+ "&albumDesc=" + request.getParameter("albumDesc"));
			return;
		}
	
		String newFileDir = getServletContext().getRealPath( "/WEB-INF/uploads" ) + "/" + request.getParameter("albumName");	
		File dir = new File(newFileDir);
		dir.mkdir();
		
		ArrayList<PhotoAlbum> photoAlbums = (ArrayList<PhotoAlbum>) getServletContext().getAttribute("photoAlbums");
		
		String name = request.getParameter("albumName");
		String description = request.getParameter("albumDesc");
		PhotoAlbum newAlbum = new PhotoAlbum(name, description, newFileDir);
		
		photoAlbums.add(newAlbum);
		
		response.sendRedirect("Albums");
	}
}
	