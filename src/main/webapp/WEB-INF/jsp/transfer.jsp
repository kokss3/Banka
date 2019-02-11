<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="container">
    <form:form method="post" modelAttribute="acc-holder">
        <fieldset class="form-group">
            <form:label path="name">Ime primatelja:</form:label>
            <form:input path="name" type="text" class="form-control" required="required"/>
            <form:errors path="name" cssClass="text-warning"/>
        </fieldset>
            <fieldset class="form-group">
                <form:label path="iban">Iban primatelja:</form:label>
                <form:input path="iban" type="text" class="form-control" required="required"/>
                <form:errors path="iban" cssClass="text-warning"/>
            </fieldset>
        <fieldset class="form-group">
            <form:label path="funds">Iznos:</form:label>
            <form:input path="funds" type="integer" class="form-control" required="required"/>
            <form:errors path="funds" cssClass="text-warning"/>
        </fieldset>

        <button class="btn btn-success" type="submit">Posalji</button>

    </form:form>
<div>

<%@include file="common/footer.jspf"%>