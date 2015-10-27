package cf.thehivedsu.olms.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.bean.SessionBean;

/**
 * Servlet implementation class SignOut
 */
@WebServlet(description = "Handles SignOut Requests", urlPatterns = { "/auth/signout", "/auth/signout/" })
public class SignOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignOut() {
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
		HttpSession session = request.getSession();
		SessionBean newSession = new SessionBean();
		session.setAttribute("sessionBean", newSession);

		String queryString = request.getQueryString();
		response.sendRedirect(URLConstants.SIGNIN + ((queryString != null) ? "?" + queryString : ""));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
