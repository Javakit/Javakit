<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>


<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div id="navigation" style="width: 100%; height: 24px; background-color: white;border-bottom: 2px solid gray;">
  <div style="float:left;text-transform:uppercase;padding:5px;">
    <a href="/">
      <spring:message code="pageTitles_/"/>
    </a>|
  </div>
  <sec:authorize access="hasAnyRole('ROLE_EditCompany','ROLE_EditUser', 'ROLE_EditUser', 'ROLE_EditSelfUser', 'ROLE_EditRole', 'ROLE_EditNews')">
    <div style="float:left;text-transform:uppercase;padding:5px;" >
      <a href="/admin">
        <spring:message code="pageTitles_/admin"/>
      </a>|
    </div>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_EditNews')">
    <div style="float:left;text-transform:uppercase;padding:5px;">
      <a href="/NewsEditor/newsList">
        <spring:message code="pageTitles_/NewsEditor/newsList"/>
      </a>|
    </div>
  </sec:authorize>
  <sec:authorize  access="hasRole('ROLE_EditCompany')" >
    <div style="float:left;text-transform:uppercase;padding:5px;">
      <a href="/admin/Company/entityList">
        <spring:message code="pageTitles_/admin/Company/entityList"/>
      </a>|
    </div>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_EditUser')">
    <div style="float:left;text-transform:uppercase;padding:5px;">
      <a href="/admin/User/entityList">
        <spring:message code="pageTitles_/admin/User/entityList"/>
      </a>|
    </div>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_EditRole')">
    <div style="float:left;text-transform:uppercase;padding:5px;">
      <a href="/admin/Role/entityList">
        <spring:message code="pageTitles_/admin/Role/entityList"/>
      </a>|
    </div>
  </sec:authorize>
  <div style="clear: both"/>
</div>