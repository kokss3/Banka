<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>

<div style="width: min-content" class="container">

    <h1>Register</h1>
    <form action="register" method='POST'>
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
                <td><button class="btn btn-success" username="submit" type="submit" value="submit">Register</button></td>
            </tr>
        </table>
    </form>

<%@include file="common/footer.jspf"%>