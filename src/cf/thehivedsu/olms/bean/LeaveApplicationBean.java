/**
 * 
 */
package cf.thehivedsu.olms.bean;

import java.io.Serializable;
import java.sql.Date;

import cf.thehivedsu.olms.dao.EmployeeDAO;

/**
 * @author
 *
 */
public class LeaveApplicationBean implements Serializable {

	private int id;
	private int employeeId;
	private Date fromDateTime;
	private Date toDateTime;
	private String type;
	private String reason;
	private String status;
	private int approverId;

	private EmployeeBean employee;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4334517325249190047L;

	/**
	 * 
	 */
	public LeaveApplicationBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the fromDateTime
	 */
	public Date getFromDateTime() {
		return fromDateTime;
	}

	/**
	 * @param fromDateTime
	 *            the fromDateTime to set
	 */
	public void setFromDateTime(Date fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	/**
	 * @return the toDateTime
	 */
	public Date getToDateTime() {
		return toDateTime;
	}

	/**
	 * @param toDateTime
	 *            the toDateTime to set
	 */
	public void setToDateTime(Date toDateTime) {
		this.toDateTime = toDateTime;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the approverId
	 */
	public int getApproverId() {
		return approverId;
	}

	/**
	 * @param approverId
	 *            the approverId to set
	 */
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	/**
	 * @return the employee
	 */
	public EmployeeBean getEmployee() {
		if (employee == null) {
			employee = EmployeeDAO.employeeWithId(employeeId);
		}
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

}
