<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true" %>
<html>
<body>
<h1>Title : ${title}</h1>

<h1>Message : ${message}</h1>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<form:form method="GET" action="add" commandName="user">

    <table>
        <tr>
            <td>Username</td>
            <td><form:input path="username"/></td>
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
            <td colspan="2">
                <select name="roleCheck" id="roleCheck">
                    <option value="USER" selected="selected">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add User"/></td>
        </tr>
    </table>
</form:form>

<form:form method="GET" action="search">
    <table>
        <tr>
            <input type="text" id="snippet" name="snippet"/>
            <td colspan="2"><input type="submit" value="Search by username or phone"/></td>
        </tr>
    </table>
</form:form>

<h3>Users list:</h3>
<c:if test="${!empty userList}">
    <table class="data">
        <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.lastname}, ${user.firstname}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td><a href="delete/${user.username}">Delete</a></td>
                <td><a href="edit/${user.username}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Welcome : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>

</body>
</html>