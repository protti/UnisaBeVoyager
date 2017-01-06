package servletTravel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TravelSubsystem.Travel;
import TravelSubsystem.TravelController;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class JoinTravel
 */
@WebServlet("/JoinTravel")
public class JoinTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinTravel() {
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

		Travel travel = (Travel) session.getAttribute("travel");
		session.removeAttribute("travel");
		int travelID = travel.getId();
		synchronized (session) {
			if(travel != null) {
				boolean b = TravelController.addUserInTravel(user, travel);
				if(b == false) {
					response.sendRedirect("500page.html");
					return;
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("object/travelPage.jsp");
					request.setAttribute("travel", travel);
					rd.forward(request, response);
					return;
				}
			}	
		}
		
	}

}
