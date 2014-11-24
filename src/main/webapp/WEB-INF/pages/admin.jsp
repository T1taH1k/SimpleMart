<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true" %>
<html>
<body>
<h3>Title : ${title}</h3>

<h3>Message : ${message}</h3>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<form:form method="GET" action="add" commandName="product">

    <table>
        <tr>
            <td>Name</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Add Product"/></td>
        </tr>
    </table>
</form:form>

<form:form method="GET" action="search">
    <table>
        <tr>
            <input type="text" id="snippet" name="snippet"/>
            <td colspan="2"><input type="submit" value="Search by name"/></td>
        </tr>
    </table>
</form:form>

<h3>Product list:</h3>
<c:if test="${!empty productList}">
    <table class="data">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.good_id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td><a href="delete/${product.good_id}">Delete</a></td>
                <td><a href="edit/${product.name}">Edit</a></td>
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