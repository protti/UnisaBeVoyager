package servletTravel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;

/**
 * Servlet implementation class ShowTravel
 */
@WebServlet("/ShowTravel")
public class ShowTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int travelID = Integer.parseInt(request.getParameter("id"));
		
		Travel travel = TravelController.getTravel(travelID) ;
		
		if(travel == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			request.setAttribute("travel", travel);
			RequestDispatcher rd = request.getRequestDispatcher("travelPage.jsp");
			rd.forward(request, response);
			return;
		}	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
