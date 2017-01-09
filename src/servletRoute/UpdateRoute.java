package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;

/**
 * Servlet implementation class UpdateRoute
 */
@WebServlet("/UpdateRoute")
public class UpdateRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idRoute = Integer.parseInt(request.getParameter("idr"));
		int idTravel = Integer.parseInt(request.getParameter("idt"));
		String name = request.getParameter("nameRoute");
		String description = request.getParameter("descRoute");
		
		Route route = RouteController.getRoute(idRoute);
		Travel travel = TravelController.getTravel(idTravel);
		
		if(name.length() > 0){
			route.setName(name);
		}
		
		if(description.length() > 0){
			route.setDescription(description);
		}
		
		boolean b = RouteController.updateRoute(route);
		
		if(b == false){
			response.sendRedirect("500page.html");
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("object/routePage.jsp");
			request.setAttribute("route", route);
			request.setAttribute("travel", travel);
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
