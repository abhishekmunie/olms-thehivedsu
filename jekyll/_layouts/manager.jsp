---
layout: default
---

<div class="container-fluid">
  <div class="page-header">
    <h1 class="text-muted text-center">Manager Portal</h1>
  </div>
  <div class="row">
    <div class="col-sm-3 col-md-2">
      <ul class="nav nav-pills nav-stacked nav-sidebar">
        <li class="{% is_active /manager/index.html%}"><a href="/manager/">Dashboard</a></li>
        <li role="presentation" class="divider"></li>
        <li class="{% is_active_level /manager/leave-requests/%}"><a href="/manager/leave-requests/">Leave Requests</a></li>
        <li class="{% is_active_level /manager/leaves-approved/%}"><a href="/manager/leaves-approved/">Approved Leaves</a></li>
        <li class="{% is_active_level /manager/leaves-rejected/%}"><a href="/manager/leaves-rejected/">Rejected Leaves</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-md-10 main">
      <div class="container-fluid">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title text-center">{{ page.title }}</h3>
          </div>
          <div class="panel-body">{{content}}</div>
        </div>
      </div>
    </div>
  </div>
</div>