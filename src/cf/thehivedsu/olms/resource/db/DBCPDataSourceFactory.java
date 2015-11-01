package cf.thehivedsu.olms.resource.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSourceFactory {

	public static DataSource getDataSource() {
		DatabaseConfigBean config = new DatabaseConfigBean(null);
		BasicDataSource ds = new BasicDataSource();

		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(config.getUrl());
		ds.setUsername(config.getUsername());
		ds.setPassword(config.getPassword());

		return ds;
	}
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

}