package servletTravel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

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
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;
import TravelSubsystem.TravelManager;
import UserSubsystem.RegisteredUser;
import RouteSubsystem.*;

/**
 * Servlet implementation class createTravel
 */
@WebServlet("/createTravel")
public class createTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("global"); 
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		/*HttpSession session = request.getSession();
		synchronized (session) {
			
			
			Location loc = new Location("Milano","Bellissimo");
			Location loca = new Location("Malpensa","Bellissimo");
			
			LocationController.createLocation("Milano", "Bellissimo");
			LocationController.createLocation("Malpensa", "Bellissimo");
			
			ArrayList<Location> locs = new ArrayList<Location>();
			 //(Route)session.getAttribute("route");
			Route route;
			route = RouteController.createRoute(locs, "Giro a milano", "Terun");
			RouteController.addLocationToRoute(loc, route);
			RouteController.addLocationToRoute(loca, route);
			
			RegisteredUser creatoreViaggio = (RegisteredUser)session.getAttribute("user");
			String nome = request.getParameter("nome");
			
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("startDate");
			String types = request.getParameter("type");
			boolean type;
			if(types.equals("Y"))
				type = true;
			else
				type=false;
			
			
			Travel travel = TravelController.createTravel(nome,route,creatoreViaggio, startDate, endDate, type);
			String page = null;
			
			if(travel == null)
			{
				page = "500page.html";
			}
			else{
				page = "profilePage.jsp";
			}
			
			response.sendRedirect(page);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String travelName = request.getParameter("nome");
		String travelDesc = request.getParameter("descrizione");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Boolean type = Boolean.parseBoolean(request.getParameter("type"));
		
		if(travelName.equals("") || travelDesc.equals("") || 
				startDate.equals("") || endDate.equals("")){
			request.setAttribute("dati_mancanti", true);
			RequestDispatcher rd = request.getRequestDispatcher("create/createTravel.jsp");
			rd.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		Route selectedRoute = (Route) session.getAttribute("selectedRoute");
				
		if(selectedRoute == null) {
			response.sendRedirect("500page.html");
			return;
		}

		Travel newTravel = TravelController.createTravel(travelName, selectedRoute, user, startDate, endDate, type);
		if (newTravel == null) {
			response.sendRedirect("500page.html");
			return;
		}
				

		synchronized(session){
			session.removeAttribute("selectedRoute");
			request.setAttribute("travel", newTravel);
			RequestDispatcher rd = request.getRequestDispatcher("object/travelPage.jsp");
			rd.forward(request, response);
		}	

	}

}
