package FeedbackServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import FeedbackSubsystem.Feedback;
import FeedbackSubsystem.FeedbackController;
import UserSubsystem.RegisteredUser;

/**
 * Servlet implementation class GiveFeedbackToRoute
 */
@WebServlet("/GiveFeedbackToRoute")
public class GiveFeedbackToRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger("global");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiveFeedbackToRoute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
logger.info("Sono dentro");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String message = request.getParameter("message");
		if(message.equals("")){
			PrintWriter out = response.getWriter();
			out.println("Inserisci un feedback prima<br>");
			return;
		}
		HttpSession session = request.getSession();
		RegisteredUser user = (RegisteredUser) session.getAttribute("user");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(timestamp);
		
		Feedback fb = FeedbackController.createFeedbackRoute(user, id, message, date);
		
		if (fb == null) {
			response.sendRedirect("500page.jsp");
			return;
		}
	
		PrintWriter out = response.getWriter();
		
		if(fb.getId() == 0){
			out.println("<b>Non hai mai preso parte a questo itinerario</b>");
			return;
		}
		
		out.println("<a href=\"showProfile?id=" + fb.getSender().getId() + "\">" + 
				fb.getSender().getUsername() + "</a>" + 
				"<p>" + fb.getMessage() + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
