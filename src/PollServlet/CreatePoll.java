package PollServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import LocationSubsystem.Location;
import PollSubsystem.Poll;
import PollSubsystem.PollController;
import RouteSubsystem.Route;
import RouteSubsystem.RouteController;

/**
 * Servlet implementation class CreatePoll
 */
@WebServlet("/CreatePoll")
public class CreatePoll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePoll() {
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
		String pollDesc = request.getParameter("descrizione");
		String pollStart = request.getParameter("startDate");
		String pollEnd = request.getParameter("endDate");
		int travelID = Integer.parseInt(request.getParameter("travelID"));
		
		HttpSession session = request.getSession();
		ArrayList<Location> currentList = (ArrayList<Location>) session.getAttribute("currentList");
				
		if(currentList == null) {
			response.sendRedirect("500page.html");
			return;
		}

		Poll newPoll = PollController.createPoll(pollDesc, pollStart, pollEnd, travelID);
		if (newPoll == null) {
			response.sendRedirect("500page.html");
			return;
		}
				

		synchronized(session){
			request.setAttribute("poll", newPoll);
			RequestDispatcher rd = request.getRequestDispatcher("object/pollPage.jsp");
			rd.forward(request, response);
		}	}

}
