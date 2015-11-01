package cf.thehivedsu.olms.resource.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDIDBDataSourceFactory {

	private static volatile DataSource sharedDataSource;

	private static void createSharedDataSource() {
		String jndiname = "jdbc/MainDB";
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			sharedDataSource = (DataSource) envContext.lookup(jndiname);
		} catch (NamingException e) {
			// Handle error that it's not configured in JNDI.
			throw new RuntimeException(jndiname + " is missing in JNDI!", e);
		}
	}

	/**
	 * @return the sharedDataSource
	 */
	public static DataSource getSharedDataSource() {
		if (sharedDataSource == null) {
			synchronized (DatabaseConnectionFactory.class) {
				if (sharedDataSource == null) {
					createSharedDataSource();
				}
			}
		}
		return sharedDataSource;
	}

	public static DataSource getDataSource() {
		String jndiname = "jdbc/MainDB";
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			return (DataSource) envContext.lookup(jndiname);
		} catch (NamingException e) {
			// Handle error that it's not configured in JNDI.
			throw new RuntimeException(jndiname + " is missing in JNDI!", e);
		}
	}

}
