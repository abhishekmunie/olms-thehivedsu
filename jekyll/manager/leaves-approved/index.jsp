---
layout: manager
title: Approved Leaves
jsp_imports:
  cf.thehivedsu.olms.bean.LeaveApplicationBean: page
  cf.thehivedsu.olms.bean.EmployeeBean: page
  cf.thehivedsu.olms.dao.LeaveApplicationDAO: page
---
<% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getLeavesApprovedByManager(sessionBean.getEmployeeID()); %>
{% include application-list.jsp %}
