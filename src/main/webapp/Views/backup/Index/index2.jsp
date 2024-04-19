<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas,java.util.*,curso.java.tienda.model.VO.Producto.ProductoVO,curso.java.tienda.service.Producto.ProductoService"
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
		
		<main>
		
<%-- 			<jsp:include page="<%= Rutas.LISTAR_PROD_JSP%>"> --%>
			
			<c:forEach var="producto" items="${requestScope.productos}">
			<div class="panel-heading textoCard nombreCard">${producto.nombre}</div>
			</c:forEach>
<%-- 				<jsp:param name="productos" value="${requestScope.productos}" /> --%>
			
<%-- 			</jsp:include> --%>
		
		</main>
		
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
</html>
