package PollSubsystem;

import java.sql.SQLException;

import DBConnection.DBException;

public class PollController {
	public static Poll createPoll(String description, String startDate, String endDate, int travelId){

		Poll poll = new Poll(description, startDate, endDate, travelId);
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
				Poll poll = PollManager.fetchPoll(pollId);
				/*if(voto >= 0) poll.setVpositive(poll.getVpositive() + 1);
				else poll.setVnegative(poll.getVnegative() + 1);*/
				poll.registerVote(voto);
				PollManager.updatePoll(poll);
				PollManager.insertUserPoll(pollId, userId);
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
	
	
	public static Poll getPoll(int id){
		
		try {
			Poll poll = PollManager.fetchPoll(id);
			return poll;
		} catch (SQLException | DBException e) {
			// TODO Auto-generated catch block
			return null;
		} 
	}
}
