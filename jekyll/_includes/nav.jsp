
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

      <a class="navbar-brand site-title" href="{{ site.baseurl }}/">{{ site.name }}</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse"
      id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="{% if page.url == '/index.jsp' %}active{% endif %}"><a href="/"> <i class="fa fa-home fa-lg"></i> </a></li>
        <% if (sessionBean.getEmployeeID() > 0) { %><li class="{% is_active_level /employee%}"><a href="/employee/">Employee</a></li><% } %>
        <% if (requestBean.isManager()) { %><li class="{% is_active_level /manager%}"><a href="/manager/">Manager</a></li><% } %>
        {% for l1 in site.main_nav %}
        <li class="{% is_active_level {{l1[1]}}%}"><a href="{{l1[1]}}/">{{l1[0]}}</a></li>
        {% endfor %}
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <% if (sessionBean.getEmployeeID() >= 0) { %>
        <li class="dropdown {% is_active_level /user%} {% is_active_level /staff%} {% is_active_level /cl%}">
          <a class="btn dropdown-toggle" data-toggle="dropdown" href="/dashboard/">
          <span class="glyphicon glyphicon-user"></span>
            <% if (sessionBean.getEmployeeID() > 0) { %>
              <jsp:getProperty name="employeeBean" property="givenName" /> <jsp:getProperty name="employeeBean" property="familyName" />
            <% } else { %>
              Admin
            <% } %>
          <span class="fa fa-caret-down"></span>
        </a>
          <ul class="dropdown-menu">
            <li><a href="/dashboard/"><i class="fa fa-user fa-fw"></i> Dashboard</a></li>
            <li><a href="/dashboard/settings/"><i class="fa fa-cogs fa-fw"></i> Settings</a></li>
            <li class="divider"></li>
            <li class=""><a href="/auth/signout/"> <i class="fa fa-ban fa-fw"></i> Sign out</a></li>
          </ul>
        </li>
        <% } else { %>
        <li><a href="/auth/signin/?{% redirect_url_query %}">Sign in</a></li>
        <% } %>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
