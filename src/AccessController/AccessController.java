package AccessController;

import java.sql.SQLException;

import DBConnection.DBException;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;
/**
 * Classe che opera sugli accessi.
 * @author Salvatore
 *
 */
public class AccessController {


	/**
	 * Metodo che recupera la password di un utente.
	 * @param email
	 * @return user
	 */
	static public RegisteredUser recoveryPwd(String email)
	{
		try {
			RegisteredUser user = UserManager.getUserByEmail(email);
			return user;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	/**
	 * Metodo che aggiorna la password.
	 * @param email
	 * @param password
	 * @return true se ok, false altrimenti
	 */
	static public boolean updatePassword(String email,String password)
	{
		try {
			if(UserManager.controlEmail(email)){
				RegisteredUser user = UserManager.getUserByEmail(email);
				if(user == null) return false;
				UserManager.changePassword(user.getId(), password);
				return true;
			}
			else return false;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	static public boolean controlLog(int idUser)
	{
		return false;
	}
		
	static public boolean controlLogOut(int idUser)
	{
		return false;
	}
	
	/**
	 * Metodo che restituisce l'accesso dell'utente
	 * @param username
	 * @param password
	 * @return user
	 */
	static public RegisteredUser logUser(String username,String password)	{
		try {
			RegisteredUser user = UserManager.getUser(username, password);
			System.out.println(password);
			if (user == null) return null;
				return user;
			
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}

}
