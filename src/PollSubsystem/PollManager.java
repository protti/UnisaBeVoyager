package PollSubsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import DBConnection.DBException;
import DBConnection.DriverManagerConnection;

public class PollManager {

	public static void savePollToDB(Poll poll, int travelId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null && poll != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into "
					+ "Poll(description,positive,negative,"
					+ "startDate,endDate,idTravel) "
					+ "values('" + poll.getDescription() + "',"
					+ "" + poll.getVpositive() + ","
					+ "" + poll.getVnegative() + ","
					+ "'" + poll.getStartDate() + "',"
					+ "'" + poll.getEndDate() + "',"
					+ "" + travelId + ")");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static boolean checkPoll(int id)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Poll "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		return false;
	}
	
	public static void deletePoll(int id)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("delete from Poll "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
	
	public static void updatePoll(Poll poll)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			result = st.executeUpdate("update Poll "
					+ "set descritpion = '" + poll.getDescription() + "',"
					+ "positive = " + poll.getVpositive() +","
					+ "negative = " + poll.getVnegative() + ","
					+ "startDate = '" + poll.getStartDate() + "',"
					+ "endDate = '" + poll.getEndDate() + "' "
					+ "where id = " + poll.getId() + "");
			DriverManagerConnection.releaseConnection(con);
		}
		
		if(result != 1) throw new DBException();
	}
	
	public static Poll searchPollById(int id)
		throws SQLException,DBException{
		Poll poll = null;
		Connection con = DriverManagerConnection.getConnection();
	
		
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from Poll "
					+ "where id = " + id + "");
			DriverManagerConnection.releaseConnection(con);
			if(rs.next()){
				
				poll = new Poll(rs.getInt(1), rs.getString(2),rs.getInt(4),rs.getInt(3),
						rs.getString(5),rs.getString(6));
				poll.setId(rs.getInt(1));
			}
		}
		
		if(poll == null) throw new DBException();
		return poll;
	}
	
	public static void insertUserPoll(int pollId,int userId)
		throws SQLException,DBException{
		
		int result = 0;
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Date date = new Date();
			Calendar current = new GregorianCalendar();
			current.setTime(date);
			int mm = current.get(Calendar.MONTH) + 1;
			String month = "" + mm;
			if(month.length() == 1) month = "0" + month;
			int dd = current.get(Calendar.DAY_OF_MONTH);
			String day = "" + dd;
			if(day.length() == 1) day = "0" + day;
			String data = "" + current.get(Calendar.YEAR) + "-" + month + "-" + day;
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into PollUserMatch "
					+ "values(" + userId + ","
							+ "" + pollId + ","
							+ "'" + data + "')");
			DriverManagerConnection.releaseConnection(con);
		}
		if(result != 1) throw new DBException();
	}
	
	
	
	public static boolean checkUserPoll(int pollId,int userId)
		throws SQLException{
		
		Connection con = DriverManagerConnection.getConnection();
		if(con != null){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * "
					+ "from PollUserMatch "
					+ "where userID = " + userId + " AND "
					+ "pollID = " + pollId + "");
			DriverManagerConnection.releaseConnection(con);
			return rs.next();
		}
		
		return false;
	}
}
