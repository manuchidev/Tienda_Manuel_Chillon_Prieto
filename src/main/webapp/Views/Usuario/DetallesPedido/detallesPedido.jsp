<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Producto.ProductoVO"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Perfil</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.DETALLES_PEDIDO_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
	
			<section class="gradient-custom">
			
				<div class="container py-5">
				
					<div class="row d-flex justify-content-center my-4">
					
						<div class="col-md-10">
						
							<div class="card mb-4">
							
								<div class="card-header py-3">
									<h5 class="mb-0">Detalles Pedido</h5>
								</div>
								
								<div class="card-body">
								
								<%  
		                            List<DetallePedidoVO> detallesPedido = (List<DetallePedidoVO>) request.getAttribute("detallesPedido");					
									List<ProductoVO> productosDetallePedido = (List<ProductoVO>) request.getAttribute("productosDetallePedido");
											
		                            if (detallesPedido != null && !detallesPedido.isEmpty() && productosDetallePedido != null && !productosDetallePedido.isEmpty()) {
	                        	%>
										<div class="table-responsive">
										
											<table class="table table-striped">
											
												<thead>
												
													<tr class="text-center">																					
													
														<th class="w-25">Producto</th>
														<th class="w-25">Precio Unidad</th>
														<th class="w-25">Unidades</th>
														<th class="w-25">Impuesto</th>
														<th class="w-25">Total</th>
													</tr>
													
												</thead>
												
												<tbody>
														
												<% 
													for (DetallePedidoVO detallePedido : detallesPedido) { 
														
														ProductoVO producto = null;
																										
														for(ProductoVO prod : productosDetallePedido) {	
															
															if (prod.getId() == detallePedido.getId_producto()) {
																producto = prod;
																break;																								
															}
														}
														
														if (producto != null) {
												%> 
																																															
															<tr class="text-center">
															
																<td class="d-flex align-items-center justify-content-start gap-1">
																
																	<img class="imgProdDetallePedido" src="<%= request.getContextPath() + Rutas.IMAGENES_PROD + producto.getImagen() %>" alt="<%= producto.getNombre() %>">

																    <span class="w-100"><%= producto.getNombre() %></span>
															    
																</td>
																 										
																<td><%= detallePedido.getPrecio_unidad() %>€</td>
																<td><%= detallePedido.getUnidades() %></td>
																<td><%= detallePedido.getImpuesto() %>%</td>
																<td><%= detallePedido.getTotal() %>€</td>
																
															</tr>
												<%
													  }
													}
												%>
														
													</tr>
															
												</tbody>
												
											</table>
											
										</div>
										
							<%  
	                            } else {
	                        %>
									<div class="col-md-12">
										<h2 style="color: red; text-align: center;">Todavía no ha realizado ningún pedido</h2>
									</div>
									
									<% } %>
									
								</div>
								
							</div>
							
						</div>
						
					</div>
					
				</div>
				
			</section>
	
		</main>
		
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>