---
layout: manager
title: "Application Details"
jsp_imports:
  cf.thehivedsu.olms.bean.LeaveApplicationBean: page
  cf.thehivedsu.olms.bean.EmployeeBean: page
  cf.thehivedsu.olms.dao.LeaveApplicationDAO: page
---
<%
  int applicationId = (int) request.getAttribute("applicationId");
  LeaveApplicationBean currentLeaveApplication = LeaveApplicationDAO.getLeaveApplicationWithId(applicationId);
  request.setAttribute("leaveApplicationBean", currentLeaveApplication);
  request.setAttribute("leaveApplicationApplierBean", currentLeaveApplication.getEmployee());
 %>
<jsp:useBean id="leaveApplicationBean" scope="request" class="cf.thehivedsu.olms.bean.LeaveApplicationBean"/>
<jsp:useBean id="leaveApplicationApplierBean" scope="request" class="cf.thehivedsu.olms.bean.EmployeeBean"/>


<%
  String successmsg = (String)request.getAttribute("successMessage");
  String errormsg = (String)request.getAttribute("errorMessage");
  Exception ex = (Exception)(request.getAttribute("errorException"));
  if (successmsg != null) {
%>
<div class="alert alert-success alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong>Success!</strong> <%= (successmsg.equals("")) ? "Login Successfull." : successmsg %>
</div>
<% } else if (errormsg != null) { %>
<div class="alert alert-danger alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <details>
    <summary><strong>Error!</strong> <%=errormsg%></summary>
    <p><%=(ex != null) ? ex.getLocalizedMessage() : ""%></p>
  </details>
</div>
<% } %>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Application ID: <%=applicationId%></h3>
  </div>
  <div class="panel-body">
    <div class="form-horizontal">
      {% include application.jsp %}

      <% if (currentLeaveApplication.getStatus().equals("P")) { %>
      <form action="" method="post">
        <div class="form-group">
          <div class="col-sm-6">
              <input type="submit" name="action" value="Accept" class="btn btn-primary btn-block" />
          </div>
          <div class="col-sm-6">
              <input type="submit" name="action" value="Reject" class="btn btn-danger btn-block" />
          </div>
        </div>
      </form>
      <% } %>
    </div>
  </div>
</div>

<% if (currentLeaveApplication.getStatus().equals("P")) { %>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Previous Leaves</h3>
  </div>
  <div class="panel-body">
    <% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getApprovedLeavesForEmployee(currentLeaveApplication.getEmployee().getId()); %>
    {% include application-list.jsp hideStatus="true" hideEmployeeId="true" %}
  </div>
</div>
<% } %>
