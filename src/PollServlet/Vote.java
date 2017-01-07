package PollServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PollSubsystem.Poll;
import PollSubsystem.PollController;
import PollSubsystem.PollManager;
import TravelSubsystem.Travel;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class Vote
 */
@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Vote() {
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
		int vote = Integer.parseInt(request.getParameter("vote"));
		int pollID = Integer.parseInt(request.getParameter("pollID"));

		boolean hasVoted = false;
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		System.out.println("ECCOMI QUI");

		int userID = user.getId();

		if (PollController.hasUserVoted(userID, pollID)) {
			PrintWriter pw = response.getWriter();
			//pw.println("Hai già votato!");
			pw.println("<p>Hai già votato in questo sondaggio!</p><br>");
			return;
		}
		
		if(hasVoted == false){
			boolean b = PollController.updatePoll(pollID, vote, userID);
			Poll poll = PollController.getPoll(pollID);
			if (b == false) {
				response.sendRedirect("500page.html");
				return;
			} else {
				PrintWriter pw = response.getWriter();
				pw.println("Grazie per il voto");
			}
		}
	}

}
