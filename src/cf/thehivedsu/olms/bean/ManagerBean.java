package cf.thehivedsu.olms.bean;

import java.io.Serializable;

import cf.thehivedsu.olms.employee.Employee;

/**
 * 
 * @author abhishekmunie
 *
 */
public class ManagerBean implements Serializable {

	/**
	 * Auto Generated Serial Version ID
	 */
	private static final long serialVersionUID = -4297838203594748813L;

	private int id;
	private int employeeId;
	private String teamName;

	private EmployeeBean employeeData;

	public ManagerBean() {
	}

	/**
	 * @param id
	 * @param employeeId
	 * @param teamName
	 */
	public ManagerBean(int id, int employeeId, String teamName) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.teamName = teamName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the employeeData
	 */
	public EmployeeBean getEmployeeData() {
		if (employeeData == null) {
			employeeData = Employee.employeeWithId(employeeId);
		}
		return employeeData;
	}

	/**
	 * @param employeeData
	 *            the employeeData to set
	 */
	public void setEmployeeData(EmployeeBean employeeData) {
		this.employeeData = employeeData;
	}

}
