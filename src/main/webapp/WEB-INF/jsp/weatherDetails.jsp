
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>View Items</title>
    <link href="/static/style.css" rel="stylesheet">
</head>
<body>


<%

%>

</br></br></br>
<table id="weather">
    <thead>
    <tr>
        <th>Date</th>
        <th>DailyTemp</th>
        <th>DailyTempMin</th>
        <th>DailyTempMax</th>
        <th>Humidity</th>
        <th>Pressure</th>
        <th>WeatherDescription</th>
        <th>Speed</th>
        <th>Suggestions</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.date}</td>
            <td>${item.temp}</td>
            <td>${item.temp_min}</td>
            <td>${item.temp_max}</td>
            <td>${item.humidity}</td>
            <td>${item.pressure}</td>
            <td>${item.weatherDescription}</td>
            <td>${item.speed}</td>
            <td>${item.suggestions}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>