package cf.thehivedsu.olms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.CredentialBean;
import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

public class AuthenticationDAO {

	private static final String authenticationQuery = "{ call authenticate(?, ?) }";

	/**
	 * Authenticates the user based on passed credential by calling the
	 * authenticate procedure on the database with the credentials.
	 * 
	 * @param credential
	 *            Users credentials to be authenticated
	 * @return employee id of the user if user authentic else -1.
	 */
	public static int authenticateUser(CredentialBean credential) {
		ResultSet resultSet = null;
		try (Connection conn = DatabaseConnectionFactory.getConnection();
				CallableStatement statement = conn.prepareCall(authenticationQuery, ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_READ_ONLY);) {
			// conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement.setString(1, credential.getUsername());
			statement.setString(2, credential.getPassword());

			resultSet = statement.executeQuery();

			if (resultSet.first()) {
				int employeeId = resultSet.getInt(1);
				return (employeeId != 0) ? employeeId : -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(resultSet);
		}
		return -1;
	}
}
