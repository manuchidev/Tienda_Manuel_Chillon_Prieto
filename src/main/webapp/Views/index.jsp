<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<jsp:include page="<%= Rutas.MAIN%>" />	
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	
<%-- 		<script src="<c:url value="<%= Rutas.JS %>scripts.js" />"></script> --%>

	</body>
	
</html>
