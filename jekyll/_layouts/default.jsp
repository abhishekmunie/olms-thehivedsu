{% include jspdefaults.jsp %}
{% include jspimports.jsp %}
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
{% include title.jsp %}
<meta name="description" content="{% if page.excerpt %}{{ page.excerpt | strip_html | strip_newlines | truncate: 160 }}{% else %}{{ site.description }}{% endif %}">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="canonical" href="{{ page.url | replace:'index.html','' | prepend: site.baseurl | prepend: site.url }}">
<link rel="apple-touch-icon" href="apple-touch-icon.png">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

{% include head.jsp %}

</head>
<body>
  {% assign levels = page.url | split: '/' %} {% include header.jsp %}

  <div class="page-content">
    <div class="wrapper">{{ content }}</div>
  </div>

  {% include footer.jsp %}
</body>
</html>
