<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<head>
    <jsp:include page="/WEB-INF/jsp/masterpage/headerLinks.jsp"/>
    <title><spring:message code="pageTitles_${requestScope['javax.servlet.forward.servlet_path']}"  text=""/></title>
</head>
<body>
<jstl:if test="${message!=null}">
<div id="message" title="<spring:message code="message" text=""/>">
    ${message}
</div>
    <script>$(function() {
        $( "#message" ).dialog();
    });</script>
</jstl:if>

<div style="width: 100%; height: 40px; background-color: white;border-bottom: 2px solid gray;"></div>



