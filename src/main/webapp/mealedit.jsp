<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 3/29/2017
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal edit page.</title>
</head>
<body>
<h3>Edit meal:</h3>
<form name="mealForm" method="POST" action="?action=update&id=${param.id}" >
    <label>
        Date:
        <input type="datetime-local" name="date" value="${readMeal.getDateTime()}"/>
    </label> <br/>
    <label>
        Description:
        <input type="text" name="description" value="${readMeal.getDescription()}"/>
    </label> <br/>
    <label>
        Calories:
        <input type="number" name="calories" value="${readMeal.getCalories()}"/>
    </label> <br/>
    <input type="submit" name="button" value="Update" />
</form>
</body>
</html>
