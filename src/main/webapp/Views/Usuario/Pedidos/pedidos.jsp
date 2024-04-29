<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.Pedido.PedidoVO"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Perfil</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PEDIDOS_CSS%>">
	  
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
									<h5 class="mb-0">Lista de Pedidos</h5>
								</div>
								
								<div class="card-body">
								
								<%  
		                            List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("pedidos");
		
		                            if (pedidos != null && !pedidos.isEmpty()) {
	                        	%>
										<div class="table-responsive">
										
											<table class="table table-striped">
											
												<thead>
												
													<tr class="text-center">
													
														<th class="w-25">Fecha</th>
														<th class="w-25">Método de Pago</th>
														<th class="w-25">Estado</th>
														<th class="w-25">Acciones</th>
														
													</tr>
													
												</thead>
												
												<tbody>
												
												<% for (PedidoVO pedido : pedidos) { %>
													
													<tr class="text-center">
													
														<td><%= pedido.getFecha() %></td>
														<td><%= pedido.getMetodo_pago() %></td>
														<td><%= pedido.getEstado() %></td>
														
														<td class="d-flex flex-column justify-content-center">

														    <div class="d-flex justify-content-center mb-2">
														    
														        <form action="DetallesPedido" method="post" class="me-1">
														            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
														            <button type="submit" class="btn btn-sm bg-warning">Detalle</button>
														        </form>
														        
														        <form action="Pedidos" method="post">
														            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
														            <button type="submit" class="btn btn-sm bg-success" name="accion" value="Factura">Factura</button>
														        </form>
														        
														    </div>
														
														    <form action="Pedidos" method="post">
														        <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
														        <button type="submit" class="btn btn-sm bg-danger" name="accion" value="Cancelacion">Solicitar Cancelación</button>
														    </form>
																							
														</td>

													</tr>
													
												<% } %>
													
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