<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="container">
    <table class="table-striped" width="75%" align="center">
        <thead>
            <tr style="width: 200px">

                <td >IBAN</td>
                <td >Sredstva</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="names">
            <tr>

                <td>${names.iban}</td>
                <td>${names.funds}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>

<%@include file="common/footer.jspf"%>