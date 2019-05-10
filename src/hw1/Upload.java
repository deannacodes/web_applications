package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/photos/Upload")
public class Upload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    	
    	
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
    	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Upload A File</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div style='padding-top:50px;' class=\"container\">");
		
		if (request.getParameter("error") != null && request.getParameter("error").equals("noImage")) {
    		out.println("<div class=\"alert alert-danger\" role=\"alert\">" + 
    					"  Please choose an image to upload." + 
    					"</div>");
    	}
		
		out.println("<div class=\"card\">" + 
				"		<h1 class=\"card-header\">Upload Image</h1>" + 
				"		<div class=\"card-body\">	" + 
				"			<form action=\"Upload\" method=\"post\" enctype=\"multipart/form-data\">" + 
				"			<div class=\"form-group\">" + 
				"				<label for=\"file\">File:</label>" + 
				"				<input type=\"file\" name=\"file\" />" + 
				"			</div>\r\n" + 
				"			<input type=\"hidden\" name=\"albumId\" value=\"" + album.getId() + "\" /> " + 
				"			<input type=\"submit\" value=\"Submit\" class=\"btn btn-primary\"/>" + 
				"           <a href='AlbumView?albumId=" + album.getId() + "' class='btn btn-secondary'>Cancel Upload<a/>" +
				"			</form>" + 
				"		</div>" + 
				"	</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
		
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig()
            .getServletContext();
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        
        factory.setRepository( repository );

        ServletFileUpload upload = new ServletFileUpload( factory );
        String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads");
        
        int alId = -1;

        try
        {
            List<FileItem> items = upload.parseRequest( request );
            
            for( FileItem item : items )
            {
                if( item.isFormField() )
                {
                	alId = Integer.parseInt(item.getString());

                } 
            }
            
            for( FileItem item : items )
            {
                if( !item.isFormField() )
                {
                	
                	ArrayList<PhotoAlbum> photoAlbums = (ArrayList<PhotoAlbum>) getServletContext().getAttribute("photoAlbums");
            		int albumFoundId = -1;
            		
            		for ( int i = 0 ; i < photoAlbums.size() ; i++) {
            			if (photoAlbums.get(i).getId() == alId) {
            				albumFoundId = i;
            			}
            		}
            		
            		PhotoAlbum album = photoAlbums.get(albumFoundId);
            		            		
                    uploadPath = album.getLocation();          
                	
                    String fileName = (new File( item.getName() )).getName();
                    File file = new File( uploadPath, fileName );
                    item.write( file );
                    
                    Photo photo = new Photo(fileName, (uploadPath + "/" + fileName));
                    album.addPhoto(photo);
                    
            		response.sendRedirect("AlbumView?albumId=" + album.getId());
                    
                } 
            }

        } catch( Exception e ) {
        	
        	response.sendRedirect("Upload?albumId=" + alId + "&error=noImage");

        }


    }

}