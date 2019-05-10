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



@WebServlet("/photos/Delete")
public class Delete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<PhotoAlbum> photoAlbums = (ArrayList<PhotoAlbum>) getServletContext().getAttribute("photoAlbums");

		int deleteAlbumId = Integer.parseInt(request.getParameter("albumId")); 
		
		int albumFoundId = -1;
		
		for ( int i = 0 ; i < photoAlbums.size() ; i++) {
			if (photoAlbums.get(i).getId() == deleteAlbumId) {
				albumFoundId = i;
			}
		}
		
		PhotoAlbum album = photoAlbums.get(albumFoundId);		
		
		
		if (request.getParameter("albumId") != null) {
			
			if (request.getParameter("photoId") != null) {
				int deletePhotoId = Integer.parseInt(request.getParameter("photoId"));
				
				Photo deleteMe = album.getPhoto(deletePhotoId);
				File file  = new File(deleteMe.getLocation());
				file.delete();
				
				album.removePhoto(deletePhotoId);
				
				response.sendRedirect("AlbumView?albumId=" + album.getId());
				return;
				
			} else {
			
				File file  = new File(album.getLocation());


				PrintWriter out = response.getWriter();
				
				
				String[] innerFiles = file.list();
		        if(innerFiles == null) {
		        
		        	file.delete();
		 
		        } else {
		        	for (String innerPath :  innerFiles) {
		        		File currentFile = new File(file.getPath(),innerPath);
		        	    currentFile.delete();
		        		
		            }
		        	
		        	file.delete();
		        }
		        
		        photoAlbums.remove(albumFoundId);
		        
		        response.sendRedirect("Albums");
		        
			}
		}

		
		
		
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
	