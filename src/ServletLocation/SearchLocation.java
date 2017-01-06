package ServletLocation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LocationSubsystem.Location;
import LocationSubsystem.LocationManager;

/**
 * Servlet implementation class SearchLocation
 */
@WebServlet("/SearchLocation")
public class SearchLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = (String) request.getParameter("search");
		
		if(location.equals("")) {
			response.sendRedirect("search-location.jsp");
			return;
		}
		
		List<Location> locations = null;
		try {
			locations = LocationManager.searchLocations(location);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("500page.html");
			return;
		}
		System.out.println("Eccomi");
		System.out.println(locations.get(0).getDescrizione());

		if (locations != null) {
		
			request.setAttribute("locations", locations);
			RequestDispatcher rd = request.getRequestDispatcher("research/search-location-result.jsp");
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
