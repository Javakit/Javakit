<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form id="userFormBean" commandName="userFormBean" action='${userFormAction}'  method="post">
    <table >
        <jstl:if test="${id>0}">
            <form:hidden path="user.id"/>
        </jstl:if>
        <tr>
            <td class="Form_td1"  >
                <form:label path="user.name"><spring:message code="userFormBean_name" />*</form:label>
            </td>
            <td class="Form_td2"  >
                <form:input path="user.name" type="text" class="Form_input" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.name"  />
            </td>
        </tr>
        <tr><td >&nbsp;</td></tr>
        <tr>
            <td class="Form_td1">
                <form:label path="user.login"><spring:message code="userFormBean_login" />*</form:label>
            </td>
            <td class="Form_td2">
                <form:input path="user.login" type="text" class="Form_input" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.login" />
            </td>
        </tr>
        <tr><td >&nbsp;</td></tr>
        <jstl:if test="${hidePassword==null || hidePassword!=true}">
            <tr>
                <td class="Form_td1">
                    <form:label path="user.password"><spring:message code="userFormBean_password"/>*</form:label>
                </td>
                <td class="Form_td2">
                    <form:password path="user.password" class="Form_input form-control" maxlength="255"/>
                </td>
                <td class="Form_td3">
                    <form:errors path="user.password"/>
                </td>
            </tr>
            <tr>
                <td class="Form_td1">
                    <form:label path="repeatedPassword"><spring:message
                            code="userFormBean_repeatedPassword"/></form:label>
                </td>
                <td class="Form_td2">
                    <form:password path="repeatedPassword" class="Form_input" maxlength="12"/>
                </td>
                <td class="Form_td3">
                    <form:errors path="repeatedPassword"/>
                </td>
            </tr>
        </jstl:if>
        <tr><td >&nbsp;</td></tr>
        <tr>
            <td class="Form_td1">
                <form:label path="user.email"><spring:message code="userFormBean_email" />*</form:label>
            </td>
            <td class="Form_td2">
                <form:input path="user.email" type="text" class="Form_input" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.email"  />
            </td>
        </tr>
        <tr><td >&nbsp;</td></tr>
        <jstl:if test="${fullRoleList!=null}">
            <tr>
                <td> </td>
            </tr>
            <tr>
                <td class="Form_td1">
                    <form:label path="roleIdsList"><spring:message code="userFormBean_roleIdsList"/></form:label>
                </td>
                <td class="Form_td2">
                    <form:checkboxes path="roleIdsList" items="${fullRoleList}" itemValue="id" itemLabel="name"
                                     delimiter="<br>"/>
                </td>
                <td class="Form_td3">
                </td>
            </tr>
            <tr>
                <td> </td>
            </tr>
        </jstl:if>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="action" value="<spring:message code="save"/>"  />
                &nbsp;
                <input type="submit" name="cancel" value="<spring:message code="cancel"/>" />
            </td>
        </tr>
    </table>
</form:form>

