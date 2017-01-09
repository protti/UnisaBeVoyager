package servletRoute;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class AddToLocationList
 */
@WebServlet("/AddToLocationList")
public class AddToLocationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToLocationList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int locationID = Integer.parseInt(request.getParameter("id"));
		Location location = LocationController.getLocation(locationID) ;
		
		if(location == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			HttpSession session = request.getSession();
			List<Location> currentList = (List<Location>) session.getAttribute("currentList");
			if (currentList == null) {
				currentList = new ArrayList<Location>();
			}
			currentList.add(location);
			synchronized(session) {
				session.setAttribute("currentList", currentList);
				PrintWriter out = response.getWriter();
				for(Location loc: currentList){
					out.println("<a href=\"../ShowLocation?id=" + loc.getId() + "\">" + loc.getName() + "</a>");
				}
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
