package FeedbackSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import DBConnection.DBException;
import LocationSubsystem.Location;
import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;

public class FeedbackController {

	
	static public Feedback createFeedback(RegisteredUser sender,RegisteredUser receiver, String message, GregorianCalendar date)
	{	
		FeedbackUser feedback = new FeedbackUser(sender, message, date,receiver);
		try {
			FeedbackManager.saveFeedbackToDB(feedback);
		} catch (SQLException | DBException e) {
			
			return null;
		}
		return feedback;
	}
	static public Feedback createFeedback(RegisteredUser sender,Route receiver, String message, GregorianCalendar date)
	{
		
		FeedbackRoute feedback = new FeedbackRoute(sender,message, date,receiver);
		try {
			FeedbackManager.saveFeedbackToDB(feedback);
		} catch (SQLException | DBException e) {
			
			return null;
		}
		return feedback;
	}
	static public Feedback createFeedback(RegisteredUser sender,Location receiver, String message, GregorianCalendar date)
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
	
	
	
}
