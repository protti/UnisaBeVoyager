package RouteSubsystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBConnection.DBException;
import LocationSubsystem.Location;
import TravelSubsystem.TravelManager;
import UserSubsystem.RegisteredUser;

/**
 * Classe che effettua operazioni sugli itinerari.
 * @author Salvatore
 *
 */
public class RouteController {
	
	/**
	 * Meytodo che crea un itinerario.
	 * @param locations
	 * @param name
	 * @param description
	 * @return route
	 */
	public static Route createRoute(ArrayList<Location> locations, String name, String description) {
		Route route = new Route(locations, description, name);
		
		try {
			RouteManager.saveRouteToDB(route);
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			return null;			
		}
		return route;
	}
	
	/* Search route dovrebbe direttamente eseguire la ricerca per ID e per nome della location
	* Dalla servlet arriva comunque una stringa, quindi semplicemente effettua la ricerca 
	* usando entrambi i metodi offerti dal manager, quello che cerca per id e quello che cerca per nome
	*/
	
	/**
	 * Metodo che ricerca un itinerario.
	 * @param locationName
	 * @return results
	 */
	public static List<Route> searchRoute(String locationName) {
		List<Route> results; 
		
		try {
			results = RouteManager.searchRoutesByLocation(locationName);
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
	
	/**
	 * Metodo che controlla se un luogo è in un itinerario.
	 * @param idLocation
	 * @param locations
	 * @return true se ok, false altrimenti
	 */
	private static boolean isLocationInRoute(int idLocation, ArrayList<Location> locations) {
		for (int i = 0; i < locations.size(); i++) {
			if (idLocation == locations.get(i).getId()) {
				return false;
			}
		}		
		return false;
	}
	
	public static boolean addLocationToRoute(Location location, Route route) {
		
		ArrayList<Location> locations = route.getLocations();
		
		
		if (isLocationInRoute(location.getId(), locations)) {
			return false;
		}
		
		route.addLocation(location);
		try {
			RouteManager.updateRoute(route);
		
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}

		return true;
		
	}

	/**
	 * Metodo che rimuove un luogo da un itinerario.
	 * @param location
	 * @param route
	 * @return true se ok, false altrimenti
	 */
	public static boolean removeLocationFromRoute(Location location, Route route) {
		
		ArrayList<Location> locations = route.getLocations();
		
		
		if (!isLocationInRoute(location.getId(), locations)) {
			return false;
		}
		
		route.removeLocation(location);
		try {
			RouteManager.updateRoute(route);		
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}

		return true;
		
	}
	
	/**
	 * Metodo che elimina un itinerario.
	 * @param idRoute
	 * @return true se ok, false altrimenti
	 */
	public static boolean deleteRoute(int idRoute) {
		try {
			RouteManager.deleteRoute(idRoute);
		}
		catch (DBException e) {
			return false;
		}
		catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Questo è da usare quando un utente vuole visualizzare la pagina di un route, per esempio dopo la ricerca.
	 * Usato anche nell'aggiunta di un luogo ad un itinerario
	 * Era fetchRoute, l'ho cambiato in getRoute per essere coerente
	 */
	
	/**
	 * Metodo che restituisce un itinerario.
	 * @param idRoute
	 * @return route
	 */
	public static Route getRoute(int idRoute) {
		Route route;
		
		try {
			route = RouteManager.fetchRoute(idRoute);
		}
		catch (DBException e) {
			return null;
		}
		catch (SQLException e) {
			return null;
		}
		return route;
		
	}

	/**
	 * Metodo che aggiorna un itinerario.
	 * @param route
	 * @return true se ok, false altrimenti
	 */
	public static boolean updateRoute(Route route){
		
		try {
			RouteManager.updateRoute(route);
			return true;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
