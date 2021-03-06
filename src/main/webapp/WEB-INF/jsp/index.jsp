<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    <table class="table-striped" width="75%" align="center">
        <thead>
        <tr style="width: 200px">
            <td>IBAN</td>
            <td>Sredstva</td>
            <td>Posalji</td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${users}" var="names">
            <tr>

                <td>${names.iban}</td>
                <td>${names.funds}</td>
                <td>
                    <form action="/transfer" method="GET">
                        <button class="btn btn-success" name="ibans" value="${names.user_id}"
                                type="submit" onclick="location.href='/transfer'">Posalji</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>

<%@include file="common/footer.jspf"%>