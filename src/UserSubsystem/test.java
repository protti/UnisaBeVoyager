package UserSubsystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import DBConnection.DBException;

public class test {

	public static void main(String[] args) {
		System.out.println("Inizio");
		RegisteredUser user = new RegisteredUser("emailprova", "paolo1", "password", "paolo", "zirpoli", "1995-05-12", 21);
		
	/*	String birthDate = "1995-05-12";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBirth;
		try {
			dateOfBirth = sdf.parse(birthDate);
			Calendar current = new GregorianCalendar();
			current.setTime(new Date());
			Calendar birthCalendar = new GregorianCalendar();
			birthCalendar.setTime(dateOfBirth);
			int age = current.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR); 
			System.out.println(age);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}*/
		RegisteredUser protti = null;
		List<RegisteredUser> users = null;
		try {
			//protti = UserManager.getUser("protti");
			UserController.createUser("emailprova", "paolo2", "password", "paolo", "AAAAAA", "2000-05-12");
			users = UserManager.searchUsers("paolo");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		protti = users.get(0);
		
		System.out.println(protti.getCognome());
		System.out.println(protti.getEmail());
		System.out.println(protti.getBirthDate());
		

		protti = users.get(1);
		
		System.out.println(protti.getCognome());
		System.out.println(protti.getEmail());
		System.out.println(protti.getBirthDate());
		
	}
	
}
