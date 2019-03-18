<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">

    <form:form method="post" modelAttribute="getUser">
        <form:label path="*">Korisnik:</form:label>
        <form:select path="username">
            <form:option value="null" >-Odaberi-</form:option>
            <form:options items="${userList}" itemValue="username" itemLabel="username"/>
        </form:select>

        <br>

        <form:form method="post" modelAttribute="getAcc">
            <form:label path="*">Iban:</form:label>
            <form:select path="iban">
                <form:option value="null">-Odaberi-</form:option>
                <form:options items="${accounts}" itemValue="iban" itemLabel="iban"/>
            </form:select>
            <input type="submit" class="btn btn-success" value="Obrisi" name="button"/>

        </form:form>
        <br>
    </form:form>

</div>

<%@include file="common/footer.jspf"%>