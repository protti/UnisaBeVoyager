package servletTravel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBConnection.DBException;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelManager;

/**
 * Servlet implementation class SearcTravelFromLocation
 */
@WebServlet("/SearcTravelFromLocation")
public class SearcTravelFromLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearcTravelFromLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = (String) request.getParameter("search");
		
		if(location.equals("")) {
			response.sendRedirect("search-travel-location.jsp");
			return;
		}
		
		List<Travel> travels = null;
		try {
			travels = TravelManager.searchTravelByLocation(location);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("500page.html");
			return;
		} catch (DBException e) {
			e.printStackTrace();
			response.sendRedirect("500page.html");
			return;
		}
		if (travels != null) {
			request.setAttribute("travels", travels);
			RequestDispatcher rd = request.getRequestDispatcher("search-travel-result.jsp");
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
