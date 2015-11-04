<table class="table table-striped table-hover">
  <thead>
    <tr>
      {% unless include.hideEmployeeId %}<th>Employee ID</th>{% endunless %}
      <th>Employee Name</th>
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
      {% unless include.hideEmployeeId %}<td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getEmployeeId()%></a></td>{% endunless %}
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getEmployee().getFullName()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getId()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getType()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getFromDateTime()%></a></td>
      <td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getToDateTime()%></a></td>
      {% unless include.hideStatus %}<td><a href="../application/<%=leaveApplication.getId()%>"><%=leaveApplication.getStatusName()%></a></td>{% endunless %}
    </tr>
  <% } %>
  </tbody>
</table>
