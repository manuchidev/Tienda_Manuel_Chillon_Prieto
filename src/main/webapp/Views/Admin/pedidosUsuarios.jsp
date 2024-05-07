<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, java.text.SimpleDateFormat, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.Pedido.PedidoVO"
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
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
	
			<section class="gradient-custom">
	
				<div class="container py-5">
				
					<form action="PedidosUsuarios" method="get" class="d-flex justify-content-center">
				
						<div class="row">
				
							<div class="col-md-2 mb-1 d-flex align-items-center justify-content-center">				
								<label for="orden" class="lblFiltro">Ordenar:</label>								
							</div>

							<div class="col-md-6 mb-1 d-flex align-items-center justify-content-center">
							
								<select id="orden" name="orden">
									<option value="DESC">Más Nuevos</option>
									<option value="ASC">Más Antiguos</option>
								</select>
								
							</div>
								
							<div class="col-md-4 mb-1 d-flex align-items-center justify-content-center">
								<input type="submit" value="Filtrar Pedidos">
							</div>
				
						</div>
				
					</form>
				
					<div class="row d-flex justify-content-center my-4">
					
						<div class="col-md-10">
						
							<div class="card mb-4">
							
								<div class="card-header py-3">
									<h5 class="mb-0">Lista de Pedidos</h5>
								</div>
								
								<div class="card-body">
								
								<%  
									List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios");
		                            List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("pedidos");
								
								    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		                            if (pedidos != null && !pedidos.isEmpty()) {
	                        	%>
										<div class="table-responsive">
										
											<table class="table table-striped">
											
												<thead>
												
													<tr class="text-center">
													
														<th class="w-25">Usuario</th>
														<th class="w-25">Fecha</th>
														<th class="w-25">Método de Pago</th>
														<th class="w-25">Estado</th>
														<th class="w-25">Acciones</th>
														
													</tr>
													
												</thead>
												
												<tbody>
												
												<% for (PedidoVO pedido : pedidos) { 
												
													String fecha = sdf.format(pedido.getFecha());
												%>																								
													<tr class="text-center">
												<%
													for (UsuarioVO usuario : usuarios) {
														
														if (pedido.getId_usuario() == usuario.getId()) {
												%>
															<td><%= usuario.getEmail() %></td>
												<%			 
														}
													}
													
												%>													
														<td><%= fecha %></td>
														<td><%= pedido.getMetodo_pago() %></td>
														
													<%
														switch(pedido.getEstado()) {
                                                               case "PE":
                                                               	%>
                                                               	<td style="color:rgb(255, 217, 0); text-shadow: 1px 1px 1px black;">Pendiente de Envío</td>
                                                               	<td class="d-flex flex-column justify-content-center">

														    		<div class="d-flex justify-content-center mb-2">
														    
																        <form action="DetallesPedido" method="post" class="me-1">
																            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
																            <button type="submit" class="btn btn-sm bg-warning">Detalle</button>
																        </form>
																        															        
																    </div>
																    
														        	<form action="PedidosUsuarios" method="post">
															            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
															            <button type="submit" class="btn btn-sm bg-success" name="accion" value="Enviar">Enviar</button>
															        </form>
																    
																  </td>
                                                               	<%
                                                                   break;
                                                               	
                                                               case "PC":
                                                               	%>
                                                               	<td style="color:rgb(255, 123, 0); text-shadow: 1px 1px 1px black;">Pendiente de Cancelación</td> 
                                                               	
                                                               	<td class="d-flex flex-column justify-content-center">

														    		<div class="d-flex justify-content-center mb-2">
														    
																        <form action="DetallesPedido" method="post" class="me-1">
																            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
																            <button type="submit" class="btn btn-sm bg-warning">Detalle</button>
																        </form>
																        															        
																    </div>
																    																    																    
																    <form action="PedidosUsuarios" method="post">
																        <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
																        <button type="submit" class="btn btn-sm bg-danger" name="accion" value="Cancelar">Cancelar</button>
																    </form>
																    
																    																    
																  </td>
                                                               	<%
                                                                   break;
                                                               	
                                                               case "E":
                                                               	%>
                                                               	<td style="color:rgb(2, 165, 2); text-shadow: 1px 1px 1px black;">Enviado</td>
                                                               	
                                                               	<td class="d-flex flex-column justify-content-center">
															    	<div class="d-flex justify-content-center mb-2">
															    
																        <form action="DetallesPedido" method="post" class="me-1">
																            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
																            <button type="submit" class="btn btn-sm bg-warning">Detalle</button>
																        </form>
																       																        
																    </div>
																 </td>
                                                               	<%
                                                                   break;
                                                               	
                                                               case "C":
                                                               	%>
                                                               	<td style="color:rgb(211, 0, 0); text-shadow: 1px 1px 1px black;">Cancelado</td>
                                                               	
                                                               	<td class="d-flex flex-column justify-content-center">
															    
															        <form action="DetallesPedido" method="post" class="me-1">
															            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
															            <button type="submit" class="btn btn-sm bg-warning">Detalle</button>
															        </form>
															     </td>
                                                               	<%
                                                                   break;
                                                           }
													%>
														
													</tr>
													
												<% } %>
													
												</tbody>
												
											</table>
											
										</div>
										
							<%  
	                            } else {
	                        %>
									<div class="col-md-12">
										<h2 style="color: red; text-align: center;">Los usuarios no han hecho ningún pedido</h2>
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