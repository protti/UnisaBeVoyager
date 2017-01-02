package LocationSubsystem;
/**
*Classe che gestisce le operazioni sui luoghi.
*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;

public class LocationManager {
	/**
	*Metodo che gestisce il salvataggio di un luogo sul database.
	*@param location
	*/
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
	/**
	*Metodo che cerca sul database un luogo tramite id.
	*@param locationId
	*@return rs.next() se c'e', false se non c'e'
	*/
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
	/**
	*Metodo che gestisce l'eliminazione di un luogo.
	*@param id
	*/
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
	/**
	*Metodo che effettua una ricerca di luoghi tramite nome.
	*@param nameLocation
	*@return locations
	*/
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
	/**
	*Metodo che gestisce la ricerca di luoghi tramite id.
	*@param id
	*@return location
	*/
	public static Location fetchLocation(int id)
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
	/**
	*Metodo che aggiorna i dati di un luogo.
	*@param location
	*/
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
