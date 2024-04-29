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
			
			<div class="d-flex flex-column justify-content-center align-items-center">
			    <h1 class="mt-2">GRACIAS POR SU COMPRA</h1>
				<h3 class="mt-5" style="color: green">PEDIDO REALIZADO CON ÉXITO</h3>
			</div>			
		
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>
