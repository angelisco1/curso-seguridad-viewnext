<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  
  <title>Document</title>
</head>
<body>
  
  <h1>Mis informes</h1>
  
  <!--
  <a href="login.html">Logout</a>
  -->
  <a href="nuevo-informe">Nuevo informe</a>
  <a href="logout">Logout</a>
  
  <hr>
  
  
  <form action="buscar-informes" method="GET">
  	<div>
  		<label for="busqueda">Buscar por:</label>
  		<input type="text" id="busqueda" name="busqueda" />
  		<p>&ltscript>alert('Te he hackeado, pagame 1 bitcoin a la cuenta 0x128439fjsfal3r893fj si quieres recuperar tu cuenta')&lt/script></p>
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