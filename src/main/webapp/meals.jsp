<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3>Meals page.</h3>
<table border="1" cellpadding="7">
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${mealsList}">
        <tr style="color: ${meal.isExceed() ? '#ff0000' : '#006600'}">
            <td>
                <p>${meal.getId()}</p>
            </td>
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
            <td>
                <a href="?action=update&id=${meal.getId()}">Update</a>
            </td>
            <td>
                <a href="?action=delete&id=${meal.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="?action=create">ADD</a>
</body>
</html>
