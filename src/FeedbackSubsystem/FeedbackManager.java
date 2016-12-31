package FeedbackSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;

public class FeedbackManager {

	public static void saveFeedbackToDB(Feedback feedback)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into " + feedback.getClass()
				.getName() + "(senderID,recipientID,message,sendDate) "
				+ "values(" + feedback.getSender().getId() + ","
				+ "" + feedback.getFeedbackOwner() + ","
				+ "'" + feedback.getMessage() + "',"
				+ "" + feedback.getDate().getGregorianChange() + ")");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
	
	public static boolean chackFeedback(Feedback feedback)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from " + feedback.getClass().getName() + " "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		
		return false;
	}
	
	public static void deleteFeedback(Feedback feedback)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from " + feedback.getClass().getName() + " "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static void updateFeedback(Feedback feedback)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update " + feedback.getClass().getName() + " "
					+ "set message = '" + feedback.getMessage() + "' "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
}
