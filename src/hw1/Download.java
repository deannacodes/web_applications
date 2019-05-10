package hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/Download")
public class Download extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
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
    	Photo photo = album.getPhoto(Integer.parseInt(request.getParameter("photoId")));

        File file = new File( photo.getLocation() );

        response.setContentType( getServletContext().getMimeType( photo.getLocation() ));
        
        
        
        response.setHeader( "Content-Length", "" + file.length() );
        response.setHeader( "Content-Disposition", "inline; filename=" + photo.getName() );

        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
    }

}

