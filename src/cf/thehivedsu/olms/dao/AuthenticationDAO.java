package cf.thehivedsu.olms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.CredentialBean;
import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

public class AuthenticationDAO {

	private static final String authenticationQuery = "SELECT Employee.* FROM Credential INNER JOIN Employee ON Employee.id = Credential.employeeId WHERE `username` = ? AND `password` = ? ";

	public static EmployeeBean authenticateUser(CredentialBean credential) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DatabaseConnectionFactory.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement = conn.prepareStatement(authenticationQuery);
			statement.setString(1, credential.getUsername());
			statement.setString(2, credential.getPassword());

			resultSet = statement.executeQuery();
			BeanProcessor bp = new BeanProcessor();
			if (resultSet.first()) {
				EmployeeBean employee = bp.toBean(resultSet, EmployeeBean.class);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn, statement, resultSet);
		}
		return null;
	}
}
