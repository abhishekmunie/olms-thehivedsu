package cf.thehivedsu.olms.bean;

import java.io.Serializable;

public class EmployeeBean implements Serializable {

	/**
	 * Auto Generated Serial Version ID
	 */
	private static final long serialVersionUID = -8451294145162268727L;

	private int id;
	private String givenName;
	private String familyName;
	private String email;
	private String contactNumber;
	private String dateOfBirth;
	private String address;
	private int managerId;
	private ManagerBean manager;

	/**
	 * 
	 */
	public EmployeeBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param givenName
	 * @param familyName
	 * @param email
	 * @param contactNumber
	 * @param dateOfBirth
	 * @param address
	 * @param managerID
	 */
	public EmployeeBean(int id, String givenName, String familyName, String email, String contactNumber,
			String dateOfBirth, String address, int managerID) {
		super();
		this.id = id;
		this.givenName = givenName;
		this.familyName = familyName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.managerId = managerID;
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
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the managerID
	 */
	public int getManagerID() {
		return managerId;
	}

	/**
	 * @param managerID
	 *            the managerID to set
	 */
	public void setManagerID(int managerID) {
		this.managerId = managerID;
	}

	public boolean hasManager() {
		return this.managerId != 0;
	}

	/**
	 * 
	 * @return
	 */
	public ManagerBean getManager() {
		if (this.manager != null) {
			return manager;
		}

		return null;
	}

}
