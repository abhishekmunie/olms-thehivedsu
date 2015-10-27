---
layout: admin
title: Reset App
---

<%
  String successmsg = (String)request.getAttribute("successmsg");
  String errormsg = (String)request.getAttribute("errormsg");
  Exception ex = (Exception)(request.getAttribute("errorex"));
  if (successmsg != null) {
%>
<div class="alert alert-success alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong>Success!</strong>
  <%= (successmsg.equals("")) ? "The app was successfully reset." : successmsg %>
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

<div class="jumbotron bg-danger">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h1 class="panel-title">
        Reset <i class="fa fa-bolt text-danger"></i>
      </h1>
    </div>
    <div class="panel-body">
      <!-- Button trigger modal -->
      <div class="well">
        <button class="btn btn-danger btn-lg btn-block" data-toggle="modal" data-target="#myModal">
          <i class="fa fa-trash-o fa-4x"></i>
        </button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade bg-danger" id="myModal" tabindex="-1"
  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Danger Zone</h4>
      </div>
      <div class="modal-body bg-warning">
        <strong class="lead">
          This will reset all the data stored in this app.<br />
          This action cannot be undone.<br />
        </strong>
        <button type="button" class="btn btn-success btn-block"
          data-dismiss="modal">Cancel</button>
      </div>
      <div class="modal-footer bg-danger">
        <p class="help-block text-left">If you still want to continue,
          type 'Reset' in the text field below and press Reset.</p>
        <form action="/admin/reset/" method="post" class="form-horizontal" role="form">
          <div class="input-group">
            <span class="input-group-addon hidden-xs control-label"><i class="fa fa-chain-broken"></i></span>
            <input type="text" class="form-control" name="confirmation" placeholder="Are you sure???" pattern="^Reset$" />
            <span class="input-group-btn"><input type="submit" class="btn btn-danger col-sx-12" value="Reset" /></span>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
