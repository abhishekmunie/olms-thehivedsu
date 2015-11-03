package cf.thehivedsu.olms.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.bean.RequestBean;
import cf.thehivedsu.olms.bean.SessionBean;

public class Auth {

	public static boolean isRequestAuthrized(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");

		return sessionBean.getEmployeeID() >= 0;
	}

	public static boolean isRequestFromEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");

		return sessionBean.getEmployeeID() > 0;

	}

	public static boolean isRequestFromManager(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");

		int employeeID = sessionBean.getEmployeeID();

		if (employeeID > 0) {
			RequestBean requestBean = (RequestBean) request.getAttribute("requestBean");
			return requestBean.isManager();
		}

		return false;
	}
}
