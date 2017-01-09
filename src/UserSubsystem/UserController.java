package UserSubsystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;
/**
 * Classe che effettua operazioni sull'utente.
 * @author Salvatore
 *
 */
public class UserController {


	
	/*
	 * Prima era fetchUser, l'ho modificato per essere coerente con i sequence diagram
	 * Questo serve per la visualizzazione del profilo di un utente. Quando per esempio un utente clicca su "Il mio profilo" oppure fa la ricerca di un utente,
	 * fa prima "searchUser" e poi quando clicca sul risultato usa getUser, quindi non prnede username e password, ma un id
	 * 
	 */
	
	/**
	 * Metodo che restituisce un utente registrato.
	 * @param id
	 * @return user
	 */
		static public RegisteredUser getUser(int id)
		{
			RegisteredUser user;
			try {
				user = UserManager.fetchUser(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				user = null;
			} catch (DBException e) {
				// TODO Auto-generated catch block
				user = null;
			}
			return user;
		}
	
		/**
		 * Metodo che cerca utente tramite username.
		 * @param username
		 * @return listUsers
		 */
		static public List<RegisteredUser> searchUser(String username)
		{
			List<RegisteredUser> listUsers;
			
			try {
				listUsers = UserManager.searchUsers(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				listUsers = null;
			}
			return listUsers;
		}
		/**
		 * Metodo che crea un utente.
		 * @param email
		 * @param username
		 * @param password
		 * @param nome
		 * @param cognome
		 * @param birthDate
		 * @return user
		 */
		static public RegisteredUser createUser(String email, String username, String password, String nome, String cognome,
				String birthDate)
		{
			/*GregorianCalendar date = new GregorianCalendar();
			int time = date.compareTo(birthDate);
			Date dateDif = new Date(time);
			int age = dateDif.getYear();*/
			
			RegisteredUser user = new RegisteredUser(email,username,password,nome,cognome,
					birthDate, computeAge(birthDate));
			try {
				UserManager.saveUserToDB(user, password);
			} catch (SQLException e) {
				System.out.println("SQLEXCEPTION");
				return null;
			} catch (DBException e) {
				System.out.println("DBEXCEPTION");
				return null;
			}
			
			return user;
		}
		
		/*static public RegisteredUser getUser()
		{
			
		}*/
		/**
		 * Metodo che elimina un utente.
		 * @param id
		 * @return true eliminato, false altrimenti
		 */
		static public boolean deleteUser(int id)
		{
			try {
				UserManager.deleteUser(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			} catch (DBException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		/**
		 * Metodo che ricava l'eta' dalla data di nascita.
		 * @param birthDate
		 * @return age
		 */
		private static int computeAge(String birthDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateOfBirth;
			int age = 0; 
			try {
				dateOfBirth = sdf.parse(birthDate);
				Calendar current = new GregorianCalendar();
				current.setTime(new Date());
				Calendar birthCalendar = new GregorianCalendar();
				birthCalendar.setTime(dateOfBirth);
				age = current.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR); 
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return age;
		}

	
}
