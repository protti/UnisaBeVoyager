package TravelSubsystem;

import java.util.GregorianCalendar;

import UserSubsystem.RegisteredUser;
import TravelSubsystem.TravelManager;

public class TravelController {


	public static boolean addUserInTravel(RegisteredUser usedr, Travel travel) {
		
		TravelManager.updateTravel(travel);
		return true;
	}

	
	public static boolean deleteTravel(int idTravel) {

		return true;
	}
	
	public static Travel createTravel(RegisteredUser creatoreViaggio, GregorianCalendar startDate, GregorianCalendar endDate, boolean type) {

		Travel travel = new Travel(creatoreViaggio, startDate, endDate, type);
		return travel;
	}
	
	public static boolean confirmTravel(int idTravel) {
		
		return true;
	}
	
}
