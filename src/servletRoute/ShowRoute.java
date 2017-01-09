package servletRoute;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FeedbackSubsystem.FeedbackController;
import FeedbackSubsystem.FeedbackRoute;
import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;

/**
 * Servlet implementation class ShowRoute
 */
@WebServlet("/ShowRoute")
public class ShowRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer routeID = Integer.parseInt(request.getParameter("id"));
		
		Route route = RouteController.getRoute(routeID) ;
		List<FeedbackRoute> fbs = FeedbackController.searchFeedbackRoute(route.getId());
		if(route == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			request.setAttribute("feedback", fbs);
			request.setAttribute("id", route.getId());
			request.setAttribute("route", route);
			RequestDispatcher rd = request.getRequestDispatcher("object/routePage.jsp");
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
