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
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class GiveFeedbackToUser
 */
@WebServlet("/GiveFeedbackToUser")
public class GiveFeedbackToUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiveFeedbackToUser() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(timestamp);
		
		Feedback fb = FeedbackController.createFeedbackUser(user, id, message, date);
		
		if (fb == null) {
			response.sendRedirect("500page.jsp");
		}
	}

}
