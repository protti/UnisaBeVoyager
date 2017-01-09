package FeedbackSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import DBConnection.DBException;
import LocationSubsystem.Location;
import LocationSubsystem.LocationManager;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;
/**
 * Classe ch effettua operazioni sui feedback.
 * @author Salvatore
 *
 */
public class FeedbackController {

	private static Logger logger = Logger.getLogger("global");
	
	/**
	 * Metodo che crea un feedback utente.
	 * @param sender
	 * @param idUser
	 * @param message
	 * @param date
	 * @return feedback
	 */
	static public Feedback createFeedbackUser(RegisteredUser sender, int idUser, String message, String date) {	
		try {
			
			boolean b = UserManager.checkUserHasTraveledWith(sender.getId(), idUser);
			if (b == false) {
				return null;
			}	
			
			RegisteredUser receiver = UserManager.fetchUser(idUser);
			Feedback feedback = null;
			if(receiver != null) {
				feedback = new FeedbackUser(sender, message, date,receiver);
				FeedbackManager.saveFeedbackToDB(feedback);
			}
			return feedback;
		} catch (SQLException | DBException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	/**
	 * Metodo che crea un feedback itinerario.
	 * @param sender
	 * @param idRoute
	 * @param message
	 * @param date
	 * @return feedback
	 */
	static public Feedback createFeedbackRoute(RegisteredUser sender, int idRoute, String message, String date)	{
		try {
			
			boolean b = UserManager.checkUserRoute(sender.getId(), idRoute);
			if (b == false) {
				return null;
			}	
			logger.info("Sono in createFeedbackRoute");
			Route receiver = RouteManager.fetchRoute(idRoute);
			Feedback feedback = null;
			if(receiver != null) {
				feedback = new FeedbackRoute(sender, message, date,receiver);
				FeedbackManager.saveFeedbackToDB(feedback);
			}
			return feedback;
		} catch (SQLException | DBException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	/**
	 * Metodo che crea un feedback luogo.
	 * @param sender
	 * @param idLocation
	 * @param message
	 * @param date
	 * @return feedback
	 */
	static public Feedback createFeedbackLocation(RegisteredUser sender, int idLocation, String message, String date) {
		try {
			
			boolean b = UserManager.checkUserVisit(sender.getId(), idLocation);
			if (b == false) {
				return null;
			}	
			
			Location receiver = LocationManager.fetchLocation(idLocation);
			Feedback feedback = null;
			if(receiver != null) {
				feedback = new FeedbackLocation(sender, message, date,receiver);
				FeedbackManager.saveFeedbackToDB(feedback);
			}
			return feedback;
		} catch (SQLException | DBException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	/**
	 * Metodo che elimina un feedback.
	 * @param feedback
	 * @return true se ok, false altrimenti
	 */
	static public boolean deleteFeedback(Feedback feedback) {
		try {
			FeedbackManager.deleteFeedback(feedback);
			
		} catch (SQLException | DBException e) {
			
			return false;
		}
		return true;
	}	
	
	/**
	 * Metodo che ricerca feedback utente.
	 * @param id
	 * @return feedbacks
	 */
	public static List<FeedbackUser> searchFeedbackUser(int id) {
		try {
			List<FeedbackUser> feedbacks = FeedbackManager.searchFeedbackUser(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo che ricerca feedback itinerario.
	 * @param id
	 * @return feedbacks
	 */
	public static List<FeedbackRoute> searchFeedbackRoute(int id){
		
		try {
			List<FeedbackRoute> feedbacks = FeedbackManager.searchFeedbackRoute(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo che ricerca feedback luogo.
	 * @param id
	 * @return feedbacks
	 */
	public static List<FeedbackLocation> searchFeedbackLocation(int id){
		
		try {
			List<FeedbackLocation> feedbacks = FeedbackManager.searchFeedbackLocation(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
