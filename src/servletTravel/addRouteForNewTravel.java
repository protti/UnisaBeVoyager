package servletTravel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class addRouteForNewTravel
 */
@WebServlet("/addRouteForNewTravel")
public class addRouteForNewTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addRouteForNewTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int routeID = Integer.parseInt(request.getParameter("id"));

		Route route = RouteController.getRoute(routeID) ;
		
		if(route == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			HttpSession session = request.getSession();
			synchronized(session) {
				session.setAttribute("selectedRoute", route);
				PrintWriter out = response.getWriter();
				out.println("<a href=\"" + request.getContextPath() + "/ShowRoute?id="
						+ "" + route.getId() + "\">" + route.getName() + "</a>");
			}
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
