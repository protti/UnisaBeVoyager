package DBConnection;
/**
 * Classe che gestisce le eccezioni nella connesione al database. Estende la classe Exception.
 *
 */
public class DBException extends Exception {
	
	public DBException(){
		super("Result's operation is wrong");
	}
	
}
