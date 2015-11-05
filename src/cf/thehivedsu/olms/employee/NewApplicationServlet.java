package cf.thehivedsu.olms.employee;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.bean.LeaveApplicationBean;
import cf.thehivedsu.olms.bean.ManagerBean;
import cf.thehivedsu.olms.bean.SessionBean;
import cf.thehivedsu.olms.dao.LeaveApplicationDAO;
import cf.thehivedsu.olms.resource.email.Email;

/**
 * Servlet implementation class NewApplicationServlet
 */
@WebServlet({ "/employee/new-application", "/employee/new-application/" })
public class NewApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewApplicationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static String jspPath = "/employee/new-application/index.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		int employeeId = sessionBean.getEmployeeID();
		String leaveType = request.getParameter("leaveType");
		String fromDateTime = request.getParameter("fromDateTime");
		String toDateTime = request.getParameter("toDateTime");
		String reason = request.getParameter("reason");

		System.out.println("from: " + fromDateTime);
		System.out.println("to: " + toDateTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			Date fromSQLDateTime = new Date(dateFormat.parse(fromDateTime).getTime());
			Date toSQLDateTime = new Date(dateFormat.parse(toDateTime).getTime());

			LeaveApplicationBean newLeaveApplication = new LeaveApplicationBean();
			newLeaveApplication.setEmployeeId(employeeId);
			newLeaveApplication.setFromDateTime(fromSQLDateTime);
			newLeaveApplication.setToDateTime(toSQLDateTime);
			newLeaveApplication.setType(leaveType);
			newLeaveApplication.setReason(reason);

			int applicationId = LeaveApplicationDAO.insert(newLeaveApplication);
			if (applicationId == -1) {
				request.setAttribute("errorMessage", "Failed to submit the application.");
			} else {
				request.setAttribute("successMessage",
						"Leave Application was submitted successflly. Application ID: " + applicationId + ".");
				EmployeeBean employee = (EmployeeBean) request.getAttribute("employeeBean");
				ManagerBean manager = employee.getManager();
				EmployeeBean managersEmployeeData = manager.getEmployeeData();
				String recipient = managersEmployeeData.getEmail();
				String subject = "New Leave Request from Employee (" + employeeId + ")";
				String message = "Dear " + managersEmployeeData.getFullName() + ",\n" + employee.getFullName()
						+ " sent you a new Leave Request. https://http://olms.thehivedsu.cf/manager/application/"
						+ applicationId;
				Email.sendSimpleEmailInBackground(recipient, subject, message);
			}
			request.getRequestDispatcher(jspPath).forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
