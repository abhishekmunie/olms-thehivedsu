package cf.thehivedsu.olms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.ManagerBean;
import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

public class ManagerDAO {

	private static final String managerWithIdQuery = "SELECT Manager.* FROM Manager WHERE `id` = ? ";

	public static ManagerBean managerWithId(int managerId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DatabaseConnectionFactory.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement = conn.prepareStatement(managerWithIdQuery);
			statement.setInt(1, managerId);

			resultSet = statement.executeQuery();
			BeanProcessor bp = new BeanProcessor();
			if (resultSet.first()) {
				ManagerBean manager = bp.toBean(resultSet, ManagerBean.class);
				return manager;
			}
			System.out.println("Manager wit id <" + managerId + "> not found.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn, statement, resultSet);
		}
		return null;
	}

	private static String managerAndEmployeeRecordForManagerWithId = "SELECT * FROM Manager INNER JOIN Employee ON Manager.employeeId = Employee.id  WHERE Manager.id = ? ";

	public static ManagerBean managerAndEmployeeRecordForManagerWithId(int managerId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DatabaseConnectionFactory.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement = conn.prepareStatement(managerAndEmployeeRecordForManagerWithId);
			statement.setInt(1, managerId);

			resultSet = statement.executeQuery();
			BeanProcessor bp = new BeanProcessor();
			if (resultSet.first()) {
				ManagerBean employee = bp.toBean(resultSet, ManagerBean.class);
				return employee;
			}
			System.out.println("Manager wit id <" + managerId + "> not found.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn, statement, resultSet);
		}
		return null;
	}

	public static final String isManagerQuery = "SELECT count(*) FROM Manager WHERE employeeId = ? ";

	public static boolean isManager(int employeeID) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DatabaseConnectionFactory.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			statement = conn.prepareStatement(isManagerQuery);
			statement.setInt(1, employeeID);

			resultSet = statement.executeQuery();
			if (resultSet.first()) {
				int managerCount = resultSet.getInt(1);
				return managerCount != 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn, statement, resultSet);
		}
		return false;
	}

}
