package FeedbackServlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FeedbackSubsystem.Feedback;
import FeedbackSubsystem.FeedbackController;
import LocationSubsystem.Location;
import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class GiveFeedback
 */
@WebServlet("/GiveFeedback")
public class GiveFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiveFeedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(timestamp);
		
		Route route = null;
		Location location = null;
		RegisteredUser shownUser = null;
		Feedback fb = null; 
		if((shownUser = (RegisteredUser) session.getAttribute("shownUser")) != null) {
			fb = FeedbackController.createFeedback(user, shownUser, message, date); 
		}
		if((route = (Route) session.getAttribute("route")) != null) {
			fb = FeedbackController.createFeedback(user, route, message, date);
		}
		if((location = (Location) session.getAttribute("location")) != null) {
			fb = FeedbackController.createFeedback(user, location, message, date);
		}
		
		if(fb == null) {
			response.sendRedirect("500page.jsp");
			return;
		}
	}
}
