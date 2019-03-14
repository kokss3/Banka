<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    <table class="table-striped" width="75%" align="center">
        <thead>
        <div class="container">
            <div class="btn btn-success" id="user" title="Dodaj korisnika">Dodaj korisnika</div>
            <div class="btn btn-danger" title="Obrisi korisnika">Obrisi korisnika</div>
        </div>
            <tr style="width: 200px">
            <td>Ime</td>
            <td>IBAN</td>
            <td>Sredstva</td>
            <td>Racuni</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${c_user}" var="user">
            <tr>
                <tr></tr>
                <tr>
                    <c:forEach items="${user.accounts}" var ="acc">
                <tr>
                    <div class="input-group-text">
                    <td>${acc.realName}</td>
                    <td>${acc.iban}</td>
                    <td>${acc.funds}</td>
                    <td>
                        <div class="btn btn-success" id="account" title="Dodaj racun">Dodaj racun</div>
                        <div class="btn btn-danger" title="Obrisi racun">Obrisi racun</div>
                    </td>
                    </div>
                </tr>
                    </c:forEach>
                </tr>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
<%@include file="common/footer.jspf"%>