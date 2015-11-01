package cf.thehivedsu.olms.resource.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class DBCPDataSourceFactory {

	private DBCPDataSourceFactory() {
	}

	private static volatile DataSource sharedDataSource;

	private static void createSharedDataSource() {
		DatabaseConfigBean config = DatabaseConfigBean.getConfigFromEnvironmentVariables();
		BasicDataSource ds = new BasicDataSource();

		ds.setDriverClassName(config.getDriverClassName());
		ds.setUrl(config.getUrl());
		ds.setUsername(config.getUsername());
		ds.setPassword(config.getPassword());

		ds.setMaxIdle(5);
		ds.setMaxTotal(10);
		ds.setMaxWaitMillis(10000);

		sharedDataSource = ds;
	}

	public static DataSource getDataSource() {
		if (sharedDataSource == null) {
			synchronized (DBCPDataSourceFactory.class) {
				if (sharedDataSource == null) {
					createSharedDataSource();
				}
			}
		}
		return sharedDataSource;
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	private static volatile PoolingDataSource<PoolableConnection> sharedPoolingDataSource;

	private static void createSharedPoolingDataSource() {
		DatabaseConfigBean config = DatabaseConfigBean.getConfigFromEnvironmentVariables();
		// First, we'll create a ConnectionFactory that the
		// pool will use to create Connections.
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(config.getUrl(), config.getUsername(),
				config.getPassword());
		// Next we'll create the PoolableConnectionFactory, which wraps
		// the "real" Connections created by the ConnectionFactory with
		// the classes that implement the pooling functionality.
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		// Now we'll need a ObjectPool that serves as the
		// actual pool of connections.
		//
		// We'll use a GenericObjectPool instance, although
		// any ObjectPool implementation will suffice.
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
		// Set the factory's pool property to the owning pool
		poolableConnectionFactory.setPool(connectionPool);
		// Finally, we create the PoolingDriver itself,
		// passing in the object pool we created.
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

		sharedPoolingDataSource = dataSource;
	}

	public static DataSource getPoolingDataSource() {
		if (sharedPoolingDataSource == null) {
			synchronized (DBCPDataSourceFactory.class) {
				if (sharedPoolingDataSource == null) {
					createSharedPoolingDataSource();
				}
			}
		}
		return sharedPoolingDataSource;
	}

	public static Connection getPoolingConnection() throws SQLException {
		return getPoolingDataSource().getConnection();
	}

	static {
		// initializeSharedPoolingJNDIDataSource();
	}

	private static void initializeSharedPoolingJNDIDataSource() {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		System.setProperty(Context.PROVIDER_URL, "file:///tmp");
		DatabaseConfigBean config = DatabaseConfigBean.getConfigFromEnvironmentVariables();
		InitialContext initContext;
		try {
			initContext = new InitialContext();

			// Construct DriverAdapterCPDS reference
			Reference cpdsRef = new Reference("org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS",
					"org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS", null);
			cpdsRef.add(new StringRefAddr("driver", config.getDriverClassName()));
			cpdsRef.add(new StringRefAddr("url", config.getUrl()));
			cpdsRef.add(new StringRefAddr("user", config.getUsername()));
			cpdsRef.add(new StringRefAddr("password", config.getPassword()));
			initContext.rebind("jdbc/cpds", cpdsRef);

			// Construct PerUserPoolDataSource reference
			Reference ref = new Reference("org.apache.commons.dbcp2.datasources.PerUserPoolDataSource",
					"org.apache.commons.dbcp2.datasources.PerUserPoolDataSourceFactory", null);
			ref.add(new StringRefAddr("dataSourceName", "jdbc/cpds"));
			ref.add(new StringRefAddr("defaultMaxTotal", "10"));
			ref.add(new StringRefAddr("defaultMaxIdle", "5"));
			ref.add(new StringRefAddr("defaultMaxWaitMillis", "10000"));
			initContext.rebind("jdbc/peruser", ref);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getPoolingJNDIConnection() throws SQLException {
		InitialContext initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("jdbc/peruser");
			return ds.getConnection("foo", "bar");
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
