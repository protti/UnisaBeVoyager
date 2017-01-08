package FeedbackSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import LocationSubsystem.Location;
import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;

public class FeedbackController {

	
	static public Feedback createFeedback(RegisteredUser sender,RegisteredUser receiver, String message, String date)
	{	
		FeedbackUser feedback = new FeedbackUser(sender, message, date,receiver);
		try {
			FeedbackManager.saveFeedbackToDB(feedback);
		} catch (SQLException | DBException e) {
			
			return null;
		}
		return feedback;
	}
	static public Feedback createFeedback(RegisteredUser sender,Route receiver, String message, String date)
	{
		
		FeedbackRoute feedback = new FeedbackRoute(sender,message, date,receiver);
		try {
			FeedbackManager.saveFeedbackToDB(feedback);
		} catch (SQLException | DBException e) {
			
			return null;
		}
		return feedback;
	}
	static public Feedback createFeedback(RegisteredUser sender,Location receiver, String message, String date)
	{
		
		FeedbackLocation feedback = new FeedbackLocation(sender, message, date,receiver);
		try {
			FeedbackManager.saveFeedbackToDB(feedback);
		} catch (SQLException | DBException e) {
			
			
			return null;
		}
		return feedback;
	}
	
	static public boolean deleteFeedback(Feedback feedback) 
	{
		try {
			FeedbackManager.deleteFeedback(feedback);
			
		} catch (SQLException | DBException e) {
			
			return false;
		}
		return true;
	}	
	
	
	public static List<FeedbackUser> searchFeedbackUser(int id){
		
		try {
			List<FeedbackUser> feedbacks = FeedbackManager.searchFeedbackUser(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static List<FeedbackRoute> searchFeedbackRoute(int id){
		
		try {
			List<FeedbackRoute> feedbacks = FeedbackManager.searchFeedbackRoute(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static List<FeedbackLocation> searchFeedbackLocation(int id){
		
		try {
			List<FeedbackLocation> feedbacks = FeedbackManager.searchFeedbackLocation(id);
			return feedbacks;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
}
