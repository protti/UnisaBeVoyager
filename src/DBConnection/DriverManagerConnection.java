package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class DriverManagerConnection {
	private static List<Connection> freeDbConnections;
	private static Logger log = Logger.getLogger("global");
	
	static	{	
		freeDbConnections = new LinkedList<Connection>();	
		try	{	
			Class.forName("com.mysql.jdbc.Driver");	
		}	catch(ClassNotFoundException	e){	
			log.severe("DB driver not found: " + e.getMessage());	
		}
	}	
	
	private	static Connection createDBConnection() throws SQLException {	
		Connection newConnection = null;	
		String db = "bevoyager";	
		String username	= "badmin@localhost";	
				String password	= "adminadmin";	
				newConnection = DriverManager.getConnection(	
						"jdbc:mysql://localhost:3306/"+db, 
						username,	password);	
		newConnection.setAutoCommit(false);	
		return newConnection;	
	}	
	
	public	static synchronized Connection	getConnection()	throws SQLException	{	
		Connection	connection;	
		if	(!freeDbConnections.isEmpty())	{	
			connection	=	(Connection)	freeDbConnections.get(0);	
			DriverManagerConnection.freeDbConnections.remove(0);	
			try	{	
				if	(connection.isClosed())	
					connection	=	DriverManagerConnection.getConnection();	
			}	catch	(SQLException	e)	{	
				connection	=	DriverManagerConnection.getConnection();	
			}	
		}	else		connection	=	DriverManagerConnection.createDBConnection();
		return	connection;	
	}
	
	public	static synchronized void releaseConnection(Connection connection){	
		DriverManagerConnection.freeDbConnections.add(connection);	
	}	
}
