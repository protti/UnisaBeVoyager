package ServletUser;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UserSubsystem.RegisteredUser;
import UserSubsystem.UserController;

/**
 * Servlet implementation class newUser
 */
@WebServlet(name = "/ServletUser.newUser", urlPatterns = {"/newuser"})
public class newUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override

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
		synchronized(session)
		{
			String email = request.getParameter("email");
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String password = Integer.toString(request.getParameter("secret").hashCode());
			String d = request.getParameter("birth");
			String[] dt = d.split("-");
			int databd = Integer.parseInt(dt[2]);
			int databm = Integer.parseInt(dt[1]);
			int databy = Integer.parseInt(dt[0]);
			GregorianCalendar birthDate =	new GregorianCalendar(databy,databm,databd);
			String username = request.getParameter("username");
			
			RegisteredUser user = UserController.createUser(email, username, password, nome, cognome, birthDate);
			
			if(user == null)
			{
				response.sendRedirect("newuser.html");
			}
			else
			{
				session.setAttribute("UserR", user);
				
			}
			
		}
		
		
		
		doGet(request, response);
	}

}
