package cf.thehivedsu.olms.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.bean.SessionBean;

public class Auth {

	public static boolean isRequestAuthrized(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");

		return sessionBean.getEmployeeID() >= 0;
	}
}
