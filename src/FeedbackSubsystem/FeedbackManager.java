package FeedbackSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
/**
*Classe che gestisce le operazioni di feedback.
*/
public class FeedbackManager {
	/**
	*Metodo che gestisce il salvataggio di un feedback nel database.
	*@param feedback
	*/
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
	/**
	*Metodo booleano che vede se un feedback è all'interno del database.
	*@param feedback
	*@return rs.next() se si', false se no
	*/
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
	/**
	*Metodo che gestisce l'eliminazione di un feedback.
	*@param feedback
	*/
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
	/**
	*Metodo che gestisce l'aggiornamento di un feedback.
	*@param feedback
	*/
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
