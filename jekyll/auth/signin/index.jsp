---
layout: auth
title: Signin
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
<div class="container-fluid">
  <div id="signin-panel" class="auth-panel panel panel-default center-block">
    <div class="panel-heading">
      <h3 class="panel-title">Please enter your credentials</h3>
    </div>
    <div class="panel-body">
      <form action="" method="POST" class="form-signin ws-validate" role="form">
        <div class="form-group has-feedback">
          <label for="inputUsername">Username</label>
          <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Enter your username (e.g. steve)" autofocus required />
          <span class="glyphicon glyphicon-ok form-control-feedback"></span><span class="glyphicon glyphicon-remove form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
          <label for="inputPassword">Password</label>
          <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password" required />
          <span class="glyphicon glyphicon-ok form-control-feedback"></span>
          <span class="glyphicon glyphicon-remove form-control-feedback"></span>
        </div>
        <input class="btn btn-primary btn-block" type="submit" value="Sign in" />
      </form>
    </div>
  </div>
</div>
