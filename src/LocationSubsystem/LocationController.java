package LocationSubsystem;
/**
 * Classe che effettua operazioni sulle notifiche.
 *
 */
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import FeedbackSubsystem.Feedback;
import UserSubsystem.RegisteredUser;

public class LocationController {

	/**
	 * Metodo che crea un luogo.
	 * @param name
	 * @param descrizione
	 * @return location
	 */
	static public Location createLocation(String name, String descrizione)
	{
		Location location = new Location(name,descrizione);
		try {
			LocationManager.saveLocationToDB(location);
		} catch (SQLException | DBException e) {
			return null;
		}
		return location;
	}
	
	/**
	 * Metodo che ricerca luoghi.
	 * @param locationName
	 * @return locations
	 */
	public static List<Location> searchLocations(String locationName)
	{
		List<Location> locations;
		try {
			locations = LocationManager.searchLocations(locationName);
		} catch (SQLException e) {
			return null;
		}
		
		return locations;
	}
	
	/*
	 * Questo si usa quando un utente vuole accedere alla pagina di un luogo sequendo un link o dopo una ricerca per esempio
	 */
	
	/**
	 * Metodo che restituisce un luogo.
	 * @param id
	 * @return location
	 */
	public static Location getLocation(int id)
	{
		Location location;
		try {
			location = LocationManager.fetchLocation(id);
		} catch (SQLException | DBException e) {
			return null;
		}
		return location;
	}
	
	/**
	 * Metodo che elimina un luogo.
	 * @param location
	 * @return true se ok, false altrimenti
	 */
	public static boolean deleteLocation(Location location)
	{
		try {
			LocationManager.deleteLocation(location.getId());
		} catch (SQLException | DBException e) {
			return false;
		}
		return true;
	}
	
	
	
}
