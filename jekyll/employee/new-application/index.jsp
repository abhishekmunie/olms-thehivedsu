---
layout: employee
title: New Leave Application
---


<form role="form" action="/employee/application/" method="post" class="form-horizontal ws-validate">
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="LeaveType">Leave Type<span class="mandatory">*</span>
    <span class="info-text">(As per policy)</span>
    </label>
    <div class="col-sm-9">
      <select name="LeaveType" id="LeaveType" class="form-control input-sm" aria-required="true" required >
      <option disabled>----- Select -----</option>
      <option value="casual">Casual</option>
      <option value="medical">Medical</option>
      </select>
      <p class="help-block">Help text.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="LeaveType">Start Date/Time<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <input type="datetime-local" data-dependent-validation='{"from": "end-date", "prop": "max"}' id="start-date" placeholder="" required  class="form-control min-today" />
      <p class="help-block">Help text.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="LeaveType">End Date/Time<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <input type="datetime-local" data-dependent-validation='{"from": "start-date", "prop": "min"}' id="end-date" placeholder="" required  class="form-control" />
      <p class="help-block">Help text.</p>
    </div>
  </div>
  <div class="form-group has-feedback">
    <label class="col-sm-3 control-label" for="LeaveType">Reason<span class="mandatory">*</span></label>
    <div class="col-sm-9">
      <textarea class="form-control " required ></textarea>
      <p class="help-block">Help text.</p>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <input type="submit" value="Apply" class="btn btn-primary">
      <input type="reset" value="Clear" class="btn btn-default">
    </div>
  </div>
</form>
