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
<div style="padding:10px;"><spring:message code="pageTitles_/NewsEditor/newsList" text=""/></div>
<table border=1px bordercolor="lightgray" cellspacing=0 cellpadding=0   style="margin: 10px;" >
  <jstl:forEach items="${newsListBean.newsList}" var="news">
    <tr>
      <td class="formFieldValue">
          ${news.name}
      </td>
      <td class="formFieldIcon">
        <a class="AnchorList" href="/NewsEditor/edit/${news.id}" ><spring:message code="edit"></spring:message></a>
      </td>
      <td class="formFieldIcon">
        <a class="AnchorList" href="/NewsEditor/delete/${news.id}"  onclick="return confirm('<spring:message code="DeleteConfirm"/>');"><spring:message code="delete"></spring:message></a>
      </td>
    </tr>
  </jstl:forEach>
  <tr>
    <td class="formFieldSubmit" colspan="3" align="right">
      <form:form action="/NewsEditor/edit/0" method="post">
      <input type="submit" value="<spring:message code="add"/>">
      </form:form>
</table>

<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>