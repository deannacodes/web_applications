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



@WebServlet("/photos/PhotoView")
public class PhotoView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<PhotoAlbum> photoAlbums = (ArrayList<PhotoAlbum>) getServletContext().getAttribute("photoAlbums");
		int albumFoundId = -1;
		
		
		for ( int i = 0 ; i < photoAlbums.size() ; i++) {
			if (photoAlbums.get(i).getId() == Integer.parseInt(request.getParameter("albumId"))) {
				albumFoundId = i;
			}
		}
		
		if (request.getParameter("albumId") == null || albumFoundId == -1) {
			
			response.sendRedirect("Albums");
			return;
		}
		
		PhotoAlbum album = photoAlbums.get(albumFoundId);
		int photoId = Integer.parseInt(request.getParameter("photoId"));		
		Photo photo = album.getPhoto(photoId);

		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>" + photo.getName() + "</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div style='padding: 50px 0;' class=\"container\">");
		out.println("	<div class=\"row justify-content-between\">");
		out.println("		<h1 class='col-auto'>" + photo.getName() + "</h1>");	
		out.println("		<div class=\"col-auto \">");
		out.println("			<a download class=\"btn btn-primary\" href=\"Download?albumId=" + album.getId() + "&photoId=" + photo.getId() + "\">Download</a> ");
		out.println("			<a class=\"btn btn-danger\" href=\"Delete?albumId=" + album.getId() + "&photoId=" + photo.getId() + "\">Delete</a> ");
		out.println("			<a class='btn btn-secondary' href=\"AlbumView?albumId=" + album.getId() + "\">");
		out.println("				Return to Album");
		out.println("			</a>");	
		out.println("		</div>");
		out.println("	</div>");
		out.println("	<hr>");	
		out.println("	<div class=\"row align-items-center\">");
		
		out.println("	<img style=\"max-width: 100%; height: auto;\" src=\"Download?albumId=" + album.getId() + "&photoId=" + photo.getId() + "\">");
		out.println("	<hr>");		
	
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
	