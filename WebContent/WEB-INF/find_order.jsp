<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單狀態</title>
</head>
<body>
<h3>訂單狀態:</h3>
        <table border="1">
        <tr>
		<th>日期</th> <th>訂單編號</th> <th>取貨方式</th> <th>訂單狀態</th> </tr>
            <c:forEach items="${orders}" var="order" varStatus="loop">
                <tr><td><c:out value="${(order.dateStr)}"/></td>
                <td><c:out value="${order.orderCode}"/></td>
                <c:choose>
                <c:when test="${order.pickup}">
                <td>分店自取</td>
                </c:when>
                <c:otherwise >
                <td>送貨</td>
                </c:otherwise>
                </c:choose>
                <td><c:out value="${order.statusStr}"/></td></tr>
                
            </c:forEach>
        </table>
        <br>
        <a href="javascript:history.back(-1)">重新輸入</a>
</body>
</html>