package PollSubsystem;

import java.sql.SQLException;

import DBConnection.DBException;
/**
 * Classe che effettua operazioni sui sondaggi.
 * @author Salvatore
 *
 */
public class PollController {
	
	/**
	 * Metodo che crea un sondaggio.
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param travelId
	 * @return poll
	 */
	public static Poll createPoll(String description, String startDate, String endDate, int travelId){

		Poll poll = new Poll(description, startDate, endDate, travelId);
		try {
			PollManager.savePollToDB(poll, travelId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (DBException e) {
			e.printStackTrace();
			return null;
		}
		return poll;
	}
	/**
	 * Metodo che controlla se un utente ha votato.
	 * @param userId
	 * @param pollId
	 * @return true se si', false altrimenti
	 */
	public static boolean hasUserVoted(int userId, int pollId) {
		try {
			if(!PollManager.checkUserPoll(pollId, userId)){
				return false;
			} 
			else return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Metodo che aggiorna il sondaggio.
	 * @param pollId
	 * @param voto
	 * @param userId
	 * @return true se ok, false altrimenti
	 */
	public static boolean updatePoll(int pollId, int voto, int userId){

		try {
			Poll poll = PollManager.fetchPoll(pollId);
			/*if(voto >= 0) poll.setVpositive(poll.getVpositive() + 1);
			else poll.setVnegative(poll.getVnegative() + 1);*/
			poll.registerVote(voto);
			PollManager.updatePoll(poll);
			PollManager.insertUserPoll(pollId, userId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (DBException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo che restituisce un sondaggio.
	 * @param id
	 * @return poll
	 */
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
