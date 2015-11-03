<table class="table table-striped table-hover">
  <thead>
    <tr>
      <th>Employee ID</th>
      <th>Employee Name</th>
      <th>Application ID</th>
      <th>Type</th>
      <th>Start Date</th>
      <th>End Date</th>
    </tr>
  </thead>
  <tbody>
  <% for (LeaveApplicationBean leaveApplication : leaveApplications) { %>
    <tr>
      <td><%=leaveApplication.getEmployeeId()%></td>
      <td><%=leaveApplication.getEmployee().getFullName()%></td>
      <td><%=leaveApplication.getId()%></td>
      <td><%=leaveApplication.getType()%></td>
      <td><%=leaveApplication.getFromDateTime()%></td>
      <td><%=leaveApplication.getToDateTime()%></td>
    <tr>
  <% } %>
  </tbody>
</table>
