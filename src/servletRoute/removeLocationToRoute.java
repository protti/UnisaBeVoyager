package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;

/**
 * Servlet implementation class removeLocationToRoute
 */
@WebServlet("/removeLocationToRoute")
public class removeLocationToRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public removeLocationToRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		int idRoute = Integer.parseInt(request.getParameter("idr"));
		int idLocation = Integer.parseInt(request.getParameter("idl"));

		Route route = RouteController.getRoute(idRoute);
		Location newLocation = LocationController.getLocation(idLocation);
		boolean b = RouteController.removeLocationFromRoute(newLocation, route);

		if (b == false) {
			response.sendRedirect("500page.jsp");
			return;
		} 
		else{
			PrintWriter out = response.getWriter();
			out.println("");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
