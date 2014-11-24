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


<form:form method="GET" action="update" commandName="product">
    <table>
        <tr>
            <td>Id</td>
            <td><form:input path="good_id" readonly="true"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Update Product"/></td>
        </tr>
    </table>
</form:form>


</body>
</html>
