---
layout: employee
title: Application Status
jsp_imports:
  cf.thehivedsu.olms.bean.LeaveApplicationBean: page
  cf.thehivedsu.olms.bean.EmployeeBean: page
  cf.thehivedsu.olms.dao.LeaveApplicationDAO: page
---
<% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getLeavesForEmployee(sessionBean.getEmployeeID()); %>
{% include application-list.jsp hideEmployeeDetails="true" %}
