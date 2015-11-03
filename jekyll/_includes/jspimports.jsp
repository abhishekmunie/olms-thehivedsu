<%@ page import="java.net.URL"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%@ page import="cf.thehivedsu.olms.Utilities"%>
<%@ page import="cf.thehivedsu.olms.employee.Employee"%>
<%@ page import="cf.thehivedsu.olms.manager.Manager"%>
{% for property in page.jsp_imports %}
<%@ {{ property[1] }} import="{{ property[0] }}"%>
{% endfor %}
