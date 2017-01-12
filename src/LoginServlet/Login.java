package LoginServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AccessController.AccessController;
import FeedbackSubsystem.FeedbackController;
import FeedbackSubsystem.FeedbackUser;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RegisteredUser user = AccessController.logUser(username, password);
		
		if (username.equals("") || password.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("dati_mancanti", true);
			rd.forward(request, response);
			return;
		}
		
		if (user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("wrong", true);
			rd.forward(request, response);
			return;
		}
		List<FeedbackUser> fbs = FeedbackController.searchFeedbackUser(user.getId());
		HttpSession session = request.getSession();		
		synchronized(session) {
			System.out.println("Login effettuato");
			//response.sendRedirect("profilePage.jsp");
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			request.setAttribute("feedbacks", fbs);
			request.setAttribute("id", user.getId());
			RequestDispatcher rd = request.getRequestDispatcher("object/profilePage.jsp");
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
