<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">

    <form:form method="post" modelAttribute="getUser">
        <form:label path="*">Korisnicko ime:</form:label>
        <form:select path="username" items="${userList}" itemValue="username" itemLabel="username"/>

        <input type="submit" class="btn btn-success" value="Posalji" name="button"/>
        <br>
        <form:form method="post" modelAttribute="account">
            <form:label path="*">Naziv:</form:label>
            <form:input path="realName" type="text" class="form-control" required="required"/>
            <form:errors path="*" cssClass="text-warning"/>

            <form:label path="iban">Iban:</form:label>
            <form:input path="iban" type="text" class="form-control" required="required"/>
            <form:errors path="iban" cssClass="text-warning"/>
        </form:form>


    </form:form>

</div>

<%@include file="common/footer.jspf"%>