package cf.thehivedsu.olms.resource.db;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLDataSourceFactory {

	public static DataSource getDataSource() {
		MysqlDataSource mysqlDS = null;
		
		DatabaseConfigBean config = new DatabaseConfigBean(null);
		
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(config.getUrl());
		mysqlDS.setUser(config.getUsername());
		mysqlDS.setPassword(config.getPassword());
		return mysqlDS;
	}

}
