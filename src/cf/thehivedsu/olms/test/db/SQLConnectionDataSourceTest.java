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

import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

/**
 * Servlet implementation class SQLConnnectionPoolTest
 */
@WebServlet("/_test/db/DataSourceConnection")
public class SQLConnectionDataSourceTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SQLConnectionDataSourceTest() {
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
		Connection conn = null;
		PrintWriter out = response.getWriter();

		response.setContentType("text/plain");
		try {
			conn = DatabaseConnectionFactory.getConnection();
		} catch (SQLException e) {
			out.println("MySQL Data Source Errror: " + e.getMessage());
			e.printStackTrace();
			return;
		} finally {
			out.println("MySQL Data Source Status: " + (conn != null ? "OK" : "Error"));
			try {
				DbUtils.close(conn);
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
