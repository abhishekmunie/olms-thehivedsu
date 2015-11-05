package cf.thehivedsu.olms.employee;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.dao.EmployeeDAO;

public class Employee extends EmployeeBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7605492393005662728L;

	public static EmployeeBean employeeWithId(int employeeId) {
		return EmployeeDAO.employeeWithId(employeeId);
	}
}
