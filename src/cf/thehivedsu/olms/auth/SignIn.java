package cf.thehivedsu.olms.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.admin.Admin;
import cf.thehivedsu.olms.bean.CredentialBean;
import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.bean.SessionBean;
import cf.thehivedsu.olms.dao.AuthenticationDAO;

/**
 * Servlet implementation class SignIn
 */
@WebServlet(description = "Handles SignIng Requests", urlPatterns = { "/auth/signin", "/auth/signin/" })
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String jspPath = "/auth/signin/index.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Auth.isRequestAuthrized(request)) {
			redirectAuthorized(request, response);
		} else {
			request.getRequestDispatcher(jspPath).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Auth.isRequestAuthrized(request)) {
			redirectAuthorized(request, response);
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		CredentialBean credential = new CredentialBean(username, password);

		// BeanUtils.populate(credential, request.getParameterMap());

		// if (username.indexOf('@') < 0) {
		// username = username + System.getenv("COMPANY_DOMAIN");
		// }

		EmployeeBean employee;

		if (Admin.authenticate(credential)) {
			HttpSession session = request.getSession();
			SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
			sessionBean.setEmployeeID(0);
			redirectAuthorized(request, response);
		} else if ((employee = AuthenticationDAO.authenticateUser(credential)) != null) {
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
			sessionBean.setEmployeeID(employee.getId());
			redirectAuthorized(request, response);
		} else {
			request.setAttribute("errorMessage", "InvalidCredentils");
			request.getRequestDispatcher(jspPath).forward(request, response);
		}
	}

	private void redirectAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirect = request.getParameter("redirect");
		if (redirect != null) {
			// makes sure redirecting within site
			if (redirect.charAt(0) != '/') {
				redirect = "/" + redirect;
			}
		}
		System.out.println(redirect);
		if (redirect != null) {
			response.sendRedirect(redirect);
		} else {
			response.sendRedirect(URLConstants.DASHBOARD);
		}
	}

}
