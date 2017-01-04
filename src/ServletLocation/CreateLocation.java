package ServletLocation;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LocationSubsystem.Location;
import LocationSubsystem.LocationController;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class CreateLocation
 */
@WebServlet("/CreateLocation")
public class CreateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("global"); 
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		if(user.getAuthorization() > 0){
			String name = request.getParameter("nome");
			String description = request.getParameter("descrizione");
			synchronized(session){
				Location location = LocationController.createLocation(name,description);
				if(location == null){
					response.sendRedirect("creaLuogo.html");
				}
				else{
					//crea luogo
					logger.info("Il luogo � stato creato!!!");
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
