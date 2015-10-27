<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

{% unless page.session == false %}<%@ page session="true" %>
<jsp:useBean id="sessionBean" scope="session" class="cf.thehivedsu.olms.bean.SessionBean"/>{% endunless %}
<jsp:useBean id="requestBean" scope="request" class="cf.thehivedsu.olms.bean.RequestBean"/>
<%  if (sessionBean.getEmployeeID() > 0) {
      request.setAttribute("employeeBean", Employee.employeeWithId(sessionBean.getEmployeeID()));
 %>
  <jsp:useBean id="employeeBean" scope="request" class="cf.thehivedsu.olms.bean.EmployeeBean"/>
<%  } %>

{% for bean in page.beans %}
<jsp:useBean id="{{ bean[0] }}" scope="{{ bean[1].scope }}" class="{{ bean[1].class }}"/>
{% endfor %}
