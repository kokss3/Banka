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
                <div class="input-group">
                    <div class="input-group-append">
                        <button class="btn btn-success dropdown-toggle"
                            type="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">Role</button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">ADMINISTRATOR</a>
                            <a class="dropdown-item" href="#">USER</a>
                        </div>
                    </div>
                </div>
            <tr>
                <td><button class="btn btn-success"  username="submit" type="submit" value="submit">Register</button></td>
            </tr>
        </table>
    </form>

</div>
<!-- /container -->
    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </body>
</html>