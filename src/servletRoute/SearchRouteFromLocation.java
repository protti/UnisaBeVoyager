package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBConnection.DBException;
import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import LocationSubsystem.LocationManager;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;

/**
 * Servlet implementation class SearchRouteFromLocation
 */
@WebServlet("/SearchRouteFromLocation")
public class SearchRouteFromLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRouteFromLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = (String) request.getParameter("search");
		
		if(location.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("research/search-route-result.jsp");
			request.setAttribute("dati_mancanti", true);
			rd.forward(request, response);
			return;
		}
		
		List<Route> routes = null;
		try {
			routes = RouteManager.searchRoutesByLocation(location);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("500page.html");
			return;
		} catch (DBException e) {
			e.printStackTrace();
			response.sendRedirect("500page.html");
			return;
		}
		
		if (routes != null) {
			request.setAttribute("routes", routes);
			RequestDispatcher rd = request.getRequestDispatcher("research/search-route-result.jsp");
			rd.forward(request, response);
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
