---
layout: default
---

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2">
      <ul class="nav nav-pills nav-stacked nav-sidebar">
        <li class="{% is_active /admin/index.jsp%}"><a href="/admin/">Dashboard</a></li>
        <li role="presentation" class="divider"></li>
        <li class="{% is_active_level /admin/reset%}"><a href="/admin/reset/">Reset</a></li>
        <li class="{% is_active_level /admin/analytics%}"><a href="/admin/analytics/">Analytics</a></li>
        <li class="{% is_active_level /admin/settings%}"><a href="/admin/settings/">Settings</a></li>
      </ul>
    </div>
    <div class="col-sm-9 col-md-10 main">
      <div class="container-fluid">{{content}}</div>
    </div>
  </div>
</div>