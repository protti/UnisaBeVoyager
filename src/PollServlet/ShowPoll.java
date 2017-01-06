package PollServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PollSubsystem.Poll;
import PollSubsystem.PollController;

/**
 * Servlet implementation class ShowPoll
 */
@WebServlet("/ShowPoll")
public class ShowPoll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPoll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pollID = Integer.parseInt(request.getParameter("id"));
		
		Poll poll = PollController.fetchPoll(pollID);
		
		
		if(poll == null){
			response.sendRedirect("500page.html");
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("object/pagePoll.jsp");
			request.setAttribute("poll", poll);
			rd.forward(request, response);
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
