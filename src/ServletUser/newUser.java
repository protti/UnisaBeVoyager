package ServletUser;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
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
			String password = request.getParameter("secret");
			String birthDate = request.getParameter("birth");
			String username = request.getParameter("username");
			
			if(email.equals("") || nome.equals("") || cognome.equals("") || password.equals("")
					|| birthDate.equals("") || username.equals("")) {
				
				request.setAttribute("dati_mancanti", true);
				RequestDispatcher rd = request.getRequestDispatcher("create/newUser.jsp");
				rd.forward(request, response);
			}
			
			RegisteredUser user = UserController.createUser(email, username, password, nome, cognome, birthDate);
			
			if(user == null){
				response.sendRedirect("create/newUser.jsp");
				return;
			}
			else {
				session.setAttribute("user", user);
				/*request.setAttribute("nome", user.getNome());
				request.setAttribute("cognome", user.getCognome());
				request.setAttribute("username", user.getUsername());
				request.setAttribute("eta", user.getAge());*/
				request.setAttribute("user", user);				
				RequestDispatcher rd = request.getRequestDispatcher("object/profilePage.jsp");
				rd.forward(request, response);
			}
			
		}
	}

}
