package cf.thehivedsu.olms.manager;

import cf.thehivedsu.olms.dao.ManagerDAO;

public class Manager {

	public static boolean isManager(int employeeID) {
		return ManagerDAO.isManager(employeeID);
	}
}
