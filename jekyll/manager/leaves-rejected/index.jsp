---
layout: manager
title: Rejected Leaves
jsp_imports:
  cf.thehivedsu.olms.bean.LeaveApplicationBean: page
  cf.thehivedsu.olms.bean.EmployeeBean: page
  cf.thehivedsu.olms.dao.LeaveApplicationDAO: page
---
<% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getLeavesRejectedByManager(sessionBean.getEmployeeID()); %>
{% include application-list.jsp %}
