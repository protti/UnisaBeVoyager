package servletTravel;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import RouteSubsystem.RouteController;
import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class DeleteTravel
 */
@WebServlet("/DeleteTravel")
public class DeleteTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger("global");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTravel() {
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
			return;
		}
		
		Travel travel = (Travel) session.getAttribute("travel");
		session.removeAttribute("travel");
		int idTravel = travel.getId();
		logger.info("" + idTravel);
		synchronized(session){
			Boolean b = TravelController.deleteTravel(idTravel);
			if(b != true){
				response.sendRedirect("500page.html");
				return;
			}
		}
		response.sendRedirect("correctResult.jsp");
		return;
	}

}
