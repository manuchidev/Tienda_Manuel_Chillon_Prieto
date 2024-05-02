<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO"
%>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<% UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario"); %>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<% if (usuario == null || usuario.esCliente()){%>
			<jsp:include page="<%= Rutas.NAV%>" />
				
		<% } else if (usuario.esEmpleado()) {%>
			<jsp:include page="<%= Rutas.NAV_EMPLEADO%>" />	
		<% } %>
							
		<jsp:include page="<%= Rutas.MAIN%>" />							
							
		<jsp:include page="<%= Rutas.FOOTER%>" />

	</body>
	
</html>
