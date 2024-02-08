<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  
  <h1>Informes encontrados para la busqueda: ${busqueda}</h1>
  
  <!--
  <a href="login.html">Logout</a>
  -->
  <a href="nuevo-informe">Nuevo informe</a>
  <a href="informes">Informes</a>
  <a href="logout">Logout</a>
  
  <hr>
  
  
  <form action="buscar-informes" method="GET">
  	<div>
  		<label for="busqueda">Buscar por:</label>
  		<input type="text" id="busqueda" name="busqueda" />
  	</div>
  	<button type="submit">Buscar</button>
  </form>
  
  
  <c:choose>
  	<c:when test="${informes.size() > 0}">
  		<c:forEach items="${informes}" var="informe">
  			<div>
  				<h2>${informe.titulo}</h2>
  				<p>${informe.descripcion}</p>
  				<a href="informe?id=${informe.id}">Ver informe completo</a>
  			</div>
  		</c:forEach>
  	</c:when>
  	<c:otherwise>
  		<p>No tienes ningún informe</p>
  	</c:otherwise>
  </c:choose>
  
</body>
</html>