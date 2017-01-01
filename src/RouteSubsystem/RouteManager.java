package RouteSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;

public class RouteManager {
	public static void saveRouteToDB(Route route)
		throws SQLException,DBException{
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && route != null){
			
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into Route "
					+ "values(" + route.getId() + ","
					+ "'" + route.getDescription() + "',"
					+ "'" + route.getName() + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
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
	
	public static void deleteRoute(int id)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Route "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
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
	
	/*public static void updateRoute(Route route)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && route != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Route "
					+ "set name = '" + route.getName() + "',"
					+ "description = '" + route.getDescription() + "' "
					+ "where id = " + route.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	} PROBABILMENTE RIDONDANTE*/
	
	public static void addLocationToRoute(int routeId,int locationId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Date date = new Date();
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into RouteLocationMatch "
					+ "values(" + locationId + ","
					+ "" + routeId + ","
					+ "'" + date + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	
	
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
	
	public static List<Route> searchRoutesByLocation(int locationId)
		throws SQLException,DBException{
		
		List<Route> routes = new ArrayList<Route>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Route "
					+ "where id IN (select routeID "
					+ "from RouteLocationMatch "
					+ "where locationID = " + locationId + ")");
			DriverManagerConnection.releaseConnection(con);
			while(rs.next()){
				Route route = RouteManager.fetchRoute(rs.getInt(1));
				routes.add(route);
			}
		}
		return routes;
	}
	
	
}
