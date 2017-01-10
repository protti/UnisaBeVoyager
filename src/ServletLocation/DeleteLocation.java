package ServletLocation;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class DeleteLocation
 */
@WebServlet("/DeleteLocation")
public class DeleteLocation extends HttpServlet {
	private static Logger logger = Logger.getLogger("global"); 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
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
		String id = request.getParameter("id");
		Location location = LocationController.getLocation(Integer.parseInt(id));
		synchronized(session){
			String page = null;
			Boolean b = LocationController.deleteLocation(location);
			logger.info(""+b);
			if(b != true){
				
				page = "500page.html";
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("object/profilePage.jsp");
				request.setAttribute("user", user);
				rd.forward(request, response);
			}
				
			
			
			
		}
		
		
		
		//Bisognerebbe aggiungere un redirect a qualcosa qui
	}

}
