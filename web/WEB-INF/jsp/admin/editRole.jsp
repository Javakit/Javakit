<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/admin/admin_navigation_menu.jsp"/>

<form:form method="post" action="/admin/Role/save" commandName="selectedRoleBean">
  <jstl:if test="${id>0}">
    <form:hidden path="role.id"/>
  </jstl:if>
  <table>
    <tr>
      <td class="formFieldName">
        <form:label path="role.name"><spring:message code="field_RoleName"/></form:label>
      </td>
      <td class="formFieldValue">
        <form:input path="role.name" type="text" class="ElemWidth"/>
      </td>
      <td class="Form_td3">
        <form:errors path="role.name"  />
      </td>
    </tr>
    <tr><td >&nbsp;</td></tr>
    <tr>
      <td class="formFieldName">
        <form:label path="permissionIdsList"><spring:message code="field_PermissionList"/>*</form:label>
      </td>
      <td class="formFieldValue">
        <form:checkboxes path="permissionIdsList" items="${fullPermissionList}" itemValue="id" itemLabel="name"
                         delimiter="<br>"/>
      </td>
      <td class="Form_td3"></td>
    </tr>
    <tr><td >&nbsp;</td></tr>
    <tr>
      <td></td>
      <td>
        <input type="submit" name="save" value="<spring:message code="save"/>" onclick="
                if (document.getElementById('name').value.length == 0 )
                {
                alert('<spring:message code="requiredFieldsMessage"/>');
                return false;
                }
                "/>

        <input type="submit" name="cancel" value="<spring:message code="cancel"/>"/>
      </td>
      <td class="Form_td3"></td>
    </tr>
  </table>
</form:form>
<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>