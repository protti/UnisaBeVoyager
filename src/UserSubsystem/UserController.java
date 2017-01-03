package UserSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;

public class UserController {


	
	/*
	 * Prima era fetchUser, l'ho modificato per essere coerente con i sequence diagram
	 * Questo serve per la visualizzazione del profilo di un utente. Quando per esempio un utente clicca su "Il mio profilo" oppure fa la ricerca di un utente,
	 * fa prima "searchUser" e poi quando clicca sul risultato usa getUser, quindi non prnede username e password, ma un id
	 * 
	 */
	
		static public RegisteredUser getUser(int id)
		{
			RegisteredUser user;
			try {
				user = UserManager.fetchUser(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				user = null;
			} catch (DBException e) {
				// TODO Auto-generated catch block
				user = null;
			}
			return user;
		}
	
		static public List<RegisteredUser> searchUser(String username)
		{
			List<RegisteredUser> listUsers;
			
			try {
				listUsers = UserManager.searchUsers(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				listUsers = null;
			}
			return listUsers;
		}
		
		static public RegisteredUser createUser(String email, String username, String password, String nome, String cognome,
				GregorianCalendar birthDate, int age)
		{
			RegisteredUser user = new RegisteredUser(email,username,password,nome,cognome,
					birthDate,age);
			try {
				UserManager.saveUserToDB(user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			} catch (DBException e) {
				// TODO Auto-generated catch block
				return null;
			}
			
			return user;
		}
		
		/*static public RegisteredUser getUser()
		{
			
		}*/
		
		static public boolean deleteUser(int id)
		{
			try {
				UserManager.deleteUser(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			} catch (DBException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
	
}
