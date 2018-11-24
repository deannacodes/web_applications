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

import lab3.GuestBookEntry;



@WebServlet(loadOnStartup=1, urlPatterns= {"/photos/Albums", "/photos/albums"})
public class Albums extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ArrayList<PhotoAlbum> photoAlbums = new ArrayList<>();
		
		getServletContext().setAttribute("photoAlbums", photoAlbums);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ArrayList<PhotoAlbum> photoAlbums = (ArrayList<PhotoAlbum>) getServletContext().getAttribute("photoAlbums");
		
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
		out.println("	<div class=\"row justify-content-between\">");
		out.println("		<h1 class='col-auto'> Photo Albums </h1>");	
		out.println("		<div class=\"col-auto\">");
		out.println("			<a href=\"CreateAlbum\" class=\"btn btn-primary\">Create Album</a>");
		out.println("		</div>");
		out.println("	</div>");

		out.println("	<hr>");			
		out.println("	<div class=\"row align-items-center\">");
		
		for ( PhotoAlbum album : photoAlbums ) {
			
			out.println("<div class=\"col-md-3\">");
			out.println("	<a style=\"padding: 40px 0; width: 100%;\" class=\"btn btn-light\" href=\"AlbumView?albumId=" + album.getId() + "\">"
					+ "<h5 style='white-space: normal;'>" + album.getName() + "</h5>"
					+ "<br>" 
					+ "<p style='white-space: normal;'>" + album.getDescription() + "</p>"
					+ "</a>");
				
				
			out.println("	<div style=\"padding-top: 5px;\">");
			out.println("		<form action='Delete' method='get'>");
			out.println("			<input type='hidden' name='albumId' value='" + album.getId() + "'>");										
			out.println("			<input type='submit' value='Delete' class=\"btn btn-danger\">");
			out.println("		</form>");
			out.println("	</div>");
			
			out.println("</div>");
		}
			
		out.println("	</div>");

		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
	