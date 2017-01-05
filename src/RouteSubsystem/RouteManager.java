package RouteSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;
import LocationSubsystem.LocationManager;
/**
 *Classe che gestisce le operazioni su un itinerario.
 */
public class RouteManager {
	
	private static Logger logger = Logger.getLogger("global");
	
	/**
	 *Metodo che gestisce il salvataggio di un itinerario sul database.
	 *@param route
	 */
	public static void saveRouteToDB(Route route)
			throws SQLException,DBException{
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && route != null){

			
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into Route(description,name) "
					+ "values('" + route.getDescription() + "',"
					+ "'" + route.getName() + "')");
			
			Statement st2 = con.createStatement();
			ResultSet rs = st2.executeQuery("select id "
					+ "from Route "
					+ "where name = '" + route.getName() + "' AND "
					+ "description = '" + route.getDescription() + "'");
			
			int id = 0;
			if(rs.next()) id = rs.getInt(1);
			
			List<Location> locations = route.getLocations();
			if(locations != null && id > 0){
				for(Location location: locations){
					Statement st1 = con.createStatement();
					st1.executeUpdate("insert into RouteLocationMatch "
						+ "values(" + location.getId() + ","
						+ "" + id + ","
						+ "'0000-00-00')");
				}
			}
			
			DriverManagerConnection.releaseConnection(con);
		}

		if(result != 1) throw new DBException();
	}
	/**
	 *Metodo che cerca sul database un itinerario tramite id.
	 *@param id
	 *@return rs.next() se c'e', false se non c'e'
	 */
	public static boolean checkRoute(int id)
			throws SQLException{

		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Route "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);

			return rs.next();
		}

		return false;
	}
	/**
	 *Metodo che gestisce l'eliminazione di un itinerario.
	 *@param id
	 */
	public static void deleteRoute(int id)
			throws SQLException,DBException{

		
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st1 = con.createStatement();
			st1.executeUpdate("delete from RouteLocationMatch "
					+ "where routeID = " + id + "");
			
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Route "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
		}

		if(result != 1) throw new DBException();
	}
	/**
	 *Metodo che preleva un itinerario tramite id.
	 *@param id
	 *@return route
	 */
	public static Route fetchRoute(int id)
			throws SQLException,DBException{
		Route route = null;

		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			ArrayList<Location> locations = new ArrayList<Location>();
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select * "
					+ "from Location "
					+ "where id IN(select locationID "
					+ "from RouteLocation "
					+ "where routeID = " + id + ")");

			while(rs1.next()){
				Location location = new Location(rs1.getInt(1), rs1.getString(3),
						rs1.getString(2));
				locations.add(location);
			}

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Route "
					+ "where id = " + id + "");

			DriverManagerConnection.releaseConnection(con);

			if(rs.next()){
				route = new Route(locations,rs.getInt(1),rs.getString(2),
						rs.getString(3));
			}

		}

		if(route == null) throw new DBException();
		return route;
	}
	/**
	 *Metodo che effettua una ricerca di itinerari tramite nome.
	 *@param name
	 *@return routes
	 */
	public static List<Route> searchRoutes(String name)
			throws SQLException,DBException{

		List<Route> routes = new ArrayList<Route>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && name != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Route "
					+ "where name LIKE '%" + name + "%'");
			DriverManagerConnection.releaseConnection(con);

			while(rs.next()){
				Route route = RouteManager.fetchRoute(rs.getInt(1));
				routes.add(route);
			}
		}

		return routes;
	}
	/**
	 *Metodo che aggiorna i dati di un itinerario.
	 *@param route
	 */
	public static void updateRoute(Route route)
			throws SQLException,DBException{

		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && route != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Route "
					+ "set name = '" + route.getName() + "',"
					+ "description = '" + route.getDescription() + "' "
					+ "where id = " + route.getId() + "");

			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery("select locationID "
					+ "from RouteLocationMatch "
					+ "where routeID = " + route.getId() + "");

			DriverManagerConnection.releaseConnection(con);

			List<Integer> listDB = new ArrayList<Integer>();
			List<Location> listRoute = route.getLocations();

			if(listRoute != null){
				while(rs.next()){
					int id = rs.getInt(1);
					listDB.add(id); 
				}
				
				for(int i = 0; i < listDB.size(); i++){
					boolean type = false;
					for(Location location: listRoute){
						if(listDB.get(i) == location.getId()) type = true;
					}
					
					if(!type) RouteManager.removeLocationByRoute(route.getId(), listDB.get(i));
				}
				
				for(Location loc: listRoute){
					boolean type = false;
					for(int i = 0; i < listDB.size(); i++){
						if(listDB.get(i) == loc.getId()) type = true;
					}
					if(!type) RouteManager.addLocationToRoute(route.getId(), loc.getId());
				}
			}
		}

		if(result != 1) throw new DBException();
	}

	/**
	 *Metodo che aggiunge un luogo ad un itinerario.
	 *@param routeId
	 *@param locationId
	 */
	public static void addLocationToRoute(int routeId,int locationId)
			throws SQLException,DBException{

		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Calendar current = new GregorianCalendar();
			current.setTime(new Date());
			int m = current.get(Calendar.MONTH) + 1;
			String month = "" + m;
			if(month.length() == 1) month = "0" + month;
			String day = "" + current.get(Calendar.DAY_OF_MONTH);
			if(day.length() == 1) day = "0" + day;
			String date = "" + current.get(Calendar.YEAR) + "-" + month + "-" + day;
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into RouteLocationMatch "
					+ "values(" + locationId + ","
					+ "" + routeId + ","
					+ "'" + date + "')");


			DriverManagerConnection.releaseConnection(con);


		}

		if(result != 1) throw new DBException();
	}

	/**
	 *Metodo che restituisce i luogi di un itinerario.
	 *@param routeId
	 *@return locations
	 */
	public static List<Location> getLocations(int routeId)
			throws SQLException{
		List<Location> locations = new ArrayList<Location>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Location "
					+ "where id IN (select locationID "
					+ "from RouteLocationMatch "
					+ "where routeID = " + routeId + ")");
			DriverManagerConnection.releaseConnection(con);

			while(rs.next()){
				Location location = new Location(rs.getInt(1),rs.getString(3),
						rs.getString(2));
				locations.add(location);
			}
		}

		return locations;
	}
	/**
	 *Metodo che elimina un luogo ad un itinerario.
	 *@param routeId
	 *@param locationId
	 */
	public static void removeLocationByRoute(int routeId, int locationId)
			throws SQLException,DBException{

		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from RouteLocationMatch "
					+ "where routeID = " + routeId + " AND "
					+ "locationID = " + locationId + "");
			DriverManagerConnection.releaseConnection(con);
		}

		if(result != 1) throw new DBException();
	}
	/**
	 *Metodo che gestisce la ricerca di itinerari tramite id di un luogo.
	 *@param locationId
	 *@return routes
	 */
	public static List<Route> searchRoutesByLocation(String location)
			throws SQLException,DBException{

		List<Route> routes = new ArrayList<Route>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Route "
					+ "where id IN (select routeID "
					+ "from RouteLocationMatch "
					+ "where locationID IN (select id "
					+ "from Location "
					+ "where name LIKE '%" + location + "%')");
			DriverManagerConnection.releaseConnection(con);
			while(rs.next()){
				Route route = RouteManager.fetchRoute(rs.getInt(1));
				routes.add(route);
			}
		}
		return routes;
	}

	public static boolean checkUserRoute(int userId, int routeId)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from UserTravelMatch "
					+ "where partecipantID = " + userId + " AND "
					+ "travelID IN (select id "
					+ "from Travel where"
					+ "routeId = " + routeId + ")");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		return false;
	}

}
