<%@ page import="java.time.LocalDate" %>
<%@ page import="com.app.weather.weatherPrediction.dto.WeatherAverageDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>View Items</title>
    <link href="/static/style.css" rel="stylesheet">
</head>
<body>
<div>
<form:form method = "GET" action = "/getWeatherDetails">
    <table>
        <tr>
            <td><form:label path = "city">City</form:label></td>
            <td><form:input path = "city" /></td>
        </tr>
        <tr>
            <td>
                <input type = "submit" class="button" value = "Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</div>