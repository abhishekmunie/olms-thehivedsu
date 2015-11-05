package cf.thehivedsu.olms.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.bean.LeaveApplicationBean;
import cf.thehivedsu.olms.bean.SessionBean;
import cf.thehivedsu.olms.dao.LeaveApplicationDAO;
import cf.thehivedsu.olms.resource.email.Email;

/**
 * Servlet implementation class ApplicationActionServlet
 */
@WebServlet({ "/manager/application", "/manager/application/*" })
public class ApplicationActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplicationActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String jspPath = "/manager/application.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlComponents[] = request.getRequestURI().split("/");
		int applicationId = -1;
		try {
			applicationId = Integer.parseInt(urlComponents[3]);
		} catch (NumberFormatException e) {
			response.sendError(404);
			return;
		}
		request.setAttribute("applicationId", applicationId);
		request.getRequestDispatcher(jspPath).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		try {
			String urlComponents[] = request.getRequestURI().split("/");
			int applicationId = Integer.parseInt(urlComponents[3]);
			String action = request.getParameter("action");
			String status;
			switch (action) {
			case "Accept":
				status = "A";
				break;
			case "Reject":
				status = "R";
				break;
			default:
				return;
			}

			int affected = LeaveApplicationDAO.setApplicationStatus(applicationId, status, sessionBean.getEmployeeID());
			if (affected != 1) {
				request.setAttribute("errorMessage", "Failed to " + action.toLowerCase() + " the application.");
			} else {
				request.setAttribute("successMessage",
						"Leave Application was " + action.toLowerCase() + "ed successflly.");

				LeaveApplicationBean application = LeaveApplicationDAO.getLeaveApplicationWithId(applicationId);
				EmployeeBean employee = application.getEmployee();
				String recipient = employee.getEmail();
				String subject = "Your application with id (" + applicationId + ") was " + action.toLowerCase() + "ed.";
				String message = "Dear " + employee.getFullName() + ",\n" + "Your application with id (" + applicationId
						+ ") was " + action.toLowerCase() + "ed. https://olms.thehivedsu.cf/employee/application/"
						+ applicationId;
				Email.sendSimpleEmailInBackground(recipient, subject, message);
			}
			request.setAttribute("applicationId", applicationId);
			request.getRequestDispatcher(jspPath).forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(404);
		}

	}

}
