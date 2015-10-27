package cf.thehivedsu.olms.bean;

import java.io.Serializable;

public class CredentialBean implements Serializable {

	/**
	 * Auto Generated Serial Version ID
	 */
	private static final long serialVersionUID = 3613627518141808392L;

	private String username;
	private String password;

	/**
	 * 
	 */
	public CredentialBean() {
	}

	/**
	 * @param username
	 * @param password
	 */
	public CredentialBean(String email, String password) {
		this.username = email;
		this.password = password;
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

}
