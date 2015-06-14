<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
Вы зарегистрировались в системе.<br>
Добро пожаловать! <br><br>
Через 5 секунд Вы будете перенаправлены на главную страницу.
Если этого не происходит, или вы не хотите ждать, то вы можете перейти самостоятельно по  <a href='/'>ссылке</a>.
<script language = 'javascript'>
    setTimeout("document.location.href='/'", 5000);
</script>
<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>