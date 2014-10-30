<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true" %>
<html>
<head>
    <title>Update page</title>
</head>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<h3>Update user</h3>


<form:form method="GET" action="update" commandName="user">
    <table>
        <tr>
            <td>Username</td>
            <td><form:input path="username" readonly="true"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td>Lastname</td>
            <td><form:input path="lastname"/></td>
        </tr>
        <tr>
            <td>Firstname</td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Update User"/></td>
        </tr>
    </table>
</form:form>


</body>
</html>
