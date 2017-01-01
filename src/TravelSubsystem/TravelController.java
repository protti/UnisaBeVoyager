package TravelSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import DBConnection.DBException;
import UserSubsystem.RegisteredUser;
import TravelSubsystem.TravelManager;

public class TravelController {


	public static boolean addUserInTravel(RegisteredUser user, Travel travel) {
		
		boolean res = travel.addUserToTravel(user);
		
		if (res = false) {
			return false;
		}
		
		try {
			TravelManager.updateTravel(travel);
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean deleteTravel(int idTravel) {
		try {
			TravelManager.deleteTravel(idTravel);
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static Travel createTravel(RegisteredUser creatoreViaggio, GregorianCalendar startDate, GregorianCalendar endDate, boolean type) {

		Travel travel = new Travel(creatoreViaggio, startDate, endDate, type);
		return travel;
	}
	
	public static boolean confirmTravel(Travel travel) {
		
		travel.closeTravel();

		try {
			TravelManager.updateTravel(travel);		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}
		
		
		return true;
	}
	
}
