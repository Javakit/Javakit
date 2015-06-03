<!DOCTYPE html>
<html>
<head>
  <%@ page pageEncoding="UTF-8" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>i18n-title</title>
</head>
<body>
<input type="button" onclick="alert('${DeleteConfirmMessage}')"   value='<spring:message code="delete"/>' />
<br><br>
<a href='?lang=ru'>rus</a> | <a href='?lang=en'>eng</a>
</body>
</html>