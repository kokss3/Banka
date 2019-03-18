<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>


<div style="width: min-content" class="container">

    <h1>Dodaj korisnika</h1>
    <form action="add_user" method='POST'>
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" id="username"> </td>
            </tr>

            <tr>
                <td>Password:</td>
                <td><input type='password' id="password" name='password' /></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td><input type="role" id="role" name="roles" required/> </td>
            </tr>
            <td><button class="btn btn-success" type="submit" value="submit">Dodaj</button></td>
            </tr>
        </table>
    </form>

</div>
<%--

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
--%>


<%@include file="common/footer.jspf"%>