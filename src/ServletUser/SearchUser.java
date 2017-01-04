package ServletUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UserSubsystem.RegisteredUser;
import UserSubsystem.User;
import UserSubsystem.UserController;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet(name = "/ServletUser.SearchUser", urlPatterns = {"/searchUser"})
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = (String) request.getParameter("search");
		List<RegisteredUser> users = UserController.searchUser(result);
		
		PrintWriter out = response.getWriter();
		
		if(users.size() > 0){
			for(RegisteredUser user: users){
				out.println("<a href=\"showProfile?id="+ user.getId()+"\">"+user.getNome()+"</a><br>");
			}
		}
		else{
			out.println("<h2>Nessun risultato</h2>");
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
