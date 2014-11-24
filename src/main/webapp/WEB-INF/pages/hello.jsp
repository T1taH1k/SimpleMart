<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h3>Title : ${title}</h3>

<h3>Message : ${message}</h3>
<form>
    <h3>Product list:</h3>
    <c:if test="${!empty productList}">
        <table class="data">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td>${product.good_id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</form>

<c:if test="${pageContext.request.userPrincipal.name == null}">
    <h2>
        <a href="<c:url value="login"/>">Login Page</a>
    </h2>
</c:if>

<sec:authorize access="hasRole('ROLE_USER')">
    <!-- For login user -->
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <a href="<c:url value="admin"/>">Edit page for Administrator only</a>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            User : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Logout</a>
        </h2>
    </c:if>


</sec:authorize>
</body>
</html>