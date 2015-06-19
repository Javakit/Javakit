<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<img src="/images/duke.gif" alt="duke">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<jstl:if test="${userDetails!=null}">
    ${userDetails.getUsername()}
</jstl:if>

<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>