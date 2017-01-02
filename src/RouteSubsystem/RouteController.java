package RouteSubsystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBConnection.DBException;
import LocationSubsystem.Location;
import TravelSubsystem.TravelManager;
import UserSubsystem.RegisteredUser;

public class RouteController {
	
	/* Search route dovrebbe direttamente eseguire la ricerca per ID e per nome della location
	* Dalla servlet arriva comunque una stringa, quindi semplicemente effettua la ricerca 
	* usando entrambi i metodi offerti dal manager, quello che cerca per id e quello che cerca per nome
	*/
	public static List<Route> searchRoute(String locationName) {
		List<Route> results; 
		
		try {
			results = RouteManager.searchRouteByLocation(locationName);
			if(results.isEmpty()) {
				results = RouteManager.searchRoute(locationName);
			}
		}
		catch (DBException e) {
			return null;
		}
		catch (SQLException e) {
			return null;
		}
		
		if(!results.isEmpty()) {
			return results;
		} else {
			return null;
		}
	}
	
	private static boolean isLocationInRoute(int idLocation, Map<Integer, Location> locations) {
		/*for (int i = 0; i < locations.size(); i++) {
			if (idLocation == locations.get(i).getId()) {
				return false;
			}
		}*/

		if (locations.containsKey(idLocation)) {
			return false;
		}

		
		return true;
	}
	
	public static boolean addLocationToRoute(Location location, Route route) {
		
		Map<Integer, Location> locations = route.getLocations();
		
		
		if (!isLocationInRoute(location.getId(), locations)) {
			return false;
		}
		
		route.addLocation(location);
		try {
			/*
			 * MANCA UPDATE ROUTE NEL MANAGER
			 */
		
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}

		return true;
		
	}

}
