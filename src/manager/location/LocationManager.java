package manager.location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;

public class LocationManager {
	
	public static void saveLocationToDB(Location location)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && location != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into Location "
					+ "values(" + location.getId() + ","
					+ "'" + location.getDescrizione() + "',"
					+ "'" + location.getName() + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static boolean checkLocation(int locationId)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Location "
					+ "where id = " + locationId + "");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		
		return false;
	}
	
	public static void deleteLocation(int id)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Location "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static List<Location> searchLocations(String nameLocation)
		throws SQLException{
		
		List<Location> locations = new ArrayList<Location>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Location "
					+ "where name LIKE '%" + nameLocation + "%'");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
				Location location = new Location(rs.getInt(1),rs.getString(3),
						rs.getString(2));
				locations.add(location);
			}
		}
		
		return locations;
	}
	
	public static Location searchLocationById(int id)
		throws SQLException,DBException{
		
		Location location = null;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Location "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			if(rs.next()){
				location = new Location(rs.getInt(1),rs.getString(3),
						rs.getString(2));
			}
		}
		if(location == null) throw new DBException();
		
		return location;
	}
	
	public static void updateLocation(Location location)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && location != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Location "
					+ "set name = '" + location.getName() + "',"
					+ "description = '" + location.getDescrizione() + "' "
					+ "where id = " + location.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	
}
