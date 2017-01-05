package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import LocationSubsystem.LocationManager;

/**
 * Servlet implementation class SearchLocationForRoute
 */
@WebServlet("/SearchLocationForRoute")
public class SearchLocationForRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLocationForRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = request.getParameter("nameLocation");
		List<Location> locations = LocationController.searchLocations(name);
		PrintWriter out = response.getWriter();
		synchronized(session){
			if(locations != null){
				if(locations.size() > 0){
					for(Location location:locations){
						out.println("<p>" + location.getName() + "</p> "
								+ "<button onclick=addToList(" + location.getId() + ")>Aggiungi luogo"
										+ "</button>");
					}
				}
				else{
					out.println("Nessun luogo trovato");
				}
			}
			else{
				out.println("Nessun luogo trovato");
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
