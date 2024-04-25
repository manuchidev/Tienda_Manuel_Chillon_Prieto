<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas"
%>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
		
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	
<%-- 		<script src="<c:url value="<%= Rutas.JS %>scripts.js" />"></script> --%>

	</body>
	
</html>
