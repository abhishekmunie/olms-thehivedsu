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

	private String username;
	private String password;
	private String url;
	
	/**
	 * 
	 */
	public DatabaseConfigBean() {
	}

	/**
	 * 
	 * @param envVar
	 */
	public DatabaseConfigBean(String envVar) {
		if (envVar == null) {
			envVar = "DATABASE_URL";
		}
		URI dbUri = null;
		try {
			dbUri = new URI(System.getenv(envVar));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.username = dbUri.getUserInfo().split(":")[0];
		this.password = dbUri.getUserInfo().split(":")[1];
		this.url = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
