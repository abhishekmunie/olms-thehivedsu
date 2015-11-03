package cf.thehivedsu.olms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;

import cf.thehivedsu.olms.bean.EmployeeBean;
import cf.thehivedsu.olms.bean.LeaveApplicationBean;
import cf.thehivedsu.olms.resource.DatabaseConnectionFactory;

/**
 * 
 * @author
 *
 */
public class LeaveApplicationDAO {

	private static final String getApprovedLeavesForEmployeeQuery = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"A\" AND employeeId = ? ";

	public static Vector<LeaveApplicationBean> getApprovedLeavesForEmployee(int employeeID) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getApprovedLeavesForEmployeeQuery);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, employeeID);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
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

	private static final String getLeavesApprovedByManagerQuery = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"A\" AND approverId = ? ";

	public static Vector<LeaveApplicationBean> getLeavesApprovedByManager(int managerEmployeeID) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeavesApprovedByManagerQuery);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, managerEmployeeID);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
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

	private static final String getLeavesRejectedByManagerQuery = "SELECT * FROM LeaveApplication INNER JOIN Employee ON Employee.id = LeaveApplication.employeeId WHERE `status` = \"R\" AND approverId = ? ";

	public static Vector<LeaveApplicationBean> getLeavesRejectedByManager(int managerEmployeeID) {
		ResultSet rs = null;
		try (Connection connection = DatabaseConnectionFactory.getConnection();
				PreparedStatement st = connection.prepareStatement(getLeavesRejectedByManagerQuery);) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			Vector<LeaveApplicationBean> leaveBeans = new Vector<>();
			st.setInt(1, managerEmployeeID);
			rs = st.executeQuery();

			BeanProcessor bp = new BeanProcessor();
			while (rs.next()) {
				LeaveApplicationBean leaveApplicationBean = bp.toBean(rs, LeaveApplicationBean.class);
				EmployeeBean employeeBean = bp.toBean(rs, EmployeeBean.class);
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

}
