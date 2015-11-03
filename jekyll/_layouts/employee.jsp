---
layout: default
---

<div class="container-fluid">
  <div class="page-header">
    <h1 class="text-muted text-center">Employee Portal</h1>
  </div>
  <div class="row">
    <div class="col-sm-3 col-md-2">
      <ul class="nav nav-pills nav-stacked nav-sidebar">
        <li class="{% is_active /employee/index.jsp%}"><a href="/employee/">Dashboard</a></li>
        <li role="presentation" class="divider"></li>
        <% if (employeeBean.hasManager()) { %>
        <li class="{% is_active_level /employee/new-application/%}"><a href="/employee/new-application/">New Application</a></li>
        <li class="{% is_active_level /employee/status/%}"><a href="/employee/status/">Status</a></li>
        <% } %>
      </ul>
    </div>
    <div class="col-sm-9 col-md-10 main">
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title text-center">{{ page.title }}</h3>
          </div>
          <div class="panel-body">
            {{content}}
          </div>
        </div>
      </div>
    </div>
  </div>
</div>