package NotificationSubsystem;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBException;

public class NotificationController {
	

	static public  boolean setReadNotice(Notification notification) {
		
		notification.setReadType(true);
		
		try {
			NotificationManager.updateNotification(notification);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return false;			
		}
		
		return true;
	}
	
	static public  ArrayList<Notification> getUserNotifications(int idUser) {
		
		return null;
	}

	static public  Notification getUserNotification(int idNotification) {
		
		Notification notification;
		try {
			notification = NotificationManager.searchNotificationById(idNotification);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;			
		}
		
		if(notification == null) {
			return null;
		}
		
		return notification;
	}
	
	static public  Notification sendNotification(int idUser, Notification notification) {
		
		return null;
	}
}
