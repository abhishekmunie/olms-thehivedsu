---
layout: manager
title: "Application Details"
---
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Application ID: <%=applicationId%></h3>
  </div>
  <div class="panel-body">
    <div class="form-horizontal">
      {% include application.jsp %}
      <div class="form-group">
        <div class="col-sm-6">
          <form action="/" method="post">
            <input type="hidden" name="action" value="validate" /> <input
              type="submit" value="Accept" class="btn btn-primary btn-block" />
          </form>
        </div>
        <div class="col-sm-6">
          <form action="/" method="post">
            <input type="hidden" name="action" value="reject" /> <input
              type="submit" value="Reject" class="btn btn-danger btn-block" />
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Previous Leaves</h3>
  </div>
  <div class="panel-body">
    <% Vector<LeaveApplicationBean> leaveApplications = LeaveApplicationDAO.getLeavesRejectedByManager(sessionBean.getEmployeeID()); %>
    {% include application-list.jsp hideStatus="true" hideEmployeeId="true" %}
  </div>
</div>
