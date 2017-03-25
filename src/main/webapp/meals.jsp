<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<p>Meals page.</p>
<table>
    <tr>
        <td>Date</td>
        <td>Description</td>
        <td>Calories</td>
    </tr>
    <c:forEach var="meal" items="${mealsList}">
        <tr style="color: ${meal.isExceed() ? '#ff0000' : '#006600'}">
            <td>
                <javatime:format value="${meal.getDateTime()}" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime"/>
                <p>${parsedDateTime}</p>
            </td>
            <td>
                <p>${meal.getDescription()}</p>
            </td>
            <td>
                <p>${meal.getCalories()}</p>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
