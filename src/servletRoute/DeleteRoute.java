package servletRoute;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		synchronized(session){
			Boolean b = RouteController.deleteRoute(idRoute);
			if(b != true){
				response.sendRedirect("500page.html");
			}
		}
		//Bisognerebbe aggiungere un redirect a qualcosa qui
	
	}

}
