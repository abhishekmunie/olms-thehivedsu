<!-- FOOTER -->
<footer id="site-footer" class="footer hidden-print">
  <div class="container text-center">
    <a href="https://apple.com/mac" class="text-center pull-left">
      <i class="fa fa-apple fa-2x"></i><br />
      Created on a Mac
    </a>
    <p id="company" class="pull-right">
      2015 &middot; {{site.company}}<br />
      <a href="/privacy/">Privacy</a> &middot;
      <a href="/terms/">Terms</a>
    </p>
    <a id="heroku-powered" href="https://www.heroku.com" title="Heroku" class="" target="_blank" >
      <img src="/images/heroku-logo.png" /><br />
      Powered by Heroku (pronounced her-OH-koo)
    </a>
  </div>
</footer>

{% if config.google.analytics %}
<script>
  (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
  function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
  e=o.createElement(i);r=o.getElementsByTagName(i)[0];
  e.src='https://www.google-analytics.com/analytics.js';
  r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
  ga('create','{{site.config.google.analytics.id}}','auto');ga('send','pageview');
</script>
{% endif %}
