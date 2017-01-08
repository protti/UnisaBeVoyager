package FeedbackSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;
import LocationSubsystem.LocationManager;
import RouteSubsystem.Route;
import RouteSubsystem.RouteManager;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;
/**
*Classe che gestisce le operazioni di feedback.
*/
public class FeedbackManager {
	
	private static Logger logger = Logger.getLogger("global");
	/**
	*Metodo che gestisce il salvataggio di un feedback nel database.
	*@param feedback
	*/
	public static void saveFeedbackToDB(Feedback feedback)
		throws SQLException,DBException{
		logger.info(feedback.getClass().getName());
		Feedback newFeedback = null;
		String table = feedback.getClass().getName().substring(17);
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			logger.info(feedback.getClass().getName());
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into " + table
				+ "(senderID,recipientID,message,sendDate) "
				+ "values(" + feedback.getSender().getId() + ","
				+ "" + feedback.getFeedbackOwner() + ","
				+ "'" + feedback.getMessage() + "',"
				+ "'" + feedback.getDate() + "')");
			
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery("select max(id) as max_id "
					+ "from " + table + " "
					+ "where sendDate = '" + feedback.getDate() + "' AND "
					+ "message = '" + feedback.getMessage() + "' AND "
					+ "senderID = " + feedback.getSender().getId() + "");
			
			DriverManagerConnection.releaseConnection(con);
			
			if(rs.next()){
				feedback.setId(rs.getInt(1));
			}
		}
						
		if(result != 1) throw new DBException();
		
	}
	/**
	*Metodo booleano che vede se un feedback è all'interno del database.
	*@param feedback
	*@return rs.next() se si', false se no
	*/
	public static boolean checkFeedback(Feedback feedback)
		throws SQLException{
		String table = feedback.getClass().getName().substring(17);
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from " + table + " "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		
		return false;
	}
	/**
	*Metodo che gestisce l'eliminazione di un feedback.
	*@param feedback
	*/
	public static void deleteFeedback(Feedback feedback)
		throws SQLException,DBException{
		String table = feedback.getClass().getName().substring(17);
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from " + table + " "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che gestisce l'aggiornamento di un feedback.
	*@param feedback
	*/
	public static void updateFeedback(Feedback feedback)
		throws SQLException,DBException{
		String table = feedback.getClass().getName().substring(17);
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && feedback != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update " + table + " "
					+ "set message = '" + feedback.getMessage() + "' "
					+ "where id = " + feedback.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
	
	public static FeedbackUser fetchFeedbackUser(int id)
		throws SQLException, DBException{
		FeedbackUser feedback = null;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from FeedbackUser "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			if(rs.next()){
				RegisteredUser sender = UserManager.fetchUser(rs.getInt(2));
				RegisteredUser recipient = UserManager.fetchUser(rs.getInt(3));
				feedback = new FeedbackUser(rs.getInt(1),sender,rs.getString(4),rs.getString(5),recipient);
			}
		}
		return feedback;
	}
	
	public static FeedbackRoute fetchFeedbackRoute(int id)
			throws SQLException, DBException{
			FeedbackRoute feedback = null;
			Connection con = DriverManagerConnection.getConnection();
			if(con != null){
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * "
						+ "from FeedbackRoute "
						+ "where id = " + id + "");
				DriverManagerConnection.releaseConnection(con);
				if(rs.next()){
					RegisteredUser sender = UserManager.fetchUser(rs.getInt(2));
					Route recipient = RouteManager.fetchRoute(rs.getInt(3));
					feedback = new FeedbackRoute(rs.getInt(1),sender,rs.getString(4),rs.getString(5),recipient);
				}
			}
			return feedback;
	}
	
	
	public static FeedbackLocation fetchFeedbackLocation(int id)
			throws SQLException, DBException{
			FeedbackLocation feedback = null;
			Connection con = DriverManagerConnection.getConnection();
			if(con != null){
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * "
						+ "from FeedbackLocation "
						+ "where id = " + id + "");
				DriverManagerConnection.releaseConnection(con);
				if(rs.next()){
					RegisteredUser sender = UserManager.fetchUser(rs.getInt(2));
					Location recipient = LocationManager.fetchLocation(rs.getInt(3));
					feedback = new FeedbackLocation(rs.getInt(1),sender,rs.getString(4),rs.getString(5),recipient);
				}
			}
			return feedback;
	}
	
	public static List<FeedbackUser> searchFeedbackUser(int id)
		throws SQLException,DBException{
		
		List<FeedbackUser> feedbacks = new ArrayList<FeedbackUser>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from FeedbackUser "
					+ "where recipientID = " + id + "");
			
			while(rs.next()){
				FeedbackUser feedback = FeedbackManager.fetchFeedbackUser(rs.getInt(1));
				feedbacks.add(feedback);
			}
		}
		return feedbacks;
	}
	
	public static List<FeedbackRoute> searchFeedbackRoute(int id)
			throws SQLException,DBException{
			
			List<FeedbackRoute> feedbacks = new ArrayList<FeedbackRoute>();
			Connection con = DriverManagerConnection.getConnection();
			if(con != null){
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select id "
						+ "from FeedbackRoute "
						+ "where recipientID = " + id + "");
				
				while(rs.next()){
					FeedbackRoute feedback = FeedbackManager.fetchFeedbackRoute(rs.getInt(1));
					feedbacks.add(feedback);
				}
			}
			return feedbacks;
	}
	
	public static List<FeedbackLocation> searchFeedbackLocation(int id)
			throws SQLException,DBException{
			
			List<FeedbackLocation> feedbacks = new ArrayList<FeedbackLocation>();
			Connection con = DriverManagerConnection.getConnection();
			if(con != null){
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select id "
						+ "from FeedbackLocation "
						+ "where recipientID = " + id + "");
				
				while(rs.next()){
					FeedbackLocation feedback = FeedbackManager.fetchFeedbackLocation(rs.getInt(1));
					feedbacks.add(feedback);
				}
			}
			return feedbacks;
	}
}
