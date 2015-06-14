<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form id="userFormBean" commandName="userFormBean" action='${userFormAction}'  method="post">
    <table >
        <tr>
            <td class="Form_td1"  >
                <form:label path="user.name"><spring:message code="userFormBean_name" /><spring:message code="userFormBean_name_note" /></form:label>
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
                <form:label path="user.login"><spring:message code="userFormBean_login" /><spring:message code="userFormBean_login_note" /></form:label>
            </td>
            <td class="Form_td2">
                <form:input path="user.login" type="text" class="Form_input" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.login" />
            </td>
        </tr>
        <tr>
            <td class="Form_td1">
                <form:label path="user.password" ><spring:message code="userFormBean_password" /><spring:message code="userFormBean_password_note" /></form:label>
            </td>
            <td class="Form_td2">
                <form:password path="user.password" class="Form_input form-control" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.password" />
            </td>
        </tr>
        <tr>
            <td class="Form_td1">
                <form:label path="repeatedPassword"><spring:message code="userFormBean_repeatedPassword" /><spring:message code="userFormBean_repeatedPassword_note" /></form:label>
            </td>
            <td class="Form_td2">
                <form:password path="repeatedPassword" class="Form_input" maxlength="12"/>
            </td>
            <td class="Form_td3">
                <form:errors path="repeatedPassword"   />
            </td>
        </tr>
        <tr><td >&nbsp;</td></tr>
        <tr>
            <td class="Form_td1">
                <form:label path="user.email"><spring:message code="userFormBean_email" /><spring:message code="userFormBean_email_note" /></form:label>
            </td>
            <td class="Form_td2">
                <form:input path="user.email" type="text" class="Form_input" maxlength="255"/>
            </td>
            <td class="Form_td3">
                <form:errors path="user.email"  />
            </td>
        </tr>
        <tr><td >&nbsp;</td></tr>
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
<script src="/jquery/validation/jquery.validate.js"></script>
<script>
    $("#userFormBean").validate(
            {
                errorPlacement: function(error, element) {
                    error.appendTo( element.parent("td").next("td") );
                },
                rules:{
                    'user.name':{
                        required: true,
                        maxlength:255
                    },
                    'user.login':{
                        required: true,
                        maxlength: 255,
                        remote: {//http://www.wenda.io/questions/3061140/jquery-validate-plugin-remote-validation-with-custom-error-message.html
                            url: "/auth/isLoginFree",
                            type: "post",
                            dataType:"json",//default
                            data: {
                                login4test: function () {
                                    return $('#user\\.login').val();
                                }
                            }
                        }
                    },
                    'user.password':{
                        required: true,
                        rangelength: [5, 255]
                    },
                    'repeatedPassword':{
                        equalTo: "#user\\.password"
                    },
                    'user.email':{
                        required: true,
                        maxlength: 255,
                        email: true
                    }
                },
                messages:{
                    'user.name': {
                        required:'<spring:message code="Size.userFormBean.user.name" />',
                        maxlength:'<spring:message code="Size.userFormBean.user.name" />'
                    },
                    'user.login':{
                        required:'<spring:message code="Size.userFormBean.user.login" />',
                        maxlength: '<spring:message code="Size.userFormBean.user.login" />',
                        remote:'<spring:message code="UnUniq.userFormBean.user.login" />'
                    },
                    'user.email':{
                        required:'<spring:message code="Pattern.userFormBean.user.email" />',
                        maxlength: '<spring:message code="Size.userFormBean.user.email" />',
                        email:'<spring:message code="Pattern.userFormBean.user.email" />'
                    },
                    'user.password': {
                        required:'<spring:message code="Size.userFormBean.user.password" />',
                        rangelength:'<spring:message code="Size.userFormBean.user.password" />',
                    },
                    'repeatedPassword':'<spring:message code="Size.userFormBean.repeatedPassword" />'
                }
            }
    );
</script>
