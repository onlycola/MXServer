<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單狀態</title>
</head>
<body bgcolor="#EBEBEB">
<h3>訂單狀態:</h3>
	<c:forEach items="${orders}" var="order" varStatus="loop">
        <table border="1">
        <tr style="background:#9D1523;color:#fff"><td>訂單編號</td><td><c:out value="${order.orderCode}"/></td></tr>
        <tr><td>日期</td><td><c:out value="${(order.dateStr)}"/></td></tr>
         <tr>  
            <td>取貨方式</td><c:choose>
                <c:when test="${order.pickup}">
                <td>分店自取</td>
                </c:when>
                <c:otherwise >
                <td>送貨</td>
                </c:otherwise>
                </c:choose>
                </tr>
            <tr> <td>訂單狀態</td><td><c:out value="${order.statusStr}"/></td></tr>
			<br>
                
            </c:forEach>
        </table>
        <br>
        <a href="javascript:history.back(-1)">重新輸入</a>
</body>
</html>