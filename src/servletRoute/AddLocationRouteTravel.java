package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import LocationSubsystem.LocationManager;
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import RouteSubsystem.RouteManager;

/**
 * Servlet implementation class AddLocationRouteTravel
 */
@WebServlet("/AddLocationRouteTravel")
public class AddLocationRouteTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLocationRouteTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int locationID = Integer.parseInt(request.getParameter("idLocation"));
		int routeID = Integer.parseInt(request.getParameter("idRoute"));
		
		Location location = LocationController.getLocation(locationID);
		Route route = RouteController.getRoute(routeID);
		
		boolean b = RouteController.addLocationToRoute(location, route);
		
		if(b == false){
			response.sendRedirect("500page.html");
			return;
		}
		else{
			PrintWriter out = response.getWriter();
			out.println("<b>Luogo aggiunto all'itinerario</b>");
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
