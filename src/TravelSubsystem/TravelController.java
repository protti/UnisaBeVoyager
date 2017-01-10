package TravelSubsystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import DBConnection.DBException;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;
import UserSubsystem.RegisteredUser;
import TravelSubsystem.TravelManager;
/**
 * Classe che effettua operazioni sui viaggi.
 * @author Salvatore
 *
 */
public class TravelController {

	
	
	
	/**
	*Metodo che controlla se un utente partecipa a quel viaggio.
	*@param idUtente
	*@return true se c'e', false se non c'e'
	*
	*/
	public static boolean isUserInTravel(int idUtente, ArrayList<RegisteredUser> users) {
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
	/**
	 * Metodo che aggiunge un utente al viaggio.
	 * @param user
	 * @param travel
	 * @return return true se va a buon fine, false altrimenti
	 */
	public static boolean addUserInTravel(RegisteredUser user, Travel travel) {
		
		ArrayList<RegisteredUser> users = travel.getPartecipantiViaggio();
		
		if(users == null){
			users = new ArrayList<RegisteredUser>();
			
		}
		
		if (!isUserInTravel(user.getId(), users) && user.getId() != travel.getCreatoreViaggio().getId()) {
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
	/**
	 * Metodo che elimina un viaggio.
	 * @param idTravel
	 * @return true se ok, false altrimenti
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
	private static Logger logger = Logger.getLogger("global"); 

	/**
	 * Metodo che crea un viaggio.
	 * @param nome
	 * @param route
	 * @param creatoreViaggio
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return travel
	 */
	public static Travel createTravel(String nome, Route route, RegisteredUser creatoreViaggio, String startDate,
			String endDate, boolean type) {
		logger.info("creo travel");
		Travel travel = new Travel(nome,route,creatoreViaggio, startDate, endDate, type);
		
		
		
		try {
			Route r = RouteManager.saveRouteToDB(route);
			logger.info("Salvo sul db");
			travel.setRoute(r);
			TravelManager.saveTravelToDB(travel);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
		return travel;
	}
	

	/* OCL
	 * context TravelController::confirmTravel(travel) pre:
	 * 	not travel.isConfirmed()
	 * 
	 * context TravelController::confirmTravel(travel) post:
	 * 	travel.isConfirmed()
	 */
	/**
	 * Metodo che conferma un viaggio.
	 * @param travel
	 * @return true se ok, false altrimenti
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
	/**
	 * Metodo che ricerca un viaggio tramite luogo.
	 * @param location
	 * @return results
	 */
	public static List<Travel> searchTravel(String location) {
		
		List<Travel> results;
		
		try {
			results = TravelManager.searchTravelByLocation(location);
		}
		catch (DBException e) {
			return null;
		}
		catch (SQLException e) {
			return null;
		}	
		
			return results;
	}

	/**
	 * Metodo che restituisce un viaggio.
	 * @param id
	 * @return result
	 */
	public static Travel getTravel(int id) {
		Travel result = null;
		try {
			result = TravelManager.fetchTravel(id);
		}
		catch (DBException e) {
			return null;
		}
		catch (SQLException e) {
			return null;
		}	
		
		if(result != null) {
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo che filtra i viaggi per data.
	 * @param travels
	 * @param start
	 * @param end
	 * @return filtered
	 */
	public static List<Travel> filterTravelByDates(List<Travel> travels, String start, String end) {
		List<Travel> filtered = new ArrayList<Travel>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date curStartDate = null;
		Date curEndDate = null;
		
		try {
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);

			for(Travel t : travels) {
				curStartDate = sdf.parse(t.getStartDate());
				curEndDate = sdf.parse(t.getEndDate());
				
				if(startDate.before(curStartDate) && endDate.after(curEndDate)) {
					filtered.add(t);
				}				
			}
		} catch (ParseException e) {
			logger.severe("PARSE ERROR");			
			e.printStackTrace();
			return null;
		}
		
		return filtered;
	}
	
}
