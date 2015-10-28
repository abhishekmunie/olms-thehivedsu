package cf.thehivedsu.olms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

public class EmployeeDAO {

	private static final String employeeWithIdQuery = "SELECT * FROM Employee WHERE `id` = ? ";

	public static EmployeeBean employeeWithId(int employeeID) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DatabaseConnectionFactory.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement = conn.prepareStatement(employeeWithIdQuery);
			statement.setInt(1, employeeID);

			resultSet = statement.executeQuery();
			BeanProcessor bp = new BeanProcessor();
			if (resultSet.first()) {
				EmployeeBean employee = bp.toBean(resultSet, EmployeeBean.class);
				return employee;
			}
			System.out.println("Employee wit id <" + employeeID + "> not found.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn, statement, resultSet);
		}
		return null;

	}
}
