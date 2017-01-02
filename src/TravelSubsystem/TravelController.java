package TravelSubsystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import UserSubsystem.RegisteredUser;
import TravelSubsystem.TravelManager;

public class TravelController {

	
	/**
	*Metodo che controlla se un utente � in quel viaggio.
	*@param idUtente
	*@return true se c'�, false se non c'�
	*
	*/
	private static boolean isUserInTravel(int idUtente, ArrayList<RegisteredUser> users) {
		for (int i = 0; i < users.size(); i++) {
			if (idUtente == users.get(i).getId()) {
				return false;
			}
		}
		return true;
	}
	
	/* OCL
	 * context TravelController::addUserInTralve(user, travel) pre:
	 * 	not travel.partecipantiViaggio->includes(user)
	 * 
	 * context TravelController::addUserInTralve(user, travel) post
	 * 	travel.partecipantiViaggio->includes(user)
	 */
	
	public static boolean addUserInTravel(RegisteredUser user, Travel travel) {
		
		ArrayList<RegisteredUser> users = travel.getPartecipantiViaggio();
		
		
		if (!isUserInTravel(user.getId(), users)) {
			return false;
		}
		
		travel.addUserToTravel(user);
				
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
	
	/*
	 * 
	 * @param idTravel
	 * @return
	 */
	
	/* OCL
	 * context TravelController::deleteTravel(idTravel) pre
	 * 	
	 */
	
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
	

	/* OCL
	 * context TravelController::confirmTravel(travel) pre:
	 * 	not travel.isConfirmed()
	 * 
	 * context TravelController::confirmTravel(travel) post:
	 * 	travel.isConfirmed()
	 */
	
	public static boolean confirmTravel(Travel travel) {
		
		travel.closeTravel();

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
	
	public static List<Travel> searchTravel(String location) {
		
		List<Travel> results;
		
		try {
			results = TravelManager.searchTravelByLocation(location);
			if (results.isEmpty()) {
				results = TravelManager.searchTravelById(location);
			}
		}
		catch (DBException e) {
			return null;
		}
		catch (SQLException e) {
			return null;
		}	
		
		if(!results.isEmpty()) {
			return results;
		} else {
			return null;
		}
	}
	
}
