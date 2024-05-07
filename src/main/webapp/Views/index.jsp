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
			<jsp:include page="<%= Rutas.MAIN%>" />
				
		<% } else if (usuario.esEmpleado()) {%>
			<jsp:include page="<%= Rutas.NAV_EMPLEADO%>" />	
			<jsp:include page="<%= Rutas.MAIN_EMPLEADO%>" />
								
		<% } else if (usuario.esAdmin()) {%>
			<jsp:include page="<%= Rutas.NAV_ADMIN%>" />	
			<jsp:include page="<%= Rutas.MAIN_ADMIN%>" />					
		<% } %>%>
							
							
		<jsp:include page="<%= Rutas.FOOTER%>" />

	</body>
	
</html>
