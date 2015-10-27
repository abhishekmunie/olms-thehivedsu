<!--[if lt IE 8]>
  <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<% if (session.getAttribute("no-academic-project-warning") == null) { %>
<div id="dev-warnng" class="alert alert-warning alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <a href="/disable-academic-project-warning?{% redirect_url_query %}" class="alert-link pull-right">Dont show again</a>
  <strong>Warning!</strong>
  This is an academic project, not the official company page.
  It's open source and available under {{ site.licence }} Licence at
  <a href="{{ site.github_repository }}" target="_blank" class="alert-link">GitHub</a>
</div>
<% } %>
