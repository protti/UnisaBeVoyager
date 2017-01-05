package servletTravel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;
import TravelSubsystem.TravelManager;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class createTravel
 */
@WebServlet("/createTravel")
public class createTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createTravel() {
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
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		synchronized (session) {
			
			RegisteredUser creatoreViaggio = (RegisteredUser)session.getAttribute("user");
			String nome = request.getParameter("nome");
			
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("startDate");
			String types = request.getParameter("type");
			boolean type;
			if(types == "Y")
				type = true;
			else
				type=false;
			Travel travel = TravelController.createTravel(nome,creatoreViaggio, startDate, endDate, type);
			String page = null;
			if(travel == null)
			{
				page = "500page.html";
			}
			else{
				page = "profilePage.jsp";
			}
			
			response.sendRedirect(page);
		}
		doGet(request, response);
	}

}