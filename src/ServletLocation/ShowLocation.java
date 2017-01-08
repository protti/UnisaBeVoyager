package ServletLocation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FeedbackSubsystem.FeedbackController;
import FeedbackSubsystem.FeedbackLocation;
import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import LocationSubsystem.LocationManager;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserController;

/**
 * Servlet implementation class ShowLocation
 */
@WebServlet("/ShowLocation")
public class ShowLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLocation() {
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
			List<FeedbackLocation> fbs = FeedbackController.searchFeedbackLocation(location.getId());
			/*request.setAttribute("nome", location.getName());
			request.setAttribute("descrizione", location.getDescrizione());*/
			
			request.setAttribute("location", location);
			request.setAttribute("id", location.getId());
			request.setAttribute("feedback", fbs);
			RequestDispatcher rd = request.getRequestDispatcher("object/locationpage.jsp");
			rd.forward(request, response);
			return;
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
