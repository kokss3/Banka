<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    <table class="table-striped" width="75%" align="center">
        <thead>
        <tr style="width: 200px">

            <td>Ime</td>
            <td>IBAN</td>
            <td>Racuni</td>
            <td>Obrisi</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${c_user}" var="user">
            <tr>
                <tr></tr>
                <tr>
                    <c:forEach  items="${user.accounts}" var ="acc">
                <tr>
                    <td>${acc.realName}</td>
                    <td>${acc.iban}</td>
                    <td>${acc.funds}</td>
                    <td><a href="/transfer"/><button class="btn btn-success" type="submit" title="Posalji">Obrisi</button></td>
                </tr>
                    </c:forEach>
                </tr>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>


<%@include file="common/footer.jspf"%>