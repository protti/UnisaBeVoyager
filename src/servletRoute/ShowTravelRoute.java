package servletRoute;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FeedbackSubsystem.FeedbackController;
import FeedbackSubsystem.FeedbackRoute;
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;

/**
 * Servlet implementation class ShowTravelRoute
 */
@WebServlet("/ShowTravelRoute")
public class ShowTravelRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTravelRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer routeID = Integer.parseInt(request.getParameter("id"));
		Integer travelID = Integer.parseInt(request.getParameter("idt"));
		Travel travel = TravelController.getTravel(travelID);
		Route route = RouteController.getRoute(routeID) ;
		List<FeedbackRoute> fbs = FeedbackController.searchFeedbackRoute(route.getId());
		if(route == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			/*request.setAttribute("nome", route.getName());
			request.setAttribute("descrizione", route.getDescription());
			request.setAttribute("locationList", route.getLocations());*/
			request.setAttribute("travel", travel);
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
