<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<jsp:include page="/WEB-INF/jsp/masterpage/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/masterpage/navigation_menu.jsp"/>


<main>
    <div id="main">
        <jstl:if test="${newsList!=null && newsList.size()>0}">

            <div id="newsBlock">
                <div id="newsBlockHeader">НОВОСТИ:</div>
                <jstl:forEach items="${newsList}" var="newsItem">
                    <div class="newsItem">
                        <div class="newsItemTitle">${newsItem.name}</div>
                        <div class="newsItemText">${newsItem.text}</div>
                    </div>
                </jstl:forEach>
            </div>
        </jstl:if>

    </div>
</main>
<jsp:include page="/WEB-INF/jsp/masterpage/footer.jsp"/>