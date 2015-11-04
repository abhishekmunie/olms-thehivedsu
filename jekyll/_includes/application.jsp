
<div class="form-group has-feedback">
  <label class="col-sm-3 control-label" for="eid">Employee ID</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationApplierBean" property="id" /></div>
  </div>
</div>
<div class="form-group has-feedback">
  <label class="col-sm-3 control-label" for="ename">Employee Name</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationApplierBean" property="fullName" /></div>
  </div>
</div>
<div class="form-group has-feedback">
  <label class="col-sm-3 control-label" for="etype">Application Type</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationBean" property="typeName" /></div>
  </div>
</div>
<hr />
<h4>Application Details</h4>
<div class="form-group has-feedback">
  <label class="col-sm-3 control-label" for="etype">From</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationBean" property="fromDateTime" /></div>
  </div>
</div>
<div class="form-group has-feedback">
  <label class="col-sm-3 control-label" for="etype">To</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationBean" property="toDateTime" /></div>
  </div>
</div>
<div class="form-group has-feedback">
  <label for="PassportType" class="col-sm-3 control-label">Reason</label>
  <div class="col-sm-9">
    <div class="form-control-static"><jsp:getProperty name="leaveApplicationBean" property="reason" /></div>
  </div>
</div>
