<%@page import="curso.java.tienda.model.VO.ProductoVO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="curso.java.tienda.config.Rutas, java.util.*"
%>

<!DOCTYPE html>
<html>
	<head>
	
		<title>Carrito</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CARRITO_CSS %>">
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CANTIDAD_CARRITO_CSS %>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<table class="table caption-top" border="1">
		
			<caption>Lista de Productos</caption>
			
			<tr class="text-center">		
				<th scope="col">Producto</th>
				<th scope="col">Cantidad</th>
				<th scope="col">Precio</th>
				<th scope="col">Eliminar Producto</th>			
			</tr>
			
		<%
			// Obtenemos el carrito de la sesión
			HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");
		
			if (carrito != null && !carrito.isEmpty()) {
				
				for (Map.Entry<ProductoVO, Integer> producto: carrito.entrySet()) {
					
					ProductoVO productoVO = producto.getKey();
					Integer cantidad = producto.getValue();
					
					String nombre = productoVO.getNombre();
					double precio = productoVO.getPrecio();
					double total = precio * cantidad;
					int id = productoVO.getId();
		%>
					<tr class="text-center">
						<td>
							<input type="text" name="id[]" id="id" value=<%= nombre %> readonly>
						</td>
						
						<td>
							<div class="input-group">								
								<input type="button" value="-" class="button-minus" data-field="quantity">
								<input type="number" step="1" min="1" max="" value=<%= cantidad %> name="cantidad[]" class="quantity-field">
								<input type="button" value="+" class="button-plus" data-field="quantity">
							</div>
						</td>
						
						<td><%= total %></td>
						
						<td>
							<form action="eliminarProductoCarrito">
								<input type="hidden" name="id" value="<%= id %>"></input>
								<button class="btn btn-outline-danger" type="submit">Eliminar</button>
							</form>						
						</td>
						
					</tr>
		<%
				}
				
			} else {
		%>	
				<tr>
					<td colspan="4" style="text-aling: center; color: red">El carrito está vacío</td>
				</tr>	
		<%		
			}			
		%>
			
			<tr>

				<td colspan="4">
						
					<form action="comprar">
						<button style="float: rigth; margin-right: 2%">Realizar Pedido</button>
					</form>
					
					<form action="actualizar">
						<button style="float: rigth; margin-right: 2%">Actualizar Carrito</button>
					</form>
					
					<form action="vaciar">
						<button style="float: rigth; margin-right: 2%">Vaciar Carrito</button>
					</form>	
								
				</td>
			
			</tr>
		
		</table>
						
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>