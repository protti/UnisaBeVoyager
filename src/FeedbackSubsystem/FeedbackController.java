package FeedbackSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import DBConnection.DBException;
import LocationSubsystem.Location;
import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;

public class FeedbackController {

	
	static public Feedback createFeedback(RegisteredUser sender,RegisteredUser reciver, String message, GregorianCalendar date)
	{	
		FeedbackUser fu = new FeedbackUser(sender, message, date,reciver);
		return fu;
	}
	static public Feedback createFeedback(RegisteredUser sender,Route reciver, String message, GregorianCalendar date)
	{
		
		FeedbackRoute fu = new FeedbackRoute(sender,message, date,reciver);
		return fu;
	}
	static public Feedback createFeedback(RegisteredUser sender,Location reciver, String message, GregorianCalendar date)
	{
		
		FeedbackLocation fu = new FeedbackLocation(sender, message, date,reciver);
		return fu;
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
