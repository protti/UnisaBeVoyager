package FeedbackSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import LocationSubsystem.Location;
import LocationSubsystem.LocationManager;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;

public class FeedbackController {

	
	static public Feedback createFeedbackUser(RegisteredUser sender, int idUser, String message, String date) {	
		try {
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
	
	static public Feedback createFeedbackRoute(RegisteredUser sender, int idRoute, String message, String date)	{
		try {
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
	
	static public Feedback createFeedbackLocation(RegisteredUser sender, int idLocation, String message, String date) {
		try {
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
	
	static public boolean deleteFeedback(Feedback feedback) {
		try {
			FeedbackManager.deleteFeedback(feedback);
			
		} catch (SQLException | DBException e) {
			
			return false;
		}
		return true;
	}	
	
	
	public static List<FeedbackUser> searchFeedbackUser(int id) {
		try {
			List<FeedbackUser> feedbacks = FeedbackManager.searchFeedbackUser(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<FeedbackRoute> searchFeedbackRoute(int id){
		
		try {
			List<FeedbackRoute> feedbacks = FeedbackManager.searchFeedbackRoute(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
