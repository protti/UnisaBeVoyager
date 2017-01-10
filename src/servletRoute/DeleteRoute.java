package servletRoute;

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
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class DeleteRoute
 */
@WebServlet("/DeleteRoute")
public class DeleteRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoute() {
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
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		if(user.getAuthorization() != 1){
			response.sendRedirect("500page.jsp");
		}
		
		int idRoute = Integer.parseInt(request.getParameter("idRoute"));
		List<FeedbackUser> fbs = FeedbackController.searchFeedbackUser(user.getId());
		synchronized(session){
			Boolean b = RouteController.deleteRoute(idRoute);
			if(b != true){
				response.sendRedirect("500page.html");
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("object/profilePage.jsp");
				request.setAttribute("feedbacks", fbs);
				request.setAttribute("user", user);
				rd.forward(request, response);
			}
		}
		//Bisognerebbe aggiungere un redirect a qualcosa qui (possibilmente una homepage)
	
	}

}
