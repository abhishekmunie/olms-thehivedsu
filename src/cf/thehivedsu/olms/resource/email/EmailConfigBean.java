package cf.thehivedsu.olms.resource.email;

import java.io.Serializable;

public class EmailConfigBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3346908790979792955L;

	private String username;
	private String password;
	private String from;

	/**
	 * 
	 */
	public EmailConfigBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param from
	 */
	public EmailConfigBean(String username, String password, String from) {
		super();
		this.username = username;
		this.password = password;
		this.from = from;
	}

	public static EmailConfigBean getConfigFromEnvironmentVariables() {
		String username = System.getenv("SYSTEM_EMAIL_SIGNIN_ID");
		String password = System.getenv("SYSTEM_EMAIL_SIGNIN_PASSWORD");
		String from = System.getenv("SYSTEM_EMAIL");
		return new EmailConfigBean(username, password, from);
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
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return super.toString() + "(" + this.username + ", " + this.password + ", " + this.from + ")";
	}
}
