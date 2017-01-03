package AccessController;

import java.sql.SQLException;

import DBConnection.DBException;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;

public class AccessController {



	static public RegisteredUser recoveryPwd(String username)
	{
		try {
			RegisteredUser user = UserManager.getUser(username);
			return user;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
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
	
	static public RegisteredUser logUser(String username,String password)
	{
		return null;
	}

}
