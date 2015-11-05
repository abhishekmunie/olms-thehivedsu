<table class="table table-striped table-hover">
  <thead>
    <tr>
      {% unless include.hideEmployeeDetails %}<th>Employee ID</th>{% endunless %}
      {% unless include.hideEmployeeDetails %}<th>Employee Name</th>{% endunless %}
      <th>Application ID</th>
      <th>Type</th>
      <th>From</th>
      <th>To</th>
      {% unless include.hideStatus %}<th>Status</th>{% endunless %}
    </tr>
  </thead>
  <tbody>
  <% for (LeaveApplicationBean leaveApplication : leaveApplications) { %>
    <tr>
      {% unless include.hideEmployeeDetails %}<td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getEmployeeId()%></a></td>{% endunless %}
      {% unless include.hideEmployeeDetails %}<td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getEmployee().getFullName()%></a></td>{% endunless %}
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getId()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getType()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getFromDateTime()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getToDateTime()%></a></td>
      {% unless include.hideStatus %}<td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getStatusName()%></a></td>{% endunless %}
    </tr>
  <% } %>
  </tbody>
</table>
