package NotificationSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import TravelSubsystem.TravelManager;
import UserSubsystem.RegisteredUser;
import UserSubsystem.UserManager;

public class NotificationManager {
	
	public static void saveNotificationToDB(Notification notification)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && notification != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into "
					+ "Notification(senderID,recipientID,body,sendDate) "
					+ "values(" + notification.getSender().getId() + ","
					+ "" + notification.getRecipient().getId() + ","
					+ "'" + notification.getBody() + "',"
					+ "'" + notification.getSendDate() + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static boolean checkNotification(int id)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Notification "
					+ "where id = " + id + "");
			return rs.next();
		}
		
		return false;
	}
	
	public static void deleteNotification(int id)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Notification "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static Notification searchNotificationById(int id)
		throws SQLException,DBException{
		
		Notification notification = null;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Notification "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			
			if(rs.next()){
				RegisteredUser sender = UserManager.fetchUser(rs.getInt(2));
				RegisteredUser recipient = UserManager.fetchUser(rs.getInt(3));
				
				notification = new Notification(sender,recipient,rs.getString(4),
						rs.getBoolean(5),rs.getString(6));
			}
		}
		
		if(notification == null) throw new DBException();
		return notification;
	}
	
	public static void updateNotification(Notification notification)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && notification != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Notification "
					+ "set readThis = " + notification.getReadType() + " "
					+ "where id = " + notification.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	

	/*
	 * Usa il travelID per recuperare i partecipanti e gli manda la notifica giusto?
	 */
	
	public static void notifyAllPartecipants(int travelId,int senderId, 
			String message) throws SQLException,DBException{
		
		List<RegisteredUser> recipients = TravelManager.
				getPartecipatingUsers(travelId);
		
		for(int i = 0; i < recipients.size(); i++){
			if(recipients.get(i).getId() == senderId){
				recipients.remove(i);
				break;
			}
		}
		
		RegisteredUser sender = UserManager.fetchUser(senderId);
		Date date = new Date();
		for(RegisteredUser user: recipients){
			Notification notification = new Notification(sender,user,
					message,false,date.toString());
			NotificationManager.saveNotificationToDB(notification);
		}
	}
		

	public static void sendNotification(int senderId,int recipientId, 
			String message) throws SQLException,DBException{
		
		RegisteredUser sender = UserManager.fetchUser(senderId);
		RegisteredUser recipient = UserManager.fetchUser(recipientId);
		
		Date date = new Date();
		Notification notification = new Notification(sender,recipient,
				message,false,date.toString());
	}
	
	public static List<Notification> getUserNotifications(int userId)
		throws SQLException,DBException{
		
		List<Notification> notifications = new ArrayList<Notification>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Notification "
					+ "where senderID = " + userId + "");
			
			RegisteredUser recipient = UserManager.fetchUser(userId);
			
			while(rs.next() && recipient != null){
				RegisteredUser sender = UserManager.fetchUser(rs.getInt(2));
				Notification notification = new Notification(sender,recipient,
						rs.getString(4),rs.getBoolean(5),rs.getString(6));
				notifications.add(notification);
			}
		}
		return notifications;
	}
}
