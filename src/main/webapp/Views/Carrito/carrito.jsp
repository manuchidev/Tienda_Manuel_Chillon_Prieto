<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import=" java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.ProductoVO" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		<main>
		
			<div class="container">
			
				<table class="table caption-top myTable">
				
					<caption>Lista de Productos</caption>
					
					<thead>
					
						<tr>		
							<th scope="col">Producto</th>
							<th scope="col">Cantidad</th>
							<th scope="col">Precio</th>
							<th scope="col">Eliminar Producto</th>			
						</tr>
						
					</thead>
					
					<tbody>
					
					<%
						// Obtenemos los productos del carrito de la sesión					
						List<ProductoVO> productosCarrito = (List<ProductoVO>) request.getAttribute("productosCarrito");
										
						if (productosCarrito != null && !productosCarrito.isEmpty()) {
							
							for (ProductoVO producto: productosCarrito) {																
					%>
								<tr>
															
									<td>
										<div class="centrarTd">
											<div class="col-xs-8 nombre"><%= producto.getNombre() %></div>
											
											<div class="col-xs-4 imagenProd">
												<img src="<%=request.getContextPath() %><%= producto.getImagen() %>" class="img-responsive imgProdCarrito"  alt="Image">										
											</div>	
											
	<%-- 									<input type="text" name="id[]" id="id" value=<%= nombre %> readonly> --%>									
										</div>
									</td>
									
									<td>
										<div class="centrarTd">
											<div class="input-group">								
												<input type="button" value="-" class="button-minus" data-field="quantity">
												<input type="number" step="1" min="1" max="" value=<%= producto.getCantidad() %> name="cantidad[]" class="quantity-field">
												<input type="button" value="+" class="button-plus" data-field="quantity">
											</div>
										</div>
									</td>
									
									<td>
										<div class="centrarTd">
											<%= producto.getTotal()%> €
										</div>
									</td>
									
									<td>
										<div class="centrarTd">
											<form action="eliminarProducto" method="post">
												<input type="hidden" name="idProd" value="<%= producto.getId() %>"></input>
												<button class="btn btn-danger" type="submit"><span class="glyphicon glyphicon-trash"></span></button>
											</form>
										</div>				
									</td>
									
								</tr>
								
					<%
							}
					%>
								<tr>
			
									<td colspan="4">
																											
										<form action="vaciar">
											<button class="btnVaciarCarrito btn btn-danger">Vaciar Carrito</button>
										</form>	
													
									</td>
								
								</tr>
					<%
							
						} else {
					%>	
							<tr>
								<td colspan="4" style="text-aling: center; color: red">El carrito está vacío</td>
							</tr>	
					<%		
						}			
					%>					
						
					</tbody>
				
				</table>
				
			</div>
			
			
			<%
				if (productosCarrito != null && !productosCarrito.isEmpty()) {
			%>
					<div class="divComprar">
						<form action="comprar">
							<button class="btnRealizarPedido btn btn-success">Realizar Pedido</button>
						</form>
					</div>
			<%
				}
			%>
		
		</main>				
						
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>