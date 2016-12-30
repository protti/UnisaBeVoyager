package manager.travel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;
import LocationSubsystem.Location;
import PollSubsystem.Poll;
import RouteSubsystem.Route;
import TravelSubsystem.Travel;
import UserSubsystem.RegisteredUser;

public class TravelManager {

	public static void saveTravelToDB(Travel travel)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && travel != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into "
					+ "Travel(startDate,endDate,routeID,"
					+ "creatorID,type) "
					+ "values('" + travel.getStartDate().getGregorianChange() + "',"
					+ "'" + travel.getEndDate().getGregorianChange() + "',"
					+ "'" + travel.getRoute().getId() + "',"
					+ "'" + travel.getCreatoreViaggio().getId() + "',"
					+ "" + travel.getType() + ")");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
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
	
	public static Travel searchTravelById(int id)
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
				GregorianCalendar gc = new GregorianCalendar();
				gc.setGregorianChange(rs1.getDate(7));
				ru = new RegisteredUser(rs1.getInt(1),rs1.getString(5),
						rs1.getString(2),rs1.getString(6),rs1.getString(3),
						rs1.getString(4),gc,rs1.getInt(8));
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
			
			ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();
			
			while(rs3.next()){
				GregorianCalendar gc = new GregorianCalendar();
				gc.setGregorianChange(rs3.getDate(7));
				RegisteredUser user = new RegisteredUser(rs3.getInt(1),rs3.getString(5),
						rs3.getString(2),rs3.getString(6),rs3.getString(3),
						rs3.getString(4),gc,rs3.getInt(8));
				users.add(ru);
			}
			
			Statement st5 = con.createStatement();
			ResultSet rs5 = st5.executeQuery("select * "
					+ "from Poll "
					+ "where idTravel = " + id + "");
			ArrayList<Poll> polls = new ArrayList<Poll>();
			
			while(rs5.next()){
				GregorianCalendar gc1 = new GregorianCalendar();
				GregorianCalendar gc2 = new GregorianCalendar();
				gc1.setGregorianChange(rs5.getDate(6));
				gc2.setGregorianChange(rs5.getDate(7));
				Poll poll = new Poll(rs5.getInt(1),rs5.getString(3),
						rs5.getInt(5),rs5.getInt(4),gc1,gc2);
				polls.add(poll);
			}
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Travel "
					+ "where id = " + id + "");
			
			DriverManagerConnection.releaseConnection(con);
			if(rs.next()){
				GregorianCalendar gc1 = new GregorianCalendar();
				GregorianCalendar gc2 = new GregorianCalendar();
				gc1.setGregorianChange(rs.getDate(2));
				gc2.setGregorianChange(rs.getDate(3));
				
				travel = new Travel(ru,rs.getInt(1),gc1,gc2,rs.getBoolean(6));
				travel.setRoute(route);
				travel.setPartecipantiViaggio(users);
				travel.setPollList(polls);
				if(route != null) route.setRoute(travel);
			}			
		}
		if(travel == null) throw new DBException();
		return travel;
	}
	
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
	
	public static void updateTravel(Travel travel, int id)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && travel != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Travel "
					+ "set startDate = '" + travel.getStartDate()
					.getGregorianChange().toString() + "',"
					+ "endDate = '" + travel.getEndDate()
					.getGregorianChange().toString() + "',"
					+ "routeID = " + travel.getRoute().getId() + ""
					+ "where id = " + travel.getId());
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
		
	}
	
	public static void addUserToTravel(int userId,int travelId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Date date = new Date();
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into UserTravel "
					+ "values('" + userId + "',"
							+ "'" + travelId + "',"
							+ "'" + date + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
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
				GregorianCalendar gc = new GregorianCalendar();
				gc.setGregorianChange(rs.getDate(7));
				RegisteredUser user = new RegisteredUser(rs.getInt(1),
						rs.getString(5),rs.getString(2),rs.getString(6),
						rs.getString(3),rs.getString(4),gc,rs.getInt(8));
				users.add(user);
			}
		}
		
		return users;
	}
	
	public static void removeUserByTravel(int travelId,int userId)
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
				Travel travel = TravelManager.searchTravelById(rs.getInt(1));
				travels.add(travel);
			}
		}
		
		return travels;
	}
	
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
				Travel travel = TravelManager.searchTravelById(rs.getInt(1));
				travels.add(travel);
			}
		}
		return travels;
	}
	
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
				GregorianCalendar gc1 = new GregorianCalendar();
				GregorianCalendar gc2 = new GregorianCalendar();
				gc1.setGregorianChange(rs.getDate(6));
				gc2.setGregorianChange(rs.getDate(7));
				Poll poll = new Poll(rs.getString(3),rs.getInt(5),rs.getInt(4),
						gc1,gc2);
				poll.setId(rs.getInt(1));
				polls.add(poll);
			}
		}
		
		return polls;
	}
}
