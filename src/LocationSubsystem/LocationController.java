package LocationSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import FeedbackSubsystem.Feedback;
import UserSubsystem.RegisteredUser;

public class LocationController {

	
	static public Location createLocation(int id,String name, String descrizione)
	{
		Location location = new Location(id,name,descrizione);
		try {
			LocationManager.saveLocationToDB(location);
		} catch (SQLException | DBException e) {
			return null;
		}
		return location;
	}
	
	public static List<Location> searchLocations(String location)
	{
		List<Location> locations;
		try {
			locations = LocationManager.searchLocations(location);
		} catch (SQLException e) {
			return null;
		}
		
		return locations;
	}
	
	public static Location fetchLocation(int id)
	{
		Location location;
		try {
			location = LocationManager.fetchLocation(id);
		} catch (SQLException | DBException e) {
			return null;
		}
		return location;
	}
	
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
