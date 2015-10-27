/**
 * 
 */
package cf.thehivedsu.olms.bean;

import java.io.Serializable;

/**
 * @author abhishekmunie
 *
 */
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932434326879131706L;

	private int employeeID;

	/**
	 * 
	 */
	public SessionBean() {
		this(-1);
	}

	/**
	 * @param employeeID
	 */
	public SessionBean(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID
	 *            the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

}
