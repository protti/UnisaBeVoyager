package UserSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;

public class UserController {

		static public RegisteredUser fetchUser(int id)
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
		
		/*static public RegisteredUser getUser(String username,String password)
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
