/**
 * 
 */
package cf.thehivedsu.olms.resource.db;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author abhishekmunie
 *
 */
public class DatabaseConfigBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171322781068033011L;

	private String url;
	private String username;
	private String password;
	private String driverClassName = "com.mysql.jdbc.Driver";

	/**
	 * 
	 */
	public DatabaseConfigBean() {
	}

	/**
	 * @param url
	 * @param username
	 * @param password
	 */
	public DatabaseConfigBean(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 * @param envVar
	 */
	public static DatabaseConfigBean getConfigFromEnvironmentVariables(String envVar) {
		URI dbUri = null;
		try {
			dbUri = new URI(System.getenv(envVar));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String url = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
		return new DatabaseConfigBean(url, username, password);
	}

	public static DatabaseConfigBean getConfigFromEnvironmentVariables() {
		return getConfigFromEnvironmentVariables("DATABASE_URL");
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName
	 *            the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

}
