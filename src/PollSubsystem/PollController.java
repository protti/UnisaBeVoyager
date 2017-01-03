package PollSubsystem;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import DBConnection.DBException;

public class PollController {
	public static Poll createPoll(String description,int pos, int neg, 
			GregorianCalendar startDate, GregorianCalendar endDate,int travelId){

		Poll poll = new Poll(description,neg,pos,startDate,endDate);
		try {
			PollManager.savePollToDB(poll, travelId);
		} catch (SQLException e) {
			return null;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return poll;
	}

	public static boolean updatePoll(int pollId, int voto, int userId){

		try {
			if(!PollManager.checkUserPoll(pollId, userId)){
				Poll poll = PollManager.searchPollById(pollId);
				if(voto >= 0) poll.setVpositive(poll.getVpositive() + 1);
				else poll.setVnegative(poll.getVnegative() + 1);
				PollManager.updatePoll(poll);
				return true;
			} 
			else return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}
}
