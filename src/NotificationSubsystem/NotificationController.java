package NotificationSubsystem;

import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBException;
/**
 * Classe che effettua operazioni sulle notifiche.
 * @author Salvatore
 *
 */
public class NotificationController {
	
	/**
	 * Metodo che setta una notifica letta
	 * @param notification
	 * @return true se letta, false altrimenti
	 */
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
	
	/**
	 * Metodo che resituisce le notifiche di un utente.
	 * @param idUser
	 * @return null
	 */
	static public  ArrayList<Notification> getUserNotifications(int idUser) {
		
		return null;
	}

	/**
	 * Metodo che resituisce le notifiche di un utente.
	 * @param idUser
	 * @return notifica
	 */
	static public  Notification getUserNotification(int idNotification) {
		
		Notification notification;
		try {
			notification = NotificationManager.getNotification(idNotification);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;			
		}
		
		if(notification == null) {
			return null;
		}
		
		return notification;
	}
	
	/**
	 * Meotodo ch invia una notifica.
	 * @param idUser
	 * @param notification
	 * @return notifica
	 */
	static public  Notification sendNotification(int idUser, Notification notification) {
		
		return null;
	}
}
