package cf.thehivedsu.olms.test.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

/**
 * Servlet implementation class SQLConnectionTest
 */
@WebServlet("/_test/db/JDBCConnection")
public class SQLConnectionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SQLConnectionTest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PrintWriter out = response.getWriter();

		response.setContentType("text/plain");
		try {
			conn = DatabaseConnectionFactory.getJDBCConnection();
		} catch (SQLException e) {
			out.println("MySQL Errror: " + e.getMessage());
			e.printStackTrace();
		} finally {
			out.println("MySQL Status: " + (conn != null ? "OK" : "Error"));
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
