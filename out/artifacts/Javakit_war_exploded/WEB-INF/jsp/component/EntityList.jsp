<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/admin/admin_navigation_menu.jsp"/>
<style type="text/css">
  TD{
    padding: 5px 10px;
  }
</style>
<div style="padding:10px;"><spring:message code="pageTitles_/admin/${entityListBean.entityName}/entityList" text=""/></div>
<table border=1px bordercolor="lightgray" cellspacing=0 cellpadding=0   style="margin: 10px;" >
  <jstl:forEach items="${entityListBean.entityList}" var="selectedEntity">
    <tr>
      <td class="formFieldValue">
          ${selectedEntity.name}
      </td>
      <td class="formFieldIcon">
        <a class="AnchorList" href="/admin/${entityListBean.entityName}/edit/${selectedEntity.id}" ><spring:message code="edit"></spring:message></a>
      </td>
      <td class="formFieldIcon">
        <a class="AnchorList" href="/admin/${entityListBean.entityName}/delete/${selectedEntity.id}"  onclick="return confirm('<spring:message code="DeleteConfirm"/>');"><spring:message code="delete"></spring:message></a>
      </td>
    </tr>
  </jstl:forEach>
  <tr>
    <td class="formFieldSubmit" colspan="3" align="right">
      <form:form action="/admin/${entityListBean.entityName}/edit/0" method="post">
      <input type="submit" value="<spring:message code="add"/>">
      </form:form>
</table>

<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>