package lab6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Lab6/Play")
public class PlayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		try {

			GameBean game = (GameBean) request.getSession().getAttribute("game");
			int row = Integer.parseInt(request.getParameter("row"));
			int col = Integer.parseInt(request.getParameter("col"));
			
			if (!game.isGameOver()) {
				game.setBoardVal(row, col);
				
				if (game.isGameOver()) {
					if (game.getWinningPlayer() == 0) {
						Integer score = (Integer) request.getSession().getAttribute("xScore");
						score++;
						request.getSession().setAttribute("xScore", score);
					} else if ( game.getWinningPlayer() == 1) {
						Integer score = (Integer) request.getSession().getAttribute("oScore");
						score++;
						request.getSession().setAttribute("oScore", score);
					} else {
						Integer score = (Integer) request.getSession().getAttribute("ties");
						score++;
						request.getSession().setAttribute("ties", score);
					}
				}
				
			} 
			
			
			
		}catch(Exception e) {}
		
		response.sendRedirect("Game");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
