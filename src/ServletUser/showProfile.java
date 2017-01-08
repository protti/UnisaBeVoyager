package ServletUser;

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
import FeedbackSubsystem.FeedbackUser;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserController;

/**
 * Servlet implementation class showProfile
 */
@WebServlet("/showProfile")
public class showProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.isNew()) {
			response.sendRedirect("500page.html");
			return;
		}
		
		RegisteredUser temp = (RegisteredUser) session.getAttribute("user");
		RegisteredUser user = null;
		
		if(temp == null) {
			response.sendRedirect("500page.html");
			return;
		}		
		
		int userID = Integer.parseInt(request.getParameter("id"));

		if (temp.getId() != userID) {
			user = UserController.getUser(userID);
		} else {
			user = temp;
		}		
		
		List<FeedbackUser> fbs = FeedbackController.searchFeedbackUser(user.getId());
		
		if(user == null) {
			response.sendRedirect("500page.html");
			return;
		}
		else {
			request.setAttribute("user", user);
			request.setAttribute("id", user.getId());
			request.setAttribute("feedbacks", fbs);
			RequestDispatcher rd = request.getRequestDispatcher("object/profilePage.jsp");
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
