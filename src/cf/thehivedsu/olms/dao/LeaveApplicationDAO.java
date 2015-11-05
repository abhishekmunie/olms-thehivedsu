package cf.thehivedsu.olms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.bean.LeaveApplicationBean;
import cf.thehivedsu.olms.resource.db.DatabaseConnectionFactory;

/**
 *
 * @author
 *
 */
public class LeaveApplicationDAO {

	private static String insertSQL = "INSERT INTO `olms`.`LeaveApplication` (`employeeId`, `fromDateTime`, `toDateTime`, `type`, `reason`) VALUES (?, ?, ?, ?, ?);";

	public static int insert(LeaveApplicationBean leaveApplication) {
		ResultSet keys = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			ps.setInt(1, leaveApplication.getEmployeeId());
			ps.setDate(2, leaveApplication.getFromDateTime());
			ps.setDate(3, leaveApplication.getToDateTime());
			ps.setString(4, leaveApplication.getType());
			ps.setString(5, leaveApplication.getReason());

			int affected = ps.executeUpdate();
			if (affected == 1) {
				keys = ps.getGeneratedKeys();
				keys.first();
				int applicationId = keys.getInt(1);
				leaveApplication.setId(applicationId);
				return applicationId;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
		}
		return -1;
	}

	private static final String getLeaveApplicationWithIdSQL = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE LeaveApplication.id = ? ";

	public static LeaveApplicationBean getLeaveApplicationWithId(int id) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeaveApplicationWithIdSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, id);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.first()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
				return leaveApplicationBean;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String getLeavesForEmployeeSQL = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE employeeId = ? ";

	public static Vector<LeaveApplicationBean> getLeavesForEmployee(int employeeId) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeavesForEmployeeSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, employeeId);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
			}
			return leaveBeans;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String getPendingLeavesForManagerSQL = "SELECT LeaveApplication.*, Employee.* FROM Manager INNER JOIN Employee ON Employee.managerId = Manager.id INNER JOIN LeaveApplication ON LeaveApplication.employeeID = Employee.id WHERE Manager.employeeID = ? AND LeaveApplication.`status` = \"P\"";

	public static Vector<LeaveApplicationBean> getPendingLeavesForManager(int managersEmployeeId) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getPendingLeavesForManagerSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, managersEmployeeId);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
			}
			return leaveBeans;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String getApprovedLeavesForEmployeeSQL = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"A\" AND employeeId = ? ";

	public static Vector<LeaveApplicationBean> getApprovedLeavesForEmployee(int employeeId) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getApprovedLeavesForEmployeeSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, employeeId);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
			}
			return leaveBeans;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String getLeavesApprovedByManagerSQL = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"A\" AND approverId = ? ";

	public static Vector<LeaveApplicationBean> getLeavesApprovedByManager(int managerEmployeeID) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeavesApprovedByManagerSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, managerEmployeeID);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
			}
			return leaveBeans;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String getLeavesRejectedByManagerSQL = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"R\" AND approverId = ? ";

	public static Vector<LeaveApplicationBean> getLeavesRejectedByManager(int managerEmployeeID) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeavesRejectedByManagerSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, managerEmployeeID);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
				leaveApplicationBean.setId(rs.getInt(1));
				employeeBean.setId(leaveApplicationBean.getEmployeeId());
				leaveApplicationBean.setEmployee(employeeBean);
				leaveBeans.addElement(leaveApplicationBean);
			}
			return leaveBeans;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return null;
	}

	private static final String setApplicationStatusSQL = "UPDATE LeaveApplication SET `status` = ?, `approverId` = ? WHERE `id` = ?";

	public static int setApplicationStatus(int applicationId, String status, int approverId) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(setApplicationStatusSQL);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			st.setString(1, status);
			st.setInt(2, approverId);
			st.setInt(3, applicationId);

			int affected = st.executeUpdate();
			return affected;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
		}
		return -1;
	}

}
