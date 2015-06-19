<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib  uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<form:form action="/auth/login" method="post" id="LoginForm" >
    <jstl:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </jstl:if>
    <jstl:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </jstl:if>
    <table>
        <tr>
            <td align="right"><spring:message code="login" /></td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="password" /></td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td align="right"><spring:message code="remember" /></td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="<spring:message code="signIn" />"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="<spring:message code="cancel" />"/>
            </td>
        </tr>
    </table>
</form:form>
<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>