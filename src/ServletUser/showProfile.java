package ServletUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		int userID = Integer.parseInt(request.getParameter("id"));
		
		RegisteredUser user = UserController.getUser(userID);
		
		
		if(user == null) {
			response.sendRedirect("500page.html");
		}
		else {
			request.setAttribute("nome", user.getNome());
			request.setAttribute("cognome", user.getCognome());
			request.setAttribute("username", user.getUsername());
			request.setAttribute("eta", user.getAge());
			RequestDispatcher rd = request.getRequestDispatcher("profilePage.jsp");
			rd.forward(request, response);
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
