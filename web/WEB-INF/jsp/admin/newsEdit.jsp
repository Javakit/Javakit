<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/admin/admin_navigation_menu.jsp"/>

<form:form method="post" action="/NewsEditor/save" commandName="entity">
  <jstl:if test="${id>0}">
    <form:hidden path="id"/>
  </jstl:if>
  <table>
    <tr>
      <td class="formFieldName">
        <form:label path="name"><spring:message code="field_NewsName"/>*</form:label>
      <td class="formFieldValue">
          <form:input path="name" type="text" class="ElemWidth"/>
    <tr>
    <tr>
      <td class="formFieldName">
          <form:label path="text"><spring:message code="field_Newstext"/></form:label>
      <td class="formFieldValue">
          <form:textarea path="text" type="text" class="ElemWidth"/>
    <tr>
      <td class="formFieldSubmit" colspan="2" align=right>
        <input type="submit" name="save" value="<spring:message code="save"/>" onclick="
                if (document.getElementById('name').value.length == 0 )
                {
                alert('<spring:message code="requiredFieldsMessage"/>');
                return false;
                }
                "/>
        <input type="submit" name="cancel" value="<spring:message code="cancel"/>"/>
  </table>
</form:form>

<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>