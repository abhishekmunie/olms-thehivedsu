---
layout: auth
title: Employee's Authorization Required
---

<div class="jumbotron">
  <h1>Employee Portal <small>Employee's Authorization Required</small></h1>
  <p class="lead">You must be logged in as Employee to access this area.</p>
  <p><a class="btn btn-lg btn-success" href="/auth/signout?{% redirect_url_query %}" role="button">Sign in</a></p>
</div>
