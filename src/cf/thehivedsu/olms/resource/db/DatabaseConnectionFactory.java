package cf.thehivedsu.olms.resource.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

	public static Connection getConnection() throws SQLException {
		return getDBCPConnection();
	}

	public static Connection getDBCPConnection() throws SQLException {
		return DBCPDataSourceFactory.getDataSource().getConnection();
	}

	public static Connection getJNDIConnection() throws SQLException {
		return JNDIDBDataSourceFactory.getDataSource().getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException {
		return MySQLDataSourceFactory.getDataSource().getConnection();
	}

	public static Connection getJDBCConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		DatabaseConfigBean config = DatabaseConfigBean.getConfigFromEnvironmentVariables();

		return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
	}
}
