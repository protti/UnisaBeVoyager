package servletRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class CreateRoute
 */
@WebServlet("/CreateRoute")
public class CreateRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String routeName = request.getParameter("name");
		String routeDesc = request.getParameter("descrizione");
		HttpSession session = request.getSession();
		ArrayList<Location> currentList = (ArrayList<Location>) session.getAttribute("currentList");
				
		if(currentList == null) {
			response.sendRedirect("500page.html");
			return;
		}

		Route newRoute = RouteController.createRoute(currentList, routeName, routeDesc);
		if (newRoute == null) {
			response.sendRedirect("500page.html");
			return;
		}
				

		synchronized(session){
			session.removeAttribute("currentList");
			request.setAttribute("route",newRoute);
			RequestDispatcher rd = request.getRequestDispatcher("object/routePage.jsp");
			rd.forward(request, response);
		}
	}

}
