{% assign levels = page.url | split: '/' %}
{% assign breadcrumb_href = '/' %}
{% unless page.url == '/index.jsp' %}
<ol class="breadcrumb{% unless levels[3] %} invisible{% endunless %}">
  <li></li>
  {% for level in levels %} {% unless level == levels.first or level == levels.last %}
  {% assign breadcrumb_href = breadcrumb_href | append: level | append: '/' %}
  <li><a href="{{breadcrumb_href}}">{{ level | removehiphen | titlecase }}</a></li>
  {% endunless %} {% endfor %}
</ol>
{% endunless %}
