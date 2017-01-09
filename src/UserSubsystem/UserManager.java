package UserSubsystem;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Date;


import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
/**
*Classe che gestisce le operazioni richeste da un utente.
*/
public class UserManager {
	private static Logger logger = Logger.getLogger("global");
	/**
	*Metodo che gestisce il salvataggio di un utente sul database del sistema.
	*@param user
	*@param password
	*/
	public static void saveUserToDB(RegisteredUser user,String password)
		throws SQLException,DBException{
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && user != null && password != null){
			System.out.println(user.getAge());
			
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into RegisteredUser"
					+ "(username,name,lastName,"
					+ "email,password,birthDate"
					+ ",age) values('" + user.getUsername() + "',"
					+ "'" + user.getNome() + "',"
					+ "'" + user.getCognome() + "',"
					+ "'" + user.getEmail() + "',"
					+ "'" + password + "',"
					+ "'" + user.getBirthDate() + "',"
					+ "" + user.getAge() + ")");
			DriverManagerConnection.releaseConnection(con);
		}
		
		
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo booleano che vede se un utente è all'interno del database tramite id.
	*@param userId
	*@return rs.next() se, stabilita la connessione trova l'user oppure false.
	*/
	public static boolean checkUser(int userId) 
			throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = " + userId + "");
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		return false;
		
	}
	/**
	*Metodo che gestisce l'eliminazione di un utente dal database.
	*@param userId
	*/
	public static void deleteUser(int userId)
		throws SQLException,DBException{
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			
			Statement st1 = con.createStatement();
			st1.executeUpdate("delete from UserTravelMatch "
					+ "where partecipantID = " + userId + "");
			
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from RegisteredUser "
					+ "where id = " + userId + "");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che ricerca utente tramite username.
	*@param username
	*@return listUsers
	*/
	public static List<RegisteredUser> searchUsers(String username)
		throws SQLException{
		
		List<RegisteredUser> listUsers = new ArrayList<RegisteredUser>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where username LIKE '%" + username + "%'");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
			//	GregorianCalendar gc = new GregorianCalendar();
				//gc.setGregorianChange(rs.getDate(7));
				RegisteredUser user = new RegisteredUser(rs.getString(5),
						rs.getString(2),rs.getString(6),rs.getString(3),
						rs.getString(4),rs.getString(7),rs.getInt(8));
				user.setAuthorization(rs.getShort(9));
				user.setId(rs.getInt(1));
				listUsers.add(user);
			}
		}
		
		return listUsers;
	}
	/**
	 * Metodo che restituisce un utente registrato.
	 * @param username
	 * @param password
	 * @return user
	 * @throws SQLException
	 * @throws DBException
	 */
	public static RegisteredUser getUser(String username, String password)
		throws SQLException,DBException{
		
		RegisteredUser user = null;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && username != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where username = '" + username + "' and password = '" + password + "'");
			DriverManagerConnection.releaseConnection(con);
			
			if(rs.next()){
				//GregorianCalendar gc = new GregorianCalendar();
				//gc.setGregorianChange(rs.getDate(7));
				user = new RegisteredUser(rs.getString(5),
						rs.getString(2),rs.getString(6),rs.getString(3),
						rs.getString(4),rs.getString(7),rs.getInt(8));
				user.setAuthorization(rs.getShort(9));
				user.setId(rs.getInt(1));
			}
		}
		
		if(user == null) throw new DBException();
		return user;
	}
	/**
	 * Metodo che resituisce l'utente in base all'email.
	 * @param email
	 * @return user
	 * @throws SQLException
	 * @throws DBException
	 */
	public static RegisteredUser getUserByEmail(String email)
			throws SQLException,DBException{
			
			RegisteredUser user = null;
			Connection con = DriverManagerConnection.getConnection();
			if(con != null && email != null){
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * "
						+ "from RegisteredUser "
						+ "where email = '" + email + "'");
				DriverManagerConnection.releaseConnection(con);
				
				if(rs.next()){
					//GregorianCalendar gc = new GregorianCalendar();
					//gc.setGregorianChange(rs.getDate(7));
					user = new RegisteredUser(rs.getString(5),
							rs.getString(2),rs.getString(6),rs.getString(3),
							rs.getString(4),rs.getString(7),rs.getInt(8));
					user.setAuthorization(rs.getShort(9));
					user.setId(rs.getInt(1));
				}
			}
			
			if(user == null) throw new DBException();
			return user;
		}
	
	/**
	*Metodo che, stabilita connessione al database, va a prendere un utente tramite il suo id.
	*@param id
	*@return user
	*/
	public static RegisteredUser fetchUser(int id)
		throws SQLException,DBException{
		
		RegisteredUser user = null;
		Connection con = DriverManagerConnection.getConnection();
		
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			
			if(rs.next()){
				//GregorianCalendar gc = new GregorianCalendar();
				//gc.setGregorianChange(rs.getDate(7));
				
				user = new RegisteredUser(rs.getString(5),
						rs.getString(2),rs.getString(6),rs.getString(3),
						rs.getString(4),rs.getString(7),rs.getInt(8));
				user.setAuthorization(rs.getShort(9));
				user.setId(rs.getInt(1));
			}
		}
		if(user == null) throw new DBException();
		return user;
	}
	/**
	*Metodo che aggiorna i dati di un utente nel database.
	*@param user
	*/
	public static void updateUser(RegisteredUser user)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		
		if(con != null && user != null){
			
			Statement st = con.createStatement();
			result = st.executeUpdate("update RegisteredUser "
					+ "set username = '" + user.getUsername() + "',"
					+ "name = '" + user.getNome() + "',"
					+ "lastName = '" + user.getCognome() + "',"
					+ "email = '" + user.getEmail() + "',"
					+ "birthDate = '" + user.getBirthDate() + "',"
					+ "age = " + user.getAge() + ","
					+ "authorization = " + user.getAuthorization() + " "
					+ "where id = " + user.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che permette ad un utente di cambiare password.
	*@param userId
	*@param password
	*/
	public static void changePassword(int userId, String password)
			throws SQLException,DBException{
			
			int result = 0;
			Connection con = DriverManagerConnection.getConnection();
			
			if(con != null && password != null){
				
				Statement st = con.createStatement();
				result = st.executeUpdate("update RegisteredUser "
						+ "set password = '" + password + "',"
						+ "where id = " + userId + "");
				DriverManagerConnection.releaseConnection(con);
			}
			
			if(result != 1) throw new DBException();
		}
	/**
	*Metodo che controlla l'email inserita.
	*@param email
	*@return rs.next() se email nel database, false altrimenti
	*/
	public static boolean controlEmail(String email)
			throws SQLException,DBException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && email != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where email = '" + email + "'");
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		return false;
	}
	/**
	 * Metodo che controlla se un utente ha visitato un luogo.
	 * @param userId
	 * @param locationId
	 * @return rs.next() se ha visitato, false altrimenti
	 * @throws SQLException
	 * @throws DBException
	 */
	public static boolean checkUserVisit(int userId,
			int locationId) throws SQLException, DBException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = " + userId + " AND "
					+ "id IN("
					+ "select partecipantID "
					+ "from UserTravelMatch "
					+ "where travelID IN("
					+ "select id "
					+ "from Travel "
					+ "where routeID IN ("
					+ "select routeID "
					+ "from RouteLocationMatch "
					+ "where locationID = " + locationId + ")))");
			
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		return false;
	}
	/**
	 * Metodo che vede se un utente ha partecipato a tale viaggio.
	 * @param userId
	 * @param travelId
	 * @return rs.next() se ha partecipato, false altrimenti
	 * @throws SQLException
	 * @throws DBException
	 */
		
	public static boolean checkUserTravel(int userId,
			int travelId) throws SQLException,DBException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = " + userId + " "
					+ "AND id IN("
					+ "select partecipantID "
					+ "from UserTravelMatch "
					+ "where travelID = " + travelId + ")");
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		return false;
	}
	/**
	 * Metodo che vede se un utente ha partecipato ad un itinerario.
	 * @param userId
	 * @param routeId
	 * @return rs.next() se si', false altrimenti
	 * @throws SQLException
	 */
	public static boolean checkUserRoute(int userId, int routeId)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from UserTravelMatch "
					+ "where partecipantID = " + userId + " AND "
					+ "travelID IN (select id "
					+ "from Travel "
					+ "where routeID = " + routeId + ")");
			
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		return false;
	}
	/**
	 * Metodo che vede se un utente ha partecipato ad un viaggio con un altro utente.
	 * @param userId1
	 * @param userId2
	 * @return rs.next() se si', false altrimenti
	 * @throws SQLException
	 */
	public static boolean checkUserHasTraveledWith(int userId1,int userId2)
			throws SQLException{
			
			Connection con = DriverManagerConnection.getConnection();
			if(con != null){
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * "
						+ "from UserTravelMatch "
						+ "where partecipantID = " + userId1 + " AND "
						+ "travelID IN (select travelID "
						+ "from UserTravelMatch "
						+ "where partecipantID = " + userId2 + ")");
				DriverManagerConnection.releaseConnection(con);
				return rs.next();
			}
			return false;
		}
	
}
