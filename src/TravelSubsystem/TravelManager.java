package TravelSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;
import PollSubsystem.Poll;
import RouteSubsystem.Route;
import UserSubsystem.RegisteredUser;
/**
*Classe che gestisce le operazioni su un viaggio.
*/

public class TravelManager {
	/**
	*Metodo che gestisce il salvataggio di un viaggio sul database.
	*@param travel
	*/
	private static Logger logger = Logger.getLogger("global"); 

	public static void saveTravelToDB(Travel travel)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && travel != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into "
					+ "Travel(startDate,endDate,routeID,"
					+ "creatorID,type,nome) "
					+ "values('" + travel.getStartDate() + "',"
					+ "'" + travel.getEndDate()+ "',"
					+ "'" + travel.getRoute().getId() + "',"
					+ "'" + travel.getCreatoreViaggio().getId() + "',"
					+ "" + travel.getType() + ")");
			logger.info("Salvo");
			DriverManagerConnection.releaseConnection(con);

		}
		
		if(result != 1)
		{	
			logger.info("Eccezione");
			throw new DBException();
		}
	}
	/**
	 * Metodo che controlla la presenza di un viaggio sul database.
	 * @param idTravel
	 * @return rs.next() se c'e', false altrimenti
	 */
	public static boolean checkTravel(int idTravel)
			throws SQLException{
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Travel "
					+ "where id = " + idTravel + "");
			DriverManagerConnection.releaseConnection(con);
			
			return rs.next();
		}
		
		return false;
	}
	/**
	*Metodo che gestisce l'eliminazione di un itinerario.
	*@param idTravel
	*/
	public static void deleteTravel(int idTravel)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Travel "
					+ "where id = " + idTravel + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che cerca sul database un viaggio tramite id.
	*@param idTravel
	*@return travel
	*/
	public static Travel fetchTravel(int id)
		throws SQLException,DBException{
		Travel travel = null;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			RegisteredUser ru = null;
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = (select creatorID "
					+ "from Travel "
					+ "where id = " + id + ")");
			
			if(rs1.next()){
				//GregorianCalendar gc = new GregorianCalendar();
				//gc.setGregorianChange(rs1.getDate(7));
				ru = new RegisteredUser(rs1.getInt(1),rs1.getString(5),
						rs1.getString(2),rs1.getString(6),rs1.getString(3),
						rs1.getString(4),rs1.getString(7),rs1.getInt(8));
			}
			
			Statement st2 = con.createStatement();
			ResultSet rs2 = st2.executeQuery("select * "
					+ "from Route "
					+ "where id = (select routeID "
					+ "from Travel "
					+ "where id = " + id + ")");
			
			Route route = null;
			
			if(rs2.next()){
				Statement st4 = con.createStatement();
				ResultSet rs4 = st4.executeQuery("select * "
						+ "from Location "
						+ "where id IN(select locationID "
						+ "from RouteLocationMatch "
						+ "where routeID = " + rs2.getLong(1) + "");
				ArrayList<Location> locations = new ArrayList<Location>();
				
				
				while(rs4.next()){
					Location location = new Location(rs4.getInt(1),
							rs4.getString(3),rs4.getString(2));
					locations.add(location);
					
				}
				
				route = new Route(locations,rs2.getInt(1),
						rs2.getString(2),rs2.getString(3));
			}
			
			Statement st3 = con.createStatement();
			ResultSet rs3 = st3.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id IN (select partecipantID "
					+ "from UserTravelMatch "
					+ "where travelID = " + id + ")");
			
			Statement st5 = con.createStatement();
			ResultSet rs5 = st5.executeQuery("select * "
					+ "from Poll "
					+ "where idTravel = " + id + "");
			ArrayList<Poll> polls = new ArrayList<Poll>();
			
			while(rs5.next()){
				//GregorianCalendar gc1 = new GregorianCalendar();
			//	GregorianCalendar gc2 = new GregorianCalendar();
				//gc1.setGregorianChange(rs5.getDate(6));
				//gc2.setGregorianChange(rs5.getDate(7));
				Poll poll = new Poll(rs5.getInt(1),rs5.getString(3),
						rs5.getInt(5),rs5.getInt(4),rs5.getString(6),rs5.getString(6));
				polls.add(poll);
			}
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Travel "
					+ "where id = " + id + "");
			
			DriverManagerConnection.releaseConnection(con);
			if(rs.next()){
				String gc1 = new String();
				String gc2 = new String();
				gc1 = (rs.getDate(2).toString());
				gc2 = (rs.getDate(3).toString());
				
				travel = new Travel(rs.getString(7),route,ru,gc1,gc2,rs.getBoolean(6));
				travel.setPollList(polls);
				
			}			
		}
		if(travel == null) throw new DBException();
		return travel;
	}
	/**
	*Metodo che vede se un utente partecipa ad un viaggio.
	*@param userId
	*@param travelId
	*return rs.next() se si', false altrimenti
	*/
	public static boolean checkUserPartecipant(int userId,int travelId)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id = " + userId + " "
					+ "AND id IN (select partecipantID "
					+ "from UserTravelMatch "
					+ "where travelID = " + travelId + ")");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		
		return false;
	}
	/**
	*Metodo che aggiorna i dati di un viaggio.
	*@param travel
	*/
	public static void updateTravel(Travel travel)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && travel != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Travel "
					+ "set startDate = '" + travel.getStartDate()
					 + "',"
					+ "endDate = '" + travel.getEndDate()
					 + "',"
					+ "routeID = " + travel.getRoute().getId() + ""
					+ "where id = " + travel.getId());
			
			Statement st1 = con.createStatement();
			ResultSet rs = st1.executeQuery("select * "
					+ "from UserTravelMatch "
					+ "where travelID = " + travel.getId() + "");
			
			DriverManagerConnection.releaseConnection(con);
			
			ArrayList<Integer> usersId = new ArrayList<Integer>();
			
			while(rs.next()){
				usersId.add(rs.getInt("partecipantID"));
			}
			
			
			
			for(RegisteredUser user: travel.getPartecipantiViaggio()){
				boolean type = false;
				for(int i = 0; i < usersId.size(); i++){
					if(user.getId() == usersId.get(i)) type = true;
				}
				
				if(!type){
					TravelManager.addUserToTravel(user.getId(), travel.getId());
				}
			}
			
			for(int i = 0; i < usersId.size(); i++){
				boolean type = false;
				for(RegisteredUser user: travel.getPartecipantiViaggio()){
					if(user.getId() == usersId.get(i)) type = true;
				}		
				if(!type){
					TravelManager.removeUserByTravel(travel.getId(), usersId.get(i));
				}
			}
			
		}
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che aggiunge un utente ad un viaggio.
	*@param userId
	*@param travelId
	*/
	private static void addUserToTravel(int userId,int travelId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Date date = new Date();
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into UserTravelMatch "
					+ "values('" + userId + "',"
							+ "'" + travelId + "',"
							+ "'" + date + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	
	/**
	*Metodo che restituisce i partecipanti ad un viaggio.
	*@param travelId
	*@return users
	*/
	public static List<RegisteredUser> getPartecipatingUsers(int travelId)
		throws SQLException{
		
		List<RegisteredUser> users = new ArrayList<RegisteredUser>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from RegisteredUser "
					+ "where id IN( select partecipantID "
					+ "from UserTravelMatch "
					+ "where travelID = " + travelId + "");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
			//	GregorianCalendar gc = new GregorianCalendar();
				//gc.setGregorianChange(rs.getDate(7));
				RegisteredUser user = new RegisteredUser(rs.getInt(1),
						rs.getString(5),rs.getString(2),rs.getString(6),
						rs.getString(3),rs.getString(4),rs.getString(7),rs.getInt(8));
				users.add(user);
			}
		}
		
		return users;
	}
	/**
	*Metodo che rimuove un utente da un viaggio.
	*@param travelId
	*@param userId
	*/
	private static void removeUserByTravel(int travelId,int userId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from UserTravelMatch "
					+ "where travelID = " + travelId +" AND "
					+ "partecipantID = " + userId + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	/**
	*Metodo che filtra ed organizza i viaggi per data.
	*@param date
	@return travels
	*/
	public static List<Travel> filterAndSortByDate(GregorianCalendar date)
		throws SQLException, DBException{
		
		List<Travel> travels = new ArrayList<Travel>();
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && date != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Travel "
					+ "where startDate = '" + date.getGregorianChange() + "'");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
				Travel travel = TravelManager.fetchTravel(rs.getInt(1));
				travels.add(travel);
			}
		}
		
		return travels;
	}
	/**
	*Metodo che filtra ed organizza i viaggi per luogo.
	*@param locationId
	@return travels
	*/
	public static List<Travel> filterAndSortByLocation(int locationId)
		throws SQLException,DBException{
		List<Travel> travels = new ArrayList<Travel>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Travel "
					+ "where routeID IN (select id "
					+ "from Route "
					+ "where id IN (select routeID "
					+ "from RouteLocationMatch "
					+ "where locationID = " + locationId + ""
					+ "order by locationID)"
					+ "order by id)");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
				Travel travel = TravelManager.fetchTravel(rs.getInt(1));
				travels.add(travel);
			}
		}
		return travels;
	}
	/**
	*Metodo che restituisce i sondaggi su un viaggio.
	*@param travelId
	*@return polls
	*/
	public static List<Poll> getPollTravel(int travelId)
		throws SQLException{
		List<Poll> polls = new ArrayList<Poll>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Poll "
					+ "where travelID = " + travelId + "");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
				//GregorianCalendar gc1 = new GregorianCalendar();
				//GregorianCalendar gc2 = new GregorianCalendar();
			//	gc1.setGregorianChange(rs.getDate(6));
			//	gc2.setGregorianChange(rs.getDate(7));
				Poll poll = new Poll(rs.getString(3),rs.getInt(5),rs.getInt(4),
						rs.getString(6),rs.getString(7));
				poll.setId(rs.getInt(1));
				polls.add(poll);
			}
		}
		
		return polls;
	}
	
	//Deve prende una stringa che è il nome del luogo cercato, non l'id del luogo,
	//perchè l'utente non conosce direttamente l'id
	/**
	*Metodo che ricerca viaggi tramite un luogo.
	*@param locationId
	*@return travels
	*/
	public static List<Travel> searchTravelByLocation(String location)
		throws SQLException,DBException{
		
		ArrayList<Travel> travels = new ArrayList<Travel>();
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id "
					+ "from Travel "
					+ "where routeID IN (select routeID "
					+ "from RouteLocationMatch "
					+ "where name = " + location + ")");
			DriverManagerConnection.releaseConnection(con);
			
			while(rs.next()){
				travels.add(TravelManager.fetchTravel(rs.getInt(1)));
			}
		}
		
		return travels;
	}
	
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
