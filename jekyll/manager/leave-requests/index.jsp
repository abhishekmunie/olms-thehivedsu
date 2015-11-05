---
layout: manager
title: Leave Requests
jsp_imports:
  cf.thehivedsu.olms.bean.LeaveApplicationBean: page
  cf.thehivedsu.olms.bean.EmployeeBean: page
  cf.thehivedsu.olms.dao.LeaveApplicationDAO: page
---
<% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getPendingLeavesForManager(sessionBean.getEmployeeID()); %>
{% include application-list.jsp hideStatus="true" %}

