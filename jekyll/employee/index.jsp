---
layout: employee
title: Employee Dashboard
---

<!-- <div class="panel panel-default"> -->
  <div class="panel-heading">
    <h3 class="panel-title">Employee ID: <jsp:getProperty name="employeeBean" property="id" /></h3>
  </div>
  <div class="panel-body">
    <div class="form-horizontal">
      <div class="form-group has-feedback">
        <label class="col-sm-3 control-label" for="GivenName">Given Name</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="givenName" /></div>
        </div>
      </div>
      <div class="form-group has-feedback">
        <label class="col-sm-3 control-label" for="FamilyName">Family Name</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="familyName" /></div>
        </div>
      </div>
      <div class="form-group has-feedback">
        <label for="Email" class="col-sm-3 control-label">Email</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="email" /></div>
        </div>
      </div>
      <div class="form-group has-feedback">
        <label for="ContactNumber" class="col-sm-3 control-label">Contact Number</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="contactNumber" /></div>
        </div>
      </div>
      <div class="form-group has-feedback">
        <label class="col-sm-3 control-label" for="DateOfBirth">Date of Birth</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="dateOfBirth" /></div>
        </div>
      </div>
      <hr />
      <h4>Present Residential Address Details (where employee presently resides)</h4>
      <div class="form-group has-feedback">
        <label class="col-sm-3 control-label" for="Present Address">Present Address</label>
        <div class="col-sm-9">
          <div class="form-control-static"><jsp:getProperty name="employeeBean" property="address" /></div>
        </div>
      </div>
    </div>
  </div>
<!-- </div> -->
