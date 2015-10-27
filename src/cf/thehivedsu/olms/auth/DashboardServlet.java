package cf.thehivedsu.olms.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.bean.RequestBean;
import cf.thehivedsu.olms.bean.SessionBean;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard/*" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectURL = URLConstants.SIGNIN;

		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		int employeeId = sessionBean.getEmployeeID();

		if (employeeId == 0) {
			redirectURL = URLConstants.ADMIN_PORTAL;
		} else if (employeeId > 0) {
			RequestBean requestBean = (RequestBean) request.getAttribute("requestBean");
			if (requestBean.isManager()) {
				redirectURL = URLConstants.MANAGER_PORTAL;
			} else {
				redirectURL = URLConstants.EMPLOYEE_PORTAL;
			}
		}

		if (redirectURL != URLConstants.SIGNIN) {
			redirectURL = request.getRequestURI().replaceFirst("^/dashboard", redirectURL).replaceAll("//", "/");
		}

		response.sendRedirect(redirectURL);
	}

}
