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
        </tr>
    </c:forEach>
</table>
<br/>
<form name="mealForm" method="POST" action="index.html" >
    <label>
        ID:
        <input type="number" name="id"/>
    </label> <br/>
    <label>
        Date:
        <input type="datetime-local" name="date"/>
    </label> <br/>
    <label>
        Description:
        <input type="text" name="description"/>
    </label> <br/>
    <label>
        Calories:
        <input type="number" name="calories"/>
    </label> <br/>
    <input type="submit" name="button" value="Create" />
    <input type="submit" name="button" value="Read" />
    <input type="submit" name="button" value="Update" />
    <input type="submit" name="button" value="Delete" />
</form>
</body>
</html>
