package servletTravel;

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
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;

/**
 * Servlet implementation class SearchRouteForTravel
 */
@WebServlet("/SearchRouteForTravel")
public class SearchRouteForTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRouteForTravel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("location");
		if(name.equals("")) return;

		
		List<Route> routes = RouteController.searchRoute(name);
		PrintWriter out = response.getWriter();
		synchronized(session){
			if(routes != null){
				if(routes.size() > 0){
					for(Route route : routes){
						out.println("<p>" + route.getName() + "</p> " 
								+ "<button onclick=addToList(" + route.getId() + ")>Aggiungi itinerario"
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
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
