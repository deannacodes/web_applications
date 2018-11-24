package lab6;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Lab6/Game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
				
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("game") == null) {
			GameBean game = new GameBean();		
			request.getSession().setAttribute("game", game);
		}
		
		if (request.getSession().getAttribute("xScore") == null) {
			Integer xScore = 0;
			request.getSession().setAttribute("xScore", xScore);
		}
		
		if (request.getSession().getAttribute("oScore") == null) {
			Integer oScore = 0;
			request.getSession().setAttribute("oScore", oScore);
		}
		
		if (request.getSession().getAttribute("ties") == null) {
			Integer ties = 0;
			request.getSession().setAttribute("ties", ties);
		}
		
		request.getRequestDispatcher("/WEB-INF/lab6/Game.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
