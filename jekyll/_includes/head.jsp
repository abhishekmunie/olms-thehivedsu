 {% for css in site.imports.css %}
<link rel="stylesheet" href="{% if css[1].url %}{{ css[1].url }}{% else %}{{ css[1] }}{% endif %}" {% if css[1].media %} media="{{ css[1].media }}" {% endif %}/>
{% endfor %} {% if site.imports.modernizr %}
<script src="{{ site.imports.modernizr }}" type="text/javascript"></script>
{% endif %}
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="{{ "/bower_components/jquery/dist/jquery.min.js" | prepend: site.baseurl }}"><\/script>')</script> -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
  importScript = function(src, defer, id, callback) {
    var sp = document.createElement('script');
    sp.type = "text/javascript";
    sp.src = src;
    sp.async = true;
    id && (sp.id = id);
    defer && (sp.defer = "defer");
    callback && (sp.onload = sp.onreadystatechange = function() {
      var rs = this.readyState;
      if(rs && rs != 'complete' && rs != 'loaded') return;
      try{ callback(); } catch(e) {console.error(e)}
    });
    var s=document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(sp,s);
  };

  {% for js in site.imports.js %}
  importScript('{% if js[1].url %}{{ js[1].url }}{% else %}{{ js[1] }}{% endif %}',
    {% if js[1].defer %}{{ js[1].defer }}{% else %}false{% endif %},
   '{% if js[1].id %}{{ js[1].id }}{% else %}js-{{ js[0] }}{% endif %}',
    {% if js[1].callback %}{{ js[1].callback }}{% else %}null{% endif %});
  {% endfor %}
</script>
