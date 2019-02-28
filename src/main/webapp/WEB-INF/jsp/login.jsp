<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

<div style="width: min-content" class="container">

    <h1>Login</h1>
    <form username='f' action="login" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input type="text" username="username"> </td>
            </tr>

            <tr>
                <td>Password:</td>
                <td><input type='password' username='password' /></td>
            </tr>
            <tr>
                <td><button class="btn btn-success" username="submit" type="submit" value="submit">Login</button></td>
            </tr>
        </table>
    </form>

</div>
<!-- /container -->
    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </body>
</html>