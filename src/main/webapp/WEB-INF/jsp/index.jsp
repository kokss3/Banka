<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div class="container">
    <table class="table-condensed" width="100%">
        <H2 title="Name">
        Racuni         </H2>
        <thead>
        <tr >
            <th>IBAN</th>
            <th>Sredstva</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody align="left" style="align-self: center">
        <c:forEach  items="${iban}" var="ibans" >
            <tr>
                <td width="50%">"${ibans.iban}"</td>
                <td width="50%">Marko</td>
                <td><a type="button" class="btn btn-success" href="">Update</a></td>
                <td><a type="button" class="btn btn-warning" href="">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>

<%@include file="common/footer.jspf"%>