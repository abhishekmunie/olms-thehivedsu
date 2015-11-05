---
layout: employee
title: Application Details
---

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Application ID: <%=applicationId%></h3>
  </div>
  <div class="panel-body">
    <div class="form-horizontal">
      {% include application.jsp %}
      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-6">
          <form action="/" method="post">
            <input type="hidden" name="action" value="cancel" />
            <input type="submit" value="Cancel" class="btn btn-danger btn-block" />
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

