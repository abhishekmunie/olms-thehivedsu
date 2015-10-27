package cf.thehivedsu.olms.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.thehivedsu.olms.ScriptRunner;
import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.Utilities;

/**
 * Servlet implementation class ResetAppServlet
 */
@WebServlet(urlPatterns = { "/admin/reset", "/admin/reset/" })
public class ResetAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetAppServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private ServletConfig servletConfig;
	private String jspURL = "/admin/reset/index.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletConfig.getServletContext().getRequestDispatcher(jspURL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("isForwordedRequest", true);
		String confirmation = request.getParameter("confirmation");
		if (confirmation.equals("Reset")) {
			try {
				InputStream initScriptInputStream = servletConfig.getServletContext()
						.getResourceAsStream(URLConstants.INIT_SQL_FILE);
				resetApp(initScriptInputStream);
				request.setAttribute("successmsg", "App was reset successfully.");
				request.getRequestDispatcher(jspURL).forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errormsg", "Couldn't reset app.");
				request.setAttribute("errorex", e);
				request.getRequestDispatcher(jspURL).forward(request, response);
			}
		} else {
			request.setAttribute("errormsg", "Incorrect confirmation text.");
			Utilities.setParametersAsAttributes(request);
			request.getRequestDispatcher(jspURL).forward(request, response);
		}
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig;
	}

	public static void resetApp(InputStream initScriptInputStream) throws IOException, SQLException {
		Connection conn = null;
		try {
			conn = Utilities.getSQLConnection();
			ScriptRunner scriptRunner = new ScriptRunner(conn, false, false);
			InputStreamReader reader = new InputStreamReader(initScriptInputStream);
			scriptRunner.runScript(reader);
			reader.close();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
