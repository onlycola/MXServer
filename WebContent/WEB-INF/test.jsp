<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h4>List of all students:</h4>
        <table>
            <c:forEach items="${orders}" var="order" varStatus="loop">
                <tr><td><c:out value="${order.date}"/></td><td> <c:out value="${order.orderCode}"/></td></tr>
            </c:forEach>
        </table>
</body>
</html>