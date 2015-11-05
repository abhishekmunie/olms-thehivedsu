---
layout: employee
title: New Leave Application
---
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
<form role="form" action="/employee/new-application/" method="post" class="form-horizontal ws-validate">
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="leaveType">Leave Type<span class="mandatory">*</span>
    <span class="info-text">(As per policy)</span>
    </label>
    <div class="col-sm-9">
      <select name="leaveType" id="leaveType" class="form-control input-sm" aria-required="true" required >
        <option disabled>----- Select -----</option>
        <option value="CL">Casual</option>
        <option value="ML">Medical</option>
        <option value="VL">Vacation</option>
        <option value="SL">Study</option>
        <option value="PC">Pregnancy/Child Care</option>
        <option value="FR">Family Responsibility</option>
        <option value="UL">Unpaid</option>
      </select>
      <p class="help-block">The type of leave you wat to apply for.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="fromDateTime">From Date/Time<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <input name="fromDateTime" id="fromDateTime" type="datetime-local" data-dependent-validation='{"from": "end-date", "prop": "max"}' placeholder="yyyy-mm-dd HH:MM" required  class="form-control min-today" />
      <p class="help-block">Start Date-Time for leave in "yyyy-mm-dd HH:MM" format.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="toDateTime">To Date/Time<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <input name="toDateTime" id="toDateTime" type="datetime-local" data-dependent-validation='{"from": "start-date", "prop": "min"}' placeholder="yyyy-mm-dd HH:MM" required  class="form-control" />
      <p class="help-block">End Date-Time for leave in "yyyy-mm-dd HH:MM" format.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="reason">Reason<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <textarea name="reason" id="reason" class="form-control " required ></textarea>
      <p class="help-block">Reason for absence.</p>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <input type="submit" class="btn btn-primary">
      <input type="reset" class="btn btn-default">
    </div>
  </div>
</form>
